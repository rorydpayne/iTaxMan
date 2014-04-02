package com.taxman.model;

import java.io.Serializable;

import play.data.validation.Constraints.*;

/**
 * Created by rory.payne on 19/03/14.
 */
public class Rates implements Serializable {
    private String Fiscal;
    private double persAllowance;
    private double allowanceIncomeLimit;
    private double basicBand;
    private double highBand;
    private double basicRate;
    private double highRate;
    private double addRate;
    private double lowerNILimit;
    private double upperNILimit;
    private double stdNIRate;
    private double addNIRate;
    private double studentLoanRate;
    private double studentLoanRepaymentThreshold;

    public double getStudentLoanRate() {
        return studentLoanRate;
    }

    public void setStudentLoanRate(double studentLoanRate) {
        this.studentLoanRate = studentLoanRate;
    }

    public double getStudentLoanRepaymentThreshold() {
        return studentLoanRepaymentThreshold;
    }

    public void setStudentLoanRepaymentThreshold(double studentLoanRepaymentThreshold) {
        this.studentLoanRepaymentThreshold = studentLoanRepaymentThreshold;
    }

    public Rates(String FY, double PA, double AIL, double Income_BB, double Income_HB, double Income_BR, double Income_HR, double Income_AR, double NI_LL, double NI_UL, double NI_SR, double NI_AR, double SLR, double SLRT) {
        this.Fiscal = FY;
        this.persAllowance = PA;
        this.allowanceIncomeLimit = AIL;
        this.basicBand = Income_BB;
        this.highBand = Income_HB;
        this.basicRate = Income_BR;
        this.highRate = Income_HR;
        this.addRate = Income_AR;
        this.lowerNILimit = NI_LL;
        this.upperNILimit = NI_UL;
        this.stdNIRate = NI_SR;
        this.addNIRate = NI_AR;
        this.studentLoanRate = SLR;
        this.studentLoanRepaymentThreshold = SLRT;
    }

    public String getFiscal() {
        return Fiscal;
    }

    public double getPersAllowance() {
        return persAllowance;
    }

    public double getAllowanceIncomeLimit() {
        return allowanceIncomeLimit;
    }

    public double getBasicBand() {
        return basicBand;
    }

    public double getHighBand() {
        return highBand;
    }

    public double getBasicRate() {
        return basicRate;
    }

    public double getHighRate() {
        return highRate;
    }

    public double getAddRate() {
        return addRate;
    }

    public double getLowerNILimit() {
        return lowerNILimit;
    }

    public double getUpperNILimit() {
        return upperNILimit;
    }

    public double getStdNIRate() {
        return stdNIRate;
    }

    public double getAddNIRate() {
        return addNIRate;
    }

    @Override
    public String toString() {
        return "\nFY: " + Fiscal +
                "\nPersonal Allowance: " + persAllowance +
                "\nIncome Limit for Personal Allowance: " + allowanceIncomeLimit +
                "\nBasic Band Upper Limit: " + basicBand +
                "\nHigher Band Upper Limit: " + highBand +
                "\nBasic Rate: " + basicRate +
                "\nHigher Rate: " + highRate +
                "\nAdditional Rate: " + addRate +
                "\nNI Lower Limit (earnings per week): " + lowerNILimit +
                "\n NI Upper Limit (earnings per week): " + upperNILimit +
                "\n NI Standard Rate: " + stdNIRate +
                "\n NI Additional Rate: " + addNIRate;
    }

    public String fiscalToString() {
        return Fiscal;
    }
}