package com.gadawski.stats;

import com.gadawski.stats.day.DayData;
import com.gadawski.stats.day.DayStats;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekStatsTest {

    @Test
    public void basicWeekStats() throws Exception {
        // given
        String t1 = "2,tuesday,18,18,18,18,18,18,22,22,18,18,18,18,18,18,18,18,18,22,22,22,22,22,22,22,22";
        String t2 = "16,tuesday,20,18,18,18,18,18,22,21,20,19,18,18,19,20,20,21,22,21,20,22,21,22,21,22,22";

        // when
        DayData dayData1 = new DayData(t1);
        DayData dayData2 = new DayData(t2);

        WeekStats weekStats = new WeekStats(Arrays.asList(dayData1, dayData2));

        // then
        assertThat(weekStats.statsFor(DayOfWeek.SUNDAY)).isNull();
        assertThat(weekStats.statsFor(DayOfWeek.SATURDAY)).isNull();
        assertThat(weekStats.statsFor(DayOfWeek.FRIDAY)).isNull();
        assertThat(weekStats.statsFor(DayOfWeek.THURSDAY)).isNull();
        assertThat(weekStats.statsFor(DayOfWeek.WEDNESDAY)).isNull();
        assertThat(weekStats.statsFor(DayOfWeek.MONDAY)).isNull();

        DayStats dayStats = weekStats.statsFor(DayOfWeek.TUESDAY);
        assertThat(dayStats).isNotNull();
        assertThat(dayStats.meanTempAt(0)).isEqualTo(Temperature.valueOf(19));
        assertThat(dayStats.meanTempAt(1)).isEqualTo(Temperature.valueOf(18));
        assertThat(dayStats.standardDeviationAt(0)).isEqualTo(Temperature.valueOf(Math.sqrt(2)));
        assertThat(dayStats.standardDeviationAt(1)).isEqualTo(Temperature.valueOf(0));
    }
}

