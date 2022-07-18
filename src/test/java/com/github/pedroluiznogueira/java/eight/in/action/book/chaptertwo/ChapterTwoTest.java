package com.github.pedroluiznogueira.java.eight.in.action.book.chaptertwo;

import com.github.pedroluiznogueira.java.eight.in.action.book.AbstractUnitTest;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChapterTwoTest extends AbstractUnitTest {

    @Test
    public void filterApples() {
        // arrange
        final Predicate<Apple> predicate = apple -> apple.getColor().equals("green");
        final int expectedSize = 3;

        // act
        final List<Apple> filteredApples = ChapterTwo.filterApples(getApples(), predicate);

        // assert
        assertThat(filteredApples)
                .hasSize(expectedSize);
    }

    @Test
    public void getApplesColors() {
        // arrange
        final Function<Apple, String> function = Apple::getColor;
        final List<String> possibleColors = List.of(
                AbstractUnitTest.Colors.GREEN.getColor(),
                AbstractUnitTest.Colors.RED.getColor());

        // act
        final Stream<String> applesColors = ChapterTwo.getApplesColors(getApples(), function);
        final boolean containsColors = applesColors
                .collect(Collectors.toList())
                .containsAll(possibleColors);

        // act
        assertThat(containsColors)
                .isTrue();
    }

    @Test
    public void getApplesInAscendingOrder() {
        // arrange
        final Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);

        // act
        final Stream<Apple> applesColors = ChapterTwo.getApplesInAscendingOrder(getApples(), comparator);

        // assert
        final Consumer<Double> consumer = weight -> assertThat(weight).isLessThan(150D);
        applesColors
                .findFirst()
                .map(Apple::getWeight)
                .ifPresent(consumer);
    }
}
