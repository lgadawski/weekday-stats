package com.gadawski.stats;

import com.gadawski.stats.day.DayData;
import com.gadawski.stats.day.DayStats;
import com.gadawski.stats.day.DayStatsFactory;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class WeekStats {
    private final Map<DayOfWeek, DayStats> stats;

    public WeekStats(List<DayData> dayData) {
        stats = Maps.newHashMapWithExpectedSize(7);
        DayStatsFactory factory = DayStatsFactory.fromData(dayData);
        for (DayStats stat : factory.getStats()) {
            stats.put(stat.getDayOfWeek(), stat);
        }
    }

    public DayStats statsFor(DayOfWeek dayOfWeek) {
        return stats.get(dayOfWeek);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<DayOfWeek, DayStats> e : stats.entrySet()) {
            for (int i = 0; i < 24; i++) {
                DayStats value = e.getValue();
                sb.append(value.meanTempAt(i).value() + ", " + value.standardDeviationAt(i).value() + "\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

//    @Override
//    public String toString() {
//        return "WeekStats{" +
//                "stats=" + stats +
//                '}';
//    }
}
