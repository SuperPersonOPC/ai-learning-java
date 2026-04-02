package com.superpersonopc.learning;

import java.util.Objects;

public class Student extends Person {
    private final String school;

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = Objects.requireNonNull(school, "school must not be null");
        if (this.school.isBlank()) {
            throw new IllegalArgumentException("school must not be blank");
        }
    }

    public String getSchool() {
        return school;
    }

    public String describe() {
        return "%s is %d and studies at %s".formatted(getName(), getAge(), school);
    }
}
