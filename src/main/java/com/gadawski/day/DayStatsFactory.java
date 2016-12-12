package com.gadawski.day;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class DayStatsFactory {

    private Map<DayOfWeek, List<DayData>> data;

    private DayStatsFactory(Map<DayOfWeek, List<DayData>> data) {
        this.data = data;
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

        return new DayStatsFactory(map);
    }

    public List<DayStats> getStats() {
        List<DayStats> result = Lists.newArrayList();
        for (DayOfWeek day : DayOfWeek.values()) {
            List<DayData> dayData = data.get(day);
            if (dayData != null) {
                result.add(new DayStats(dayData));
            }
        }

        return result;
    }
}
