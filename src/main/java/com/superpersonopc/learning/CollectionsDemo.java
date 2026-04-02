package com.superpersonopc.learning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsDemo {

    public List<String> uniqueNamesPreservingOrder(List<String> names) {
        if (names == null) {
            throw new IllegalArgumentException("names must not be null");
        }

        Set<String> seen = new HashSet<>();
        List<String> unique = new ArrayList<>();
        for (String name : names) {
            if (seen.add(name)) {
                unique.add(name);
            }
        }
        return unique;
    }
}
