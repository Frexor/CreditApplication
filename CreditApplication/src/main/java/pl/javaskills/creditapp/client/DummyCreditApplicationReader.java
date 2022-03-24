package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

import java.util.Arrays;
import java.util.List;

public class DummyCreditApplicationReader implements CreditApplicationReader {
    @Override
    public CreditApplication read() {
        FamilyMember maciej = new FamilyMember("Maciej", 28);
        FamilyMember julka = new FamilyMember("Julka", 18);
        FamilyMember irena = new FamilyMember("Irena", 42);
        List<FamilyMember> familyMembers = Arrays.asList(maciej, julka, irena);

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);


        System.out.println(person.getFamilyMembersSortedByName());
        System.out.println(person.getFamilyMembers());
        return creditApplication;
    }
}