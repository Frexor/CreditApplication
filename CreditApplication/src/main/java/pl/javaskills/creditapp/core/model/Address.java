package pl.javaskills.creditapp.core.model;

import java.util.Objects;

public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
    private final String state;
    private final String houseNumber;


    public Address(String street, String city, String zipCode, String state, String houseNumber) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        Address address = (Address) o;
        return street.equalsIgnoreCase(address.street) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(state, address.state) && Objects.equals(houseNumber, address.houseNumber);
    }

}
