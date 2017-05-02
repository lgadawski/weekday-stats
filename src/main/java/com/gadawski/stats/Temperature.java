package com.gadawski.stats;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode
@ToString
public class Temperature {

    public static final Temperature ZERO = Temperature.valueOf(0);
    public static final Temperature ONE = Temperature.valueOf(1);

    private final BigDecimal value;

    private Temperature(BigDecimal value) {
        this.value = value;
    }

    public static Temperature valueOf(BigDecimal val) {
        return new Temperature(val.setScale(1, BigDecimal.ROUND_HALF_UP));
    }

    public static Temperature valueOf(double val) {
        return new Temperature(BigDecimal.valueOf(val).setScale(1, BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal value() {
        return value;
    }
}
