package pl.javaskills.creditapp.core.validation;

import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static pl.javaskills.creditapp.core.model.CreditApplicationTestFactory.CLIENT_TIME_ZONE;
import static pl.javaskills.creditapp.util.AgeUtils.generateBirthDate;

class CreditApplicationValidatorTest {
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
    private CreditApplicationValidator cut = new CreditApplicationValidator(objectValidator);

    @Test
    public void test() throws ValidationException {
        //given
        final FamilyMember john = new FamilyMember("Beatrice", generateBirthDate(18));
        final FamilyMember jane = new FamilyMember("Jane", generateBirthDate(40));
        final FamilyMember susie = new FamilyMember("Susie", generateBirthDate(5));
        List<FamilyMember> familyMembers = Arrays.asList(john,
                jane,
                susie);

        NaturalPerson person = NaturalPerson.Builder
                .create()

                .withPesel("12341234123")
                .withFamilyMembers(familyMembers)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withContactData(ContactData.Builder.create()
                        .withEmail("test@test")
                        .withPhoneNumber("456456456")
                        .withHomeAddress(new Address("test", "test", "test", "test", "test"))
                        .withCorrespondenceAddress(new Address("test", "test", "test", "test", "test"))
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("12341234123", generateBirthDate(18)),
                new Guarantor("12341234124", generateBirthDate(41)));
        CreditApplication creditApplication = new CreditApplication(Locale.US, CLIENT_TIME_ZONE, person, purposeOfLoan, guarantorSet);
        //when
        cut.validate(creditApplication);
    }
}