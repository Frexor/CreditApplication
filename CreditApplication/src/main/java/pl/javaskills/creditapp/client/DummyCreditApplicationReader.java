package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.util.AgeUtils;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static pl.javaskills.creditapp.util.AgeUtils.generateBirthDate;

public class DummyCreditApplicationReader implements CreditApplicationReader {
    @Override
    public CreditApplication read() {
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
        CreditApplication creditApplication = new CreditApplication(new Locale("pl", "PL"),
                ZoneId.of("Europe/Warsaw"), person, purposeOfLoan, guarantorSet);
        return creditApplication;
    }
}
