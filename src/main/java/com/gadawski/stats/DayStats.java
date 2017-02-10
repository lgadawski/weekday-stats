package com.gadawski.stats.day;

import com.gadawski.stats.HourStats;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class DayStats {

    private final Map<Integer, HourStats> map;
    private final DayOfWeek dayOfWeek;

    public DayStats(List<com.gadawski.stats.day.DayData> dayData) {
        Preconditions.checkArgument(!dayData.isEmpty());

        dayOfWeek = dayData.get(0).getDayOfWeek();
        map = Maps.newHashMap();
        for (int i = 0; i < 24; i++) {
            List<Double> tempsAtHour = Lists.newArrayList();
            for (com.gadawski.stats.day.DayData data : dayData) {
                tempsAtHour.add(data.getTemperatureAt(i));
            }
            map.put(i, new HourStats(tempsAtHour));
        }
    }

    public double meanTempAt(int hour) {
        return map.get(hour).getMean();
    }

    public double standardDeviationAt(int hour) {
        return map.get(hour).getStandardDeviation();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "DayStats{" +
                "map=" + map +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
