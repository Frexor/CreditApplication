package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;

public class PersonScoringCalculatorFactory {
    private final NaturalPersonScoringCalculator naturalPersonScoringCalculator;
    private final SelfEmployedScoringCalculator selfEmployedScoringCalculator;

    public PersonScoringCalculatorFactory(NaturalPersonScoringCalculator naturalPersonScoringCalculator, SelfEmployedScoringCalculator selfEmployedScoringCalculator) {
        this.naturalPersonScoringCalculator = naturalPersonScoringCalculator;
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
    }

    public PersonScoringCalculator getCalculator(Person person){
        if (person instanceof NaturalPerson){
            return naturalPersonScoringCalculator;
        }else if (person instanceof SelfEmployed){
            return selfEmployedScoringCalculator;
        }
        return null;
    }
}
