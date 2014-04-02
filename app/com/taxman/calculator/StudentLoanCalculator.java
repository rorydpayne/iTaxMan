package com.taxman.calculator;

import com.taxman.model.Rates;
import com.taxman.model.Rounder;
import models.UserFormData;
import play.mvc.Controller;
import java.math.BigDecimal;

/**
 * Created by rory.payne on 24/03/14.
 */
public class StudentLoanCalculator extends Controller {
    private UserFormData user;
    private Rates rates;
    private Rounder r;

    public StudentLoanCalculator(UserFormData user, Rates rates, Rounder r) {
        this.user=user;
        this.rates=rates;
        this.r=r;
    }

    public BigDecimal getStudentLoan() {
        boolean studentLoan = user.isStudentLoan();
        double salary = user.getSalary().doubleValue();
        double studentLoanRate = rates.getStudentLoanRate();
        double studentLoanRepaymentThreshold = rates.getStudentLoanRepaymentThreshold();
        double studentLoanRepayment;
        if (studentLoan) {
            double taxable = salary - studentLoanRepaymentThreshold;
            if (taxable < 0){
                taxable = 0;
            }

            studentLoanRepayment = taxable * studentLoanRate;

        } else {
            studentLoanRepayment = 0;
        }
        return r.round(studentLoanRepayment);
    }
}
