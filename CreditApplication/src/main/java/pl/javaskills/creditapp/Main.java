package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.SelfEmployed;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {

    public static void main(String[] args) {
        NaturalPersonScoringCalculator naturalPersonScoringCalculator = new NaturalPersonScoringCalculator(new EducationCalculator(), new MaritalStatusCalculator(), new IncomeCalculator());
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator(new EducationCalculator(), new MaritalStatusCalculator(), new IncomeCalculator());
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(naturalPersonScoringCalculator, selfEmployedScoringCalculator);
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator());
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        CreditApplicationDecision decision = service.getDecision(creditApplication);

        System.out.println(decision.getDecisionString());
    }
}
