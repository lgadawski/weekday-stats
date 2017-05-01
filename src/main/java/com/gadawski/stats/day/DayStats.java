package com.gadawski.stats.day;

import com.gadawski.stats.HourStats;
import com.gadawski.stats.Temperature;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class DayStats {

    private final Map<Integer, HourStats> map;
    private final DayOfWeek dayOfWeek;

    public DayStats(List<DayData> dayData) {
        Preconditions.checkArgument(!dayData.isEmpty());

        dayOfWeek = dayData.get(0).getDayOfWeek();
        map = Maps.newHashMap();
        for (int i = 0; i < 24; i++) {
            List<Double> tempsAtHour = Lists.newArrayList();
            for (DayData data : dayData) {
                tempsAtHour.add(data.getTemperatureAt(i));
            }
            map.put(i, new HourStats(tempsAtHour));
        }
    }

    public Temperature meanTempAt(int hour) {
        double mean = map.get(hour).getMean();

        return Temperature.valueOf(mean);
    }

    public Temperature standardDeviationAt(int hour) {
        double standardDeviation = map.get(hour).getStandardDeviation();

        return Temperature.valueOf(standardDeviation);
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
