package com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.utils;

import com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.domain.Apple;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChapterTwoUtils {

    @Getter
    public enum Colors {
        GREEN("green"),
        RED("red");

        private final String color;

        Colors(String color) {
            this.color = color;
        }
    }

    public static List<Apple> generateApples() {
        return List.of(
                Apple.builder().color(Colors.GREEN.getColor()).weight(150D).build(),
                Apple.builder().color(Colors.GREEN.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.GREEN.getColor()).weight(150D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(150D).build());
    }
}
