package pl.javaskills.creditapp.core.model;

public class PersonTestFactory {

    public static NaturalPerson create(int numOfDependants, SourceOfIncome... sourcesOfIncome) {
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withNumberOfDependants(numOfDependants)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFinanceData(new FinanceData(sourcesOfIncome))
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withNumberOfDependants(2)
                .withMaritalStatus(maritalStatus)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(Education education) {
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(education)
                .withNumberOfDependants(2)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create() {
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withNumberOfDependants(2)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(education)
                .withNumberOfDependants(numOfDependants)
                .withMaritalStatus(maritalStatus)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT,totalMonthlyIncomeInPln)))
                .withPersonalData(personalData)
                .build();
    }
}
