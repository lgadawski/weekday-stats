package com.gadawski.day;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class DayStatsFactory {

    private List<DayStats> stats;

    private DayStatsFactory(List<DayStats> stats) {
        this.stats = stats;
    }

    public static DayStatsFactory fromData(List<DayData> dayData) {
        Map<DayOfWeek, List<DayData>> map = Maps.newHashMap();
        for (DayData day : dayData) {
            List<DayData> list = map.get(day.getDayOfWeek());
            if (map.get(day.getDayOfWeek()) == null) {
                list = Lists.newArrayList();
            }
            list.add(day);
            map.put(day.getDayOfWeek(), list);
        }

        List<DayStats> result = Lists.newArrayList();
        for (DayOfWeek day : DayOfWeek.values()) {
            List<DayData> data = map.get(day);
            if (data != null) {
                result.add(new DayStats(data));
            }
        }

        return new DayStatsFactory(result);
    }

    public List<DayStats> getStats() {
        return stats;
    }
}
