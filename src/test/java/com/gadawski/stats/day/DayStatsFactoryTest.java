package com.gadawski.stats.day;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DayStatsFactoryTest {

    @Test
    public void testFromDataSingleDay() {
        // given
        String t1 = "2,tuesday,18,18,18,18,18,18,22,22,18,18,18,18,18,18,18,18,18,22,22,22,22,22,22,22,22";

        // when
        com.gadawski.stats.day.DayData dayData1 = new com.gadawski.stats.day.DayData(t1);

        // then
        com.gadawski.stats.day.DayStats dayStats = new com.gadawski.stats.day.DayStats(Collections.singletonList(dayData1));
        assertThat(dayStats.meanTempAt(0)).isEqualTo(18);
        assertThat(dayStats.meanTempAt(22)).isEqualTo(22);
        assertThat(dayStats.standardDeviationAt(0)).isEqualTo(0);
        assertThat(dayStats.standardDeviationAt(10)).isEqualTo(0);
    }

    @Test
    public void testFromDataSameDayMultipleData() {
        // given
        String t1 = "1,tuesday,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18";
        String t2 = "2,tuesday,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19";
        String t3 = "3,tuesday,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20";

        // when
        com.gadawski.stats.day.DayData dayData1 = new com.gadawski.stats.day.DayData(t1);
        com.gadawski.stats.day.DayData dayData2 = new com.gadawski.stats.day.DayData(t2);
        com.gadawski.stats.day.DayData dayData3 = new com.gadawski.stats.day.DayData(t3);

        // then
        com.gadawski.stats.day.DayStats dayStats = new com.gadawski.stats.day.DayStats(Arrays.asList(dayData1, dayData2, dayData3));
        assertThat(dayStats.meanTempAt(0)).isEqualTo(19);
        assertThat(dayStats.meanTempAt(10)).isEqualTo(19);
        assertThat(dayStats.meanTempAt(20)).isEqualTo(19);
        assertThat(dayStats.standardDeviationAt(1)).isEqualTo(1);
        assertThat(dayStats.standardDeviationAt(11)).isEqualTo(1);
        assertThat(dayStats.standardDeviationAt(12)).isEqualTo(1);
    }

}