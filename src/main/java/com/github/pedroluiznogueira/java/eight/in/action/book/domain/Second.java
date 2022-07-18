package com.github.pedroluiznogueira.java.eight.in.action.book.domain;

import lombok.Data;

@Data
public class Second {
    private Third third = new Third();
}
