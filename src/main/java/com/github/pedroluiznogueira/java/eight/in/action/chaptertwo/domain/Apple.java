package com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Apple {
    String color;
    Double weight;
}
