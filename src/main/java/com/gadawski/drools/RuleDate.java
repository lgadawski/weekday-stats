package com.gadawski.drools;

import lombok.Getter;
import lombok.ToString;

import java.time.DayOfWeek;

@ToString
@Getter
public class RuleDate {

    private final DayOfWeek dayOfWeek;
    private final int hour;

    public RuleDate(DayOfWeek dayOfWeek, int hour) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
    }

}
