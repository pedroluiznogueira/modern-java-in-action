package com.github.pedroluiznogueira.java.eight.in.action.book.domain;

import lombok.Data;

@Data
public class First {
    private Second second = new Second();
}
