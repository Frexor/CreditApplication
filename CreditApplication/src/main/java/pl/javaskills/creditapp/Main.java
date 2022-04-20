package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.CreditApplicationReader;
import pl.javaskills.creditapp.client.DummyCreditApplicationReader;
import pl.javaskills.creditapp.core.CreditApplicationManager;
import pl.javaskills.creditapp.core.validation.CompoundPostValidator;
import pl.javaskills.creditapp.core.validation.ExpansesPostValidator;
import pl.javaskills.creditapp.core.validation.ObjectValidator;
import pl.javaskills.creditapp.core.validation.PurposeOfLoanPostValidator;
import pl.javaskills.creditapp.core.validation.reflection.*;
import pl.javaskills.creditapp.di.ClassInitializer;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        final ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);

        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpansesPostValidator());
        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);

        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);
        manager.add(reader.read());

        manager.startProcessing();
    }
}
