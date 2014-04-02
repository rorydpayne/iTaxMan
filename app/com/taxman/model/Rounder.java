package com.taxman.model;

import java.math.BigDecimal;


/**
 * Created by rory.payne on 18/03/14.
 */
public class Rounder {
    private int scale;

    public Rounder(int scale) {
        this.scale = scale;
    }

    public BigDecimal round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public BigDecimal monthly(BigDecimal value) {
        BigDecimal bd = value.divide(round(12), 2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public BigDecimal weekly(BigDecimal value) {
        BigDecimal bd = value.divide(round(52), 2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}