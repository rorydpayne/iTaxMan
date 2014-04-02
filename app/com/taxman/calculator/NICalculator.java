package com.taxman.calculator;

import com.taxman.model.Rates;
import com.taxman.model.Rounder;
import models.UserFormData;
import play.mvc.Controller;
import java.math.BigDecimal;

/**
 * Created by rory.payne on 24/03/14.
 */
public class NICalculator extends Controller {
    private UserFormData user;
    private Rates rates;
    private Rounder r;

    public NICalculator(UserFormData user, Rates rates, Rounder r) {
        this.user = user;
        this.rates = rates;
        this.r = r;
    }

    public BigDecimal getNI() {
        double salary = user.getSalary().doubleValue();
        double lowerLimit = rates.getLowerNILimit();
        double upperLimit = rates.getUpperNILimit();
        double NIRate = rates.getStdNIRate();
        double addNIRate = rates.getAddNIRate();

        double weeklyWage = salary/52;
        double basicNI = 0;
        double addNI = 0;
        double NI = 0;

        if (weeklyWage > lowerLimit) {
            if (weeklyWage > upperLimit) {
                basicNI = (upperLimit - lowerLimit)*NIRate;
                addNI = (weeklyWage - upperLimit)*addNIRate;
            } else {
                basicNI = (weeklyWage - lowerLimit)*NIRate;
            }
            NI = basicNI + addNI;
        }

        double totNI = NI*52;
        return r.round(totNI);
    }
}
