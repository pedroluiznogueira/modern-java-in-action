package com.github.pedroluiznogueira.java.eight.in.action.book;

import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import lombok.Getter;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.stream.IntStream;

public abstract class AbstractUnitTest {

    @Getter
    private List<Apple> apples;
    @Getter
    private List<String> names;
    @Getter
    private IntStream numbers;

    @Getter
    public enum Colors {
        GREEN("green"),
        RED("red");

        private final String color;

        Colors(final String color) {
            this.color = color;
        }
    }

    @BeforeSuite
    public void setup() {
        apples = List.of(
                Apple.builder().color(Colors.GREEN.getColor()).weight(150D).build(),
                Apple.builder().color(Colors.GREEN.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.GREEN.getColor()).weight(150D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(100D).build(),
                Apple.builder().color(Colors.RED.getColor()).weight(150D).build());
        names = List.of("pedro", "", "luiz", "nogueira", "mendes");
        numbers = IntStream.of(1, 2, 3, 4, 5);
    }
}
