package com.superpersonopc.learning;

import java.util.List;

public class App {
    public static void main(String[] args) {
        BasicsDemo basics = new BasicsDemo();
        Student student = new Student("Alex", 20, "Webex University");
        CollectionsDemo collections = new CollectionsDemo();

        System.out.println("== Java Learning Demo ==");
        System.out.println("2 + 3 = " + basics.sum(2, 3));
        System.out.println("Is 8 even? " + basics.isEven(8));
        System.out.println("Reverse of 'Java': " + basics.reverse("Java"));
        System.out.println(student.describe());
        System.out.println("Unique names: " + collections.uniqueNamesPreservingOrder(
                List.of("Ana", "Bob", "Ana", "Chris", "Bob")));
    }
}
