package com.gadawski;

import com.gadawski.day.DayData;
import com.gadawski.day.DayStats;
import com.gadawski.day.DayStatsFactory;
import com.google.common.collect.Maps;

import java.io.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeekStats {
    private final Map<DayOfWeek, DayStats> stats;

    public WeekStats(List<DayData> dayData) {
        stats = Maps.newHashMapWithExpectedSize(7);
        DayStatsFactory factory = DayStatsFactory.fromData(dayData);
        for (DayStats stat : factory.getStats()) {
            stats.put(stat.getDayOfWeek(), stat);
        }
    }

    public static WeekStats parseFromCsv(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(new File("persons.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<DayData> stats =
                br.lines()
                        .map(DayData::new)
                        .collect(Collectors.toList());

        return null;
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
