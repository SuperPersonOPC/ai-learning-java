package com.superpersonopc.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class CollectionsDemoTest {

    private final CollectionsDemo collectionsDemo = new CollectionsDemo();

    @Test
    void uniqueNamesPreservingOrder_removesDuplicatesAndKeepsFirstSeenOrder() {
        List<String> input = List.of("Ana", "Bob", "Ana", "Chris", "Bob");
        List<String> result = collectionsDemo.uniqueNamesPreservingOrder(input);
        assertEquals(List.of("Ana", "Bob", "Chris"), result);
    }

    @Test
    void uniqueNamesPreservingOrder_throwsWhenInputNull() {
        assertThrows(IllegalArgumentException.class,
                () -> collectionsDemo.uniqueNamesPreservingOrder(null));
    }
}
