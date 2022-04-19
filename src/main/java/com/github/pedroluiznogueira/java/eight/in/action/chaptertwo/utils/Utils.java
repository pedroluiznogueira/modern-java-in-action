package com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.utils;

import com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.domain.Apple;

import java.util.List;

public class Utils {

    public static List<Apple> getApples() {
        return List.of(
                Apple.builder().color("green").weight(150D).build(),
                Apple.builder().color("green").weight(100D).build(),
                Apple.builder().color("green").weight(150D).build(),
                Apple.builder().color("red").weight(100D).build(),
                Apple.builder().color("red").weight(100D).build(),
                Apple.builder().color("red").weight(100D).build(),
                Apple.builder().color("red").weight(150D).build());
    }
}
