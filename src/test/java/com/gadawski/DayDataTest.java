package com.gadawski;

import com.gadawski.day.DayData;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

public class DayDataTest {

    @Test
    public void parseDayStats() throws Exception {
        // given
        String line = "1,monday,18,18,18,18,18,18,21,21,18,18,18,18,18,18,18,18,18,21,21,21,21,21,21,21,21";

        // when
        DayData dayData = new DayData(line);

        // then
        assertThat(dayData.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(dayData.getIndex()).isEqualTo(1);
        assertThat(dayData.getTemperatureAt(22)).isEqualTo(21);
        assertThat(dayData.getTemperatureAt(0)).isEqualTo(18);
    }

    @Test
    public void dayStats() throws Exception {
        // given
        String t1 = "2,tuesday,18,18,18,18,18,18,22,22,18,18,18,18,18,18,18,18,18,22,22,22,22,22,22,22,22";
        String t2 = "16,tuesday,18,18,18,18,18,18,22,21,20,19,18,18,19,20,20,21,22,21,20,22,21,22,21,22,22";

        // when
        DayData t1s = new DayData(t1);
        DayData t2s = new DayData(t2);
//        WeekStats weekStats = new WeekStats(Arrays.asList(t1s, t2s));
//
//        // then
//        DayData dayData = weekStats.statsFor(DayOfWeek.TUESDAY);
//        assertThat(dayData).isNotNull();
////        assertThat(dayData.getTemperatureAt())
    }
}
