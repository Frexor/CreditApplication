package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("familyMembers should be sorted by age desc")
    public void test() {
        //given
        FamilyMember grzegorz = new FamilyMember("Grzegorz", 18);
        FamilyMember janina = new FamilyMember("Janina", 40);
        FamilyMember ola = new FamilyMember("Ola", 5);
        List<FamilyMember> familyMembers = Arrays.asList(grzegorz, janina, ola);

    //when
        Person person = NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .build();

        //then
        assertNotNull(person.getFamilyMembers());
        assertTrue(person.getFamilyMembers().size() == 3);
        assertEquals(janina, person.getFamilyMembers().get(0));
        assertEquals(grzegorz, person.getFamilyMembers().get(1));
        assertEquals(ola, person.getFamilyMembers().get(2));
    }

}