package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidateCollection;
import pl.javaskills.creditapp.core.annotation.ValidateObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class CreditApplication {
    @NotNull
    private final UUID id;

    private final ZoneId clientTimeZone;

    private final ZonedDateTime creationDateClientZone;
    @NotNull
    @ValidateObject
    private final Person person;
    @NotNull
    @ValidateObject
    private final PurposeOfLoan purposeOfLoan;
    @NotNull
    @ValidateCollection
    private final Set<Guarantor> guarantors;

    public CreditApplication(ZoneId clientTimeZone, Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>();
    }

    public ZoneId getClientTimeZone() {
        return clientTimeZone;
    }

    public ZonedDateTime getCreationDateClientZone() {
        return creationDateClientZone;
    }

    public CreditApplication(ZoneId clientTimeZone, Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>(guarantors);
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
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
