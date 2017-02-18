package com.gadawski.drools;

import com.gadawski.stats.WeekStats;
import com.gadawski.stats.day.DayStats;
import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;

import java.time.DayOfWeek;

public class RuleCreatoreImpl implements RuleCreator {

    private final WeekStats weekStats;

    public RuleCreatoreImpl(WeekStats weekStats) {
        this.weekStats = weekStats;
    }

    @Override
    public String build() {
        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
        packageDescrBuilder.name("com.gadawski.drools");

        for (DayOfWeek day : DayOfWeek.values()) {
            for (int i = 0; i < 24; i++) {
                addRule(packageDescrBuilder, getDayStats(day), i);
            }
        }        
        
        String rules = new DrlDumper().dump(packageDescrBuilder.getDescr());
        System.out.println(rules);

        return rules;
    }

    private DayStats getDayStats(DayOfWeek day) {
        return getWeekStats().statsFor(day);
    }

    private void addRule(PackageDescrBuilder builder, DayStats dayStats, int hour) {
        builder
            .newRule()
            .name("HA rule for day: " + dayStats.getDayOfWeek() + " and hour: " + hour)
                .lhs()
                    .pattern()
                    .id("day", false).type("RuleDate")
                        .constraint("day.getDayOfWeek() == DayOfWeek." + dayStats.getDayOfWeek()).end()
                        .pattern()
                            .id("day", false).type("RuleDate")
                        .constraint("day.getHour() == " + hour).end()
            .end()
                .rhs("System.out.println(\"push data {" + dayStats.meanTempAt(hour)
                        + " std: " + dayStats.standardDeviationAt(hour) + "}to HA!\");")
            .end();
    }

    public WeekStats getWeekStats() {
        return weekStats;
    }
}
