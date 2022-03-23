package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PurposeOfLoan;

public class PurposeOfLoanValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final PurposeOfLoan purposeOfLoan = creditApplication.getPurposeOfLoan();

        ValidationUtils.validateNotNull("purposeOfLoanType", purposeOfLoan.getPurposeOfLoanType());
        ValidationUtils.validateMinValue("purposeOfLoanAmount", 0.0, purposeOfLoan.getAmount());
    }
}
