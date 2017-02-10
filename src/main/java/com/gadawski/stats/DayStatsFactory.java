package com.gadawski.stats.day;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class DayStatsFactory {

    private Map<DayOfWeek, List<com.gadawski.stats.day.DayData>> data;

    private DayStatsFactory(Map<DayOfWeek, List<com.gadawski.stats.day.DayData>> data) {
        this.data = data;
    }

    public static DayStatsFactory fromData(List<com.gadawski.stats.day.DayData> dayData) {
        Map<DayOfWeek, List<com.gadawski.stats.day.DayData>> map = Maps.newHashMap();
        for (com.gadawski.stats.day.DayData day : dayData) {
            List<com.gadawski.stats.day.DayData> list = map.get(day.getDayOfWeek());
            if (map.get(day.getDayOfWeek()) == null) {
                list = Lists.newArrayList();
            }
            list.add(day);
            map.put(day.getDayOfWeek(), list);
        }

        return new DayStatsFactory(map);
    }

    public List<com.gadawski.stats.day.DayStats> getStats() {
        List<com.gadawski.stats.day.DayStats> result = Lists.newArrayList();
        for (DayOfWeek day : DayOfWeek.values()) {
            List<com.gadawski.stats.day.DayData> dayData = data.get(day);
            if (dayData != null) {
                result.add(new com.gadawski.stats.day.DayStats(dayData));
            }
        }

        return result;
    }
}
