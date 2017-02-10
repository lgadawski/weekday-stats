package com.gadawski.stats;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class HourStats {
    private final double mean;
    private final double standardDeviation;


    public HourStats(List<Double> data) {
        DescriptiveStatistics stats = new DescriptiveStatistics(data.stream().mapToDouble(i -> i).toArray());

        this.mean = stats.getMean();
        this.standardDeviation = stats.getStandardDeviation();
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    @Override
    public String toString() {
        return "HourStats{" +
                "mean=" + mean +
                ", standardDeviation=" + standardDeviation +
                '}';
    }
}
