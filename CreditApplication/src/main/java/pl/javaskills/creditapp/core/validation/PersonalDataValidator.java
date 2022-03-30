package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PersonalData;

import static pl.javaskills.creditapp.core.Constants.LAST_NAME_REGEX;
import static pl.javaskills.creditapp.core.Constants.NAME_REGEX;

public class PersonalDataValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateNotNull("lastName", personalData.getLastName());
        ValidationUtils.validateRegex("lastName", personalData.getLastName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("name", personalData.getLastName());
        ValidationUtils.validateRegex("name", personalData.getLastName(), NAME_REGEX);

        ValidationUtils.validateNotNull("mothersMaidenName", personalData.getMothersMaidenName());
        ValidationUtils.validateRegex("mothersMaidenName", personalData.getLastName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("education", personalData.getEducation());
        ValidationUtils.validateNotNull("maritalStatus", personalData.getMaritalStatus());
    }
}
