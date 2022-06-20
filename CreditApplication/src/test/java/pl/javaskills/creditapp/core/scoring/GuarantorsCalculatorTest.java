package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.*;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.javaskills.creditapp.util.AgeUtils.generateBirthDate;

class GuarantorsCalculatorTest {
    private GuarantorsCalculator cut = new GuarantorsCalculator();

    @Test
    public void test1() {
        //given
        NaturalPerson person = createNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45645645645", generateBirthDate(18)));
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);

        //when
        int scoring = cut.calculate(creditApplication);

        //then
        assertEquals(50, scoring);
    }

    @Test
    public void test2() {
        //given
        NaturalPerson person = createNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45645645645", generateBirthDate(18)),
                new Guarantor("45645645646", generateBirthDate(41)));
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);

        //when
        int scoring = cut.calculate(creditApplication);

        //then
        assertEquals(75, scoring);
    }

    private NaturalPerson createNaturalPerson() {
        return NaturalPerson.Builder
                .create()
                .withFamilyMembers(new ArrayList<>())
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();
    }

}