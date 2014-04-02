package controllers;



import com.taxman.calculator.NICalculator;
import com.taxman.calculator.StudentLoanCalculator;
import com.taxman.calculator.TaxCalculator;
import com.taxman.model.Rates;

import com.taxman.model.Rounder;
import models.FiscalYear_;
import models.UserFormData;
import views.html.index;
import java.math.BigDecimal;

import play.mvc.*;
import play.data.*;

import static play.data.Form.form;


/**
 * Created by rory.payne on 27/03/14.
 */
public class Taxes extends Controller {

    final static FiscalYear_ FY = new FiscalYear_();

    // add some rates
    final static Rates r1 = new Rates("13/14", 9440, 100000, 32011, 150000, 0.2, 0.4, 0.45, 149, 797, 0.12, 0.02, 0.09, 21000);
    final static Rates r2 = new Rates("14/15", 10000, 100000, 31866, 150000, 0.2, 0.4, 0.45, 153, 805, 0.12, 0.02, 0.09, 21000);

    static Rounder r = new Rounder(2);

    final static Form<UserFormData> userForm = form(UserFormData.class);

    // initialise values to be returned in form to zero
    static BigDecimal annualSalary = r.round(0);
    static BigDecimal monthlySalary = r.monthly(annualSalary);
    static BigDecimal weeklySalary = r.weekly(annualSalary);

    static BigDecimal annualIncomeTax = r.round(0);
    static BigDecimal monthlyIncomeTax = r.monthly(annualIncomeTax);
    static BigDecimal weeklyIncomeTax = r.weekly(annualIncomeTax);

    static BigDecimal annualNI = r.round(0);
    static BigDecimal monthlyNI = r.monthly(annualNI);
    static BigDecimal weeklyNI = r.weekly(annualNI);

    static BigDecimal annualStudentLoan = r.round(0);
    static BigDecimal monthlyStudentLoan = r.monthly(annualStudentLoan);
    static BigDecimal weeklyStudentLoan = r.weekly(annualStudentLoan);

    static BigDecimal annualTakeHome = annualSalary.add(annualIncomeTax.negate()).add(annualNI.negate()).add(annualStudentLoan.negate());
    static BigDecimal monthlyTakeHome = monthlySalary.add(monthlyIncomeTax.negate()).add(monthlyNI.negate()).add(monthlyStudentLoan.negate());
    static BigDecimal weeklyTakeHome = weeklySalary.add(weeklyIncomeTax.negate()).add(weeklyNI.negate()).add(weeklyStudentLoan.negate());

    /**
     * Display a blank form.
     */
    public static Result blank() {

        FY.addFY(r1);
        FY.addFY(r2);
        return ok(index.render(userForm,
                annualSalary,
                monthlySalary,
                weeklySalary,
                annualIncomeTax,
                monthlyIncomeTax,
                weeklyIncomeTax,
                annualNI,
                monthlyNI,
                weeklyNI,
                annualStudentLoan,
                monthlyStudentLoan,
                weeklyStudentLoan,
                annualTakeHome,
                monthlyTakeHome,
                weeklyTakeHome));
    }

    /**
     * Handle the form submission.
     */
    public static Result submit() {
        Form<UserFormData> filledForm = userForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest(index.render(filledForm,
                    annualSalary,
                    monthlySalary,
                    weeklySalary,
                    annualIncomeTax,
                    monthlyIncomeTax,
                    weeklyIncomeTax,
                    annualNI,
                    monthlyNI,
                    weeklyNI,
                    annualStudentLoan,
                    monthlyStudentLoan,
                    weeklyStudentLoan,
                    annualTakeHome,
                    monthlyTakeHome,
                    weeklyTakeHome));
        } else {
            UserFormData userForm = filledForm.get();

            FiscalYear_ FY = new FiscalYear_();
            Rates rates = FY.getFYByName(userForm.getFiscalYear());
            Rounder r = new Rounder(2);
            TaxCalculator tx = new TaxCalculator(userForm, rates, r);
            NICalculator ni = new NICalculator(userForm, rates, r);
            StudentLoanCalculator sl = new StudentLoanCalculator(userForm, rates, r);

            annualSalary = userForm.getSalary();
            monthlySalary = r.monthly(annualSalary);
            weeklySalary = r.weekly(annualSalary);

            annualIncomeTax = tx.getTax();
            monthlyIncomeTax = r.monthly(annualIncomeTax);
            weeklyIncomeTax = r.weekly(annualIncomeTax);

            annualNI = ni.getNI();
            monthlyNI = r.monthly(annualNI);
            weeklyNI = r.weekly(annualNI);

            annualStudentLoan = sl.getStudentLoan();
            monthlyStudentLoan = r.monthly(annualStudentLoan);
            weeklyStudentLoan = r.weekly(annualStudentLoan);

            annualTakeHome = annualSalary.add(annualIncomeTax.negate()).add(annualNI.negate()).add(annualStudentLoan.negate());
            monthlyTakeHome = monthlySalary.add(monthlyIncomeTax.negate()).add(monthlyNI.negate()).add(monthlyStudentLoan.negate());
            weeklyTakeHome = weeklySalary.add(weeklyIncomeTax.negate()).add(weeklyNI.negate()).add(weeklyStudentLoan.negate());

            return ok(index.render(filledForm,
                                    annualSalary,
                                    monthlySalary,
                                    weeklySalary,
                                    annualIncomeTax,
                                    monthlyIncomeTax,
                                    weeklyIncomeTax,
                                    annualNI,
                                    monthlyNI,
                                    weeklyNI,
                                    annualStudentLoan,
                                    monthlyStudentLoan,
                                    weeklyStudentLoan,
                                    annualTakeHome,
                                    monthlyTakeHome,
                                    weeklyTakeHome));
        }
    }
}
