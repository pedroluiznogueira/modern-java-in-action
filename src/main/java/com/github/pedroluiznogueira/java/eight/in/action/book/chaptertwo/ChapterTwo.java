package com.github.pedroluiznogueira.java.eight.in.action.book.chaptertwo;

import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Behavior parameterization
 *
 * */
public class ChapterTwo {

    public static List<Apple> filterApples(final List<Apple> apples, final Predicate<Apple> predicate) {
        return apples.stream()
                .filter(predicate)
                .collect(toList());
    }

    public static <T> Stream<T> getApplesColors(final List<Apple> apples, final Function<Apple, T> function) {
        return apples.stream().map(function);
    }

    public static Stream<Apple> getApplesInAscendingOrder(final List<Apple> apples, final Comparator<Apple> comparator) {
        return apples.stream().sorted(comparator);
    }
}

