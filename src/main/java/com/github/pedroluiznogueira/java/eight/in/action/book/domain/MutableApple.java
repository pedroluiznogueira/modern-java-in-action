package com.github.pedroluiznogueira.java.eight.in.action.book.domain;

import lombok.Data;

@Data
public class MutableApple {
    private String color;
    private Double weight;

    public MutableApple(final String color) {
        this.color = color;
    }

    public MutableApple(final String color, final Double weight) {
        this.color = color;
        this.weight = weight;
    }
}
