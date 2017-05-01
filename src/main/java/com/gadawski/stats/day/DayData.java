package com.gadawski.stats.day;

import com.google.common.collect.Maps;

import java.time.DayOfWeek;
import java.util.Map;

public class DayData {
    private final int index;
    private final DayOfWeek dayOfWeek;
    /** <Hour, value> */
    private final Map<Integer, Double> map;

    public DayData(String line) {
        String[] elems = line.split(",");

        this.index= Integer.parseInt(elems[0]);
        this.dayOfWeek = DayOfWeek.valueOf(elems[1].toUpperCase());
        this.map = Maps.newLinkedHashMap();
        for (int i = 2; i < elems.length; i++) {
            this.map.put(i-2, Double.valueOf(elems[i]));
        }
    }

    public int getIndex() {
        return index;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public double getTemperatureAt(int hour) {
        return map.get(hour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayData dayData = (DayData) o;

        if (index != dayData.index) return false;
        if (dayOfWeek != dayData.dayOfWeek) return false;
        return map.equals(dayData.map);
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + dayOfWeek.hashCode();
        result = 31 * result + map.hashCode();
        return result;
    }
}
