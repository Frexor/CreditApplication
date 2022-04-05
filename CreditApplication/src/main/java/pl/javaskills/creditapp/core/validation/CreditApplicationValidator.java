package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class CreditApplicationValidator implements Validator {
    private final ObjectValidator objectValidator;

    public CreditApplicationValidator(ObjectValidator objectValidator) {
        this.objectValidator = objectValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        try {
            objectValidator.validate(creditApplication);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
