package com.superpersonopc.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicsDemoTest {

    private final BasicsDemo basics = new BasicsDemo();

    @Test
    void sum_shouldAddTwoNumbers() {
        assertEquals(7, basics.sum(3, 4));
    }

    @Test
    void isEven_shouldReturnTrueForEvenNumbers() {
        assertTrue(basics.isEven(10));
        assertFalse(basics.isEven(7));
    }

    @Test
    void reverse_shouldReverseGivenText() {
        assertEquals("avaJ", basics.reverse("Java"));
    }

    @Test
    void reverse_shouldRejectNullInput() {
        assertThrows(IllegalArgumentException.class, () -> basics.reverse(null));
    }
}
