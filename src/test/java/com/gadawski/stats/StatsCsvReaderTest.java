package com.gadawski.stats;

import com.gadawski.stats.WeekStats;
import com.gadawski.stats.day.DayData;
import com.gadawski.stats.day.DayStats;
import com.gadawski.stats.reader.CsvReader;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsCsvReaderTest {

    @Test
    public void testReadingCsvFile() {
        // given
        String filePath = "/home/lukasz/IdeaProjects/weekday-stats/src/test/resources/data.csv";

        // when
        CsvReader reader = new CsvReader(filePath);
        List<String> lines = reader.getLines();
        String first = lines.get(0);
        String second = lines.get(1);

        // then
        assertThat(first).isNotNull();
        assertThat(first.charAt(0)).isEqualTo(',');

        assertThat(second).isEqualTo("1,monday,18,18,18,18,18,18,21,21,18,18,18,18,18,18,18,18,18,21,21,21,21,21,21,21,21");
    }

    @Test
    public void testGettingWeedayStats() {
        // given
        String filePath = "/home/lukasz/IdeaProjects/weekday-stats/src/test/resources/data.csv";

        // when
        CsvReader reader = new CsvReader(filePath);
        WeekStats weekStats = new WeekStats(reader.getData());

        DayStats monday = weekStats.statsFor(DayOfWeek.MONDAY);
        BigDecimal expectedMondayMean = BigDecimal.valueOf(18 + 18 + 19 + 18).divide(BigDecimal.valueOf(4));

        DayStats friday = weekStats.statsFor(DayOfWeek.FRIDAY);
        BigDecimal expectedFridayMean = BigDecimal.valueOf(19 + 19 + 19 + 19).divide(BigDecimal.valueOf(4));

        DayStats thursday = weekStats.statsFor(DayOfWeek.THURSDAY);
        BigDecimal expectedThursdayMean = BigDecimal.valueOf(20 + 22 + 19 + 22).divide(BigDecimal.valueOf(4));

        // then
        assertThat(monday.meanTempAt(0)).isEqualTo(expectedMondayMean.doubleValue());
        assertThat(monday.standardDeviationAt(0)).isEqualTo(0.5);

        assertThat(friday.meanTempAt(10)).isEqualTo(expectedFridayMean.doubleValue());
        assertThat(friday.standardDeviationAt(10)).isEqualTo(0);

        assertThat(thursday.meanTempAt(22)).isEqualTo(expectedThursdayMean.doubleValue());
        assertThat(thursday.standardDeviationAt(22)).isEqualTo(1.5);
    }
}
