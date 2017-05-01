package com.gadawski.stats.day;

import com.gadawski.stats.Temperature;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DayStatsFactoryTest {

    @Test
    public void testFromDataSingleDay() {
        // given
        String t1 = "2,tuesday,18,18,18,18,18,18,22,22,18,18,18,18,18,18,18,18,18,22,22,22,22,22,22,22,22";

        // when
        DayData dayData1 = new DayData(t1);

        // then
        DayStats dayStats = new DayStats(Collections.singletonList(dayData1));
        assertThat(dayStats.meanTempAt(0)).isEqualTo(Temperature.valueOf(18));
        assertThat(dayStats.meanTempAt(22)).isEqualTo(Temperature.valueOf(22));
        assertThat(dayStats.standardDeviationAt(0)).isEqualTo(Temperature.ZERO);
        assertThat(dayStats.standardDeviationAt(10)).isEqualTo(Temperature.ZERO);
    }

    @Test
    public void testFromDataSameDayMultipleData() {
        // given
        String t1 = "1,tuesday,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18";
        String t2 = "2,tuesday,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19";
        String t3 = "3,tuesday,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20";

        // when
        DayData dayData1 = new DayData(t1);
        DayData dayData2 = new DayData(t2);
        DayData dayData3 = new DayData(t3);

        // then
        DayStats dayStats = new DayStats(Arrays.asList(dayData1, dayData2, dayData3));
        assertThat(dayStats.meanTempAt(0)).isEqualTo(Temperature.valueOf(19));
        assertThat(dayStats.meanTempAt(10)).isEqualTo(Temperature.valueOf(19));
        assertThat(dayStats.meanTempAt(20)).isEqualTo(Temperature.valueOf(19));
        assertThat(dayStats.standardDeviationAt(1)).isEqualTo(Temperature.ONE);
        assertThat(dayStats.standardDeviationAt(11)).isEqualTo(Temperature.ONE);
        assertThat(dayStats.standardDeviationAt(12)).isEqualTo(Temperature.ONE);
    }

}