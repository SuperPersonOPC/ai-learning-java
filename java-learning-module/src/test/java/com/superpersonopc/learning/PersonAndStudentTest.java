package com.superpersonopc.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonAndStudentTest {

    @Test
    void personBirthdayShouldIncreaseAge() {
        Person person = new Person("Sam", 30);

        person.birthday();

        assertEquals(31, person.getAge());
    }

    @Test
    void studentDescribeShouldContainKeyFields() {
        Student student = new Student("Nina", 21, "Cisco Academy");

        String description = student.describe();

        assertEquals("Nina is 21 and studies at Cisco Academy", description);
    }

    @Test
    void personShouldRejectNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Jill", -1));
    }
}
