package com.github.pedroluiznogueira.java.eight.in.action.chaptertwo;

import com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.domain.Apple;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.pedroluiznogueira.java.eight.in.action.chaptertwo.utils.Utils.getApples;
import static java.util.stream.Collectors.toList;

public class ChapterTwo {

    /**
    * Behavior parameterization
    *
    * */
    public static void main(String[] args) {
        // filter apples
        final Predicate<Apple> predicate = apple -> apple.getColor().equals("green");
        filterApples(getApples(), predicate).forEach(System.out::println);

        // print apple
        final Function<Apple, String> function = Apple::getColor;
        printApple(getApples(), function);

        // displaying apples in ascending order
        final Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
        displayAscending(getApples(), comparator);
    }

    public static List<Apple> filterApples(final List<Apple> apples, final Predicate<Apple> predicate) {
        return apples.stream()
                .filter(predicate)
                .collect(toList());
    }

    public static <T> void printApple(final List<Apple> apples, final Function<Apple, T> function) {
        apples.stream().map(function).forEach(System.out::println);
    }

    public static void displayAscending(final List<Apple> apples, final Comparator<Apple> comparator) {
        apples.stream().sorted(comparator).forEach(System.out::println);
    }
}

