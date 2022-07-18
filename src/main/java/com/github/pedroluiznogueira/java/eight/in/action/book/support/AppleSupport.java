package com.github.pedroluiznogueira.java.eight.in.action.book.support;

import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
@Component
public class AppleSupport {

    public Integer getColorsLength(final Apple apple) {
        return apple.getColor().length();
    }
}
