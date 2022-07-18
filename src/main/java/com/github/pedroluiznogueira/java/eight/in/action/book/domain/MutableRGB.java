package com.github.pedroluiznogueira.java.eight.in.action.book.domain;

import lombok.Data;

@Data
public class MutableRGB {
    private String first;
    private String second;
    private String third;

    public MutableRGB(final String first,
                      final String second,
                      final String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
