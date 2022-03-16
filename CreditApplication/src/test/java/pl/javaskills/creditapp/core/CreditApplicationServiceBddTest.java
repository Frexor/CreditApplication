package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

class CreditApplicationServiceBddTest {

    NaturalPersonScoringCalculator naturalPersonScoringCalculator = new NaturalPersonScoringCalculator(new EducationCalculator(), new MaritalStatusCalculator(), new IncomeCalculator());
    SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator(new EducationCalculator(), new MaritalStatusCalculator(), new IncomeCalculator());
    PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(naturalPersonScoringCalculator, selfEmployedScoringCalculator);
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator());

    @Test
    @DisplayName("should return Decision is NEGATIVE_REQUIREMENTS_NOT_MET, minimum loan amount requirement is not met")
    public void test1() {
        //given
        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(PersonalData.Builder.create()
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .withNumberOfDependants(2)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person,purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertEquals(600, decision.getScoring());
        assertEquals(360000.00, decision.getCreditRate());

    }

    @Test
    @DisplayName("should return Decision is negative, when years since founded <2")
    public void test2() {
        //given
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(PersonalData.Builder.create()
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .withNumberOfDependants(2)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withYearsSinceFounded(1)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person,purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getType());
        assertEquals(200, decision.getScoring());

    }

    @Test
    @DisplayName("should return Decision is contact required, when years since founded >=2")
    public void test3() {
        //given
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(PersonalData.Builder.create()
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .withNumberOfDependants(2)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person,purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getType());
        assertEquals(400, decision.getScoring());

    }
}