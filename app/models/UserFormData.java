package models;

import com.taxman.model.Rounder;
import play.data.validation.Constraints;
import java.math.BigDecimal;

/**
 * Created by rory.payne on 25/03/14.
 */
public class UserFormData {

    @Constraints.Required
    public String fiscalYear;

    @Constraints.Required
    public BigDecimal salary;

    public boolean studentLoan;

    public UserFormData() {

    }

    public UserFormData(String fiscalYear, String salary, String studentLoan) {
        Rounder r = new Rounder(2);
        this.fiscalYear = fiscalYear;
        this.salary = r.round(Double.parseDouble(salary));
        if (studentLoan == "checked") {
            this.studentLoan=true;
        } else {
            this.studentLoan=false;
        }
    }

    public boolean isStudentLoan() {
        return studentLoan;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
