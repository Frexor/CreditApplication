package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDataTest {

    @Test
    @DisplayName("should set Optional.empty correspondence address, when homeAddress is the same")
    public void test1(){
        //given & when
        ContactData contactData = ContactData.Builder
                .create()
                .withCorrespondenceAddress(new Address("Rumuńska", "Białystok", "15-011", "Podlaskie", "69"))
                .withHomeAddress(new Address("Rumuńska", "Białystok", "15-011", "Podlaskie", "69"))
                .build();

        //then
        assertTrue(contactData.getCorrespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("should set Optional.of correspondence address, when homeAddress is NOT the same")
    public void test2(){
        //given
        final Address correspondenceAddress = new Address("Porzeczkowa", "Krynki", "16-690", "Podlaskie", "69");
        final Address homeAddress = new Address("Rumuńska", "Białystok", "15-011", "Podlaskie", "69");

        //when
        ContactData contactData = ContactData.Builder
                .create()
                .withCorrespondenceAddress(correspondenceAddress)
                .withHomeAddress(homeAddress)
                .build();

        //then
        assertTrue(contactData.getCorrespondenceAddress().isPresent());
        assertEquals(correspondenceAddress, contactData.getCorrespondenceAddress().get());

    }
}