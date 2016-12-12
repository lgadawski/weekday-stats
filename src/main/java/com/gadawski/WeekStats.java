package com.gadawski;

import com.gadawski.day.DayData;
import com.gadawski.day.DayStats;
import com.gadawski.day.DayStatsFactory;
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
        return "WeekStats{" +
                "stats=" + stats +
                '}';
    }
}
