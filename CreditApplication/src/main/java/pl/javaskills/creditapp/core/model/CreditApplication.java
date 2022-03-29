package pl.javaskills.creditapp.core.model;

import java.util.Optional;
import java.util.UUID;

public class CreditApplication {
    private final UUID id;
    private final Person person;
    private final PurposeOfLoan purposeOfLoan;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public Person getPerson() {
        return person;
    }
}