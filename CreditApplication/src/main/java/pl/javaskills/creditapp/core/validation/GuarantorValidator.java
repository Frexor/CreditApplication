package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;

import java.util.Set;

import static pl.javaskills.creditapp.core.Constants.PESEL_REGEX;

public class GuarantorValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final Set<Guarantor> guarantorSet = creditApplication.getGuarantors();
        for (Guarantor g : guarantorSet) {
            ValidationUtils.validateNotNull("guarantorPesel", g.getPesel());
            ValidationUtils.validateRegex("guarantorPesel", g.getPesel(), PESEL_REGEX);
            ValidationUtils.validateMinValue("guarantorAge", 0, g.getAge());
        }

    }
}
