package com.taxman.calculator;

import com.taxman.model.Rates;
import com.taxman.model.Rounder;
import models.UserFormData;
import play.mvc.Controller;
import java.math.BigDecimal;

/**
 * Created by rory.payne on 24/03/14.
 */
public class TaxCalculator extends Controller {

    private UserFormData user;
    private Rates rates;
    private Rounder r;

    public TaxCalculator(UserFormData user, Rates rates, Rounder r) {
        this.user = user;
        this.rates = rates;
        this.r = r;
    }

    private double taxableAmount() {
        double salary = user.getSalary().doubleValue();
        double allowance = rates.getPersAllowance();
        double allowanceLimit = rates.getAllowanceIncomeLimit();

        double taxableIncome = salary - allowance;
        if (taxableIncome < 0) {
            taxableIncome = 0.0;
        } else if (salary > allowanceLimit) {
            double excess = salary - allowanceLimit;

            // recalculate personal allowance
            allowance -= 0.5 * excess;
            if (allowance < 0) {
                allowance = 0;
            }
            taxableIncome = salary - allowance;
        }
        return taxableIncome;
    }

    public BigDecimal getTax() {

        double basicBand = rates.getBasicBand();
        double highBand = rates.getHighBand();
        double basicRate = rates.getBasicRate();
        double highRate = rates.getHighRate();
        double addRate = rates.getAddRate();
        double taxableIncome = taxableAmount();


        double basicAmount = taxableIncome;
        if (taxableIncome > basicBand) {
            basicAmount = basicBand;
        }

        double higherAmount = taxableIncome - basicBand;
        if (higherAmount < 0) {
            higherAmount = 0;
        }
        if (higherAmount > (highBand-basicBand)) {
            higherAmount = highBand - basicBand;
        }

        double addAmount = taxableIncome - highBand;
        if (addAmount < 0) {
            addAmount = 0;
        }

        double tax = basicAmount*basicRate + higherAmount*highRate + addAmount*addRate;
        return r.round(tax);
    }
}
