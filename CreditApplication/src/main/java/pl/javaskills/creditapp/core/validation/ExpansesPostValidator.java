package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.ExpenseType;

public class ExpansesPostValidator implements PostValidator {
    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        double personalExpanses = creditApplication.getPerson().getFinanceData().getSumOfExpenses(ExpenseType.PERSONAL);

        double percentage = personalExpanses * 100 / balance;

        if (percentage > 40) {
            throw new RequirementNotMetException(RequirementNotMetCause.TOO_HIGH_PERSONAL_EXPENSES);
        }
    }
}
