package com.superpersonopc.learning;

public class BasicsDemo {

    public int sum(int first, int second) {
        return first + second;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public String reverse(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        return new StringBuilder(value).reverse().toString();
    }
}
