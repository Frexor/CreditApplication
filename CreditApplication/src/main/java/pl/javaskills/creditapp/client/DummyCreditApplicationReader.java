package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.PurposeOfLoan;
import pl.javaskills.creditapp.core.model.PurposeOfLoanType;

public class DummyCreditApplicationReader implements CreditApplicationReader {
    @Override
    public CreditApplication read() {
        return new CreditApplication(NaturalPerson.Builder.create().build(),
                new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 10000, 30));
    }
}