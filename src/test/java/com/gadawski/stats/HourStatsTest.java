package com.gadawski.stats;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HourStatsTest {

    @Test
    public void meanHourTest() throws Exception {
        // given
        List<Double> data = Arrays.asList(1D, 2D, 3D);

        // when
        HourStats hourStats = new HourStats(data);

        // then
        assertThat(hourStats.getMean()).isEqualTo(2);
    }

    @Test
    public void stdDeviationTest() throws Exception {
        // given
        List<Double> data = Arrays.asList(1D, 2D, 3D);

        // when
        HourStats hourStats = new HourStats(data);

        // then
        assertThat(hourStats.getStandardDeviation()).isEqualTo(1);
    }
}
