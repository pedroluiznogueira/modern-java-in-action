package com.github.pedroluiznogueira.java.eight.in.action.book.chapterthree;

import com.github.pedroluiznogueira.java.eight.in.action.book.AbstractUnitTest;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.ComplexObject;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.First;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.MutableRGB;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Second;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Third;
import com.github.pedroluiznogueira.java.eight.in.action.book.support.TriFunction;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChapterThreeTest extends AbstractUnitTest {

    @Test
    public void processFirstLineOfFile() throws IOException {
        // arrange
        final String firstLine = "hello";
        final ChapterThree.BufferedReaderProcessor processor = BufferedReader::readLine;

        // act
        final String actualResult = ChapterThree.processFile(processor);

        // assert
        assertThat(actualResult)
                .isEqualTo(firstLine);
    }

    @Test
    public void processTwoLinesOfFile() throws IOException {
        // arrange
        final String firstLine = "hello";
        final String secondLine = "world";
        final ChapterThree.BufferedReaderProcessor processor = bufferedReader -> bufferedReader.readLine() + bufferedReader.readLine();

        // act
        final String actualResult = ChapterThree.processFile(processor);

        // assert
        assertThat(actualResult)
                .isEqualTo(firstLine + secondLine);
    }

    @Test
    public void filterNonEmptyNames() {
        // arrange
        final List<String> names = getNames();
        final int expectedSize = 4;

        // act
        final Stream<String> actualResult = ChapterThree.filter(names, name -> !name.isEmpty());
        final List<String> nonEmptyNames = actualResult.collect(Collectors.toList());

        // assert
        assertThat(nonEmptyNames)
                .hasSize(expectedSize);
    }

    @Test
    public void filterNonEmptyNames_NegatingEmptyNames() {
        // arrange
        final List<String> names = getNames();
        final Predicate<String> predicate = String::isEmpty;
        final int expectedSize = 4;

        // act
        final Stream<String> actualResult = ChapterThree.filter(names, predicate.negate());
        final List<String> nonEmptyNames = actualResult.collect(Collectors.toList());

        // assert
        assertThat(nonEmptyNames)
                .hasSize(expectedSize);
    }

    @Test
    public void filterNumbersLessThanFour_WithIntPredicate() {
        // arrange
        final IntStream numbers = getNumbers();
        final IntPredicate intPredicate = number -> number < 4;
        final int expectedCount = 3;

        // act
        final IntStream actualResult = ChapterThree.filter(numbers, intPredicate);

        // assert
        assertThat(actualResult)
                .hasSize(expectedCount);
    }

    @Test
    public void displayNames() {
        // arrange
        final List<String> names = getNames();
        final Consumer<String> consumer = System.out::println;

        // act
        ChapterThree.forEach(names, consumer);
    }

    @Test
    public void mapNamesToItsLength() {
        // arrange
        final List<String> names = getNames();
        final Function<String, Integer> function = String::length;

        // act
        final Stream<Integer> actualResult = ChapterThree.map(names, function);
        final List<Integer> namesLength = actualResult.collect(Collectors.toList());

        // assert
        namesLength.forEach(length -> {
            final var nameIndex = namesLength.indexOf(length);
            final var name = names.get(nameIndex);
            final var nameLength = name.length();

            assertThat(nameLength)
                    .isEqualTo(length);
        });
    }

    @Test
    public void combineLists() {
        // arrange
        final List<String> names = getNames();
        final List<Integer> numbers = getNumbers().boxed().collect(Collectors.toList());
        final BiFunction<String, Integer, String> biFunction = (name, number) -> name + number;

        // act
        final List<String> combinedList = ChapterThree.listCombiner(names, numbers, biFunction);

        // assert
        combinedList.forEach(combinedItem -> {
            final var nameIndex = combinedList.indexOf(combinedItem);
            final var numberIndex = combinedList.indexOf(combinedItem);
            final var name = names.get(nameIndex);
            final var number = numbers.get(numberIndex);
            final var combined = name + number;

            assertThat(combinedItem)
                    .isEqualTo(combined);
        });
    }

    @Test
    public void capturingLambdas() {
        // act
        final Runnable runnable = ChapterThree.capturingLambdas();
        runnable.run();
    }

    @Test
    public void multiParameterConstructorMethodReference() {
        // arrange
        final String first = "red";
        final String second = "green";
        final String third = "blue";
        final TriFunction<String, String, String, MutableRGB> function = MutableRGB::new;

        // act
        final MutableRGB actualResult = ChapterThree.multiParameterConstructorMethodReference(function, first, second, third);

        // assert
        assertThat(actualResult)
                .isInstanceOf(MutableRGB.class);
    }

    @Test
    public void mapComplexObject() {
        // arrange
        final ComplexObject complexObject = new ComplexObject();
        final Function<ComplexObject, First> getFirst = ComplexObject::getFirst;
        final Function<ComplexObject, String> function = getFirst
                .andThen(First::getSecond)
                .andThen(Second::getThird)
                .andThen(Third::getEnd);
        final String expectedResult = "end";

        // act
        final String actualResult = ChapterThree.mapComplexObject(complexObject, function);

        // assert
        assertThat(actualResult)
                .isEqualTo(expectedResult);
    }

    @Test
    public void sortApples_ChainingComparators() {
        // arrange
        final List<Apple> apples = getApples();
        final Comparator<Apple> comparator = Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor);

        // act
        final Stream<Apple> actualResult = ChapterThree.sortApples(apples, comparator);

        // assert
        assertThat(actualResult)
                .isNotNull();
    }

    @Test
    public void filterApples_ComposingPredicates() {
        // arrange
        final List<Apple> apples = getApples();
        final Predicate<Apple> redApplesPredicate = apple -> apple.getColor().equals(Colors.RED.getColor());
        final Predicate<Apple> heavyApplesPredicate = apple -> apple.getWeight() > 150D;
        final Predicate<Apple> greenApplesPredicate = apple -> apple.getColor().equals(Colors.GREEN.getColor());
        final Predicate<Apple> predicate = redApplesPredicate.and(heavyApplesPredicate).or(greenApplesPredicate);

        // act
        final Stream<Apple> actualResult = ChapterThree.filterApples(apples, predicate);

        // assert
        assertThat(actualResult)
                .isNotNull();
    }

    @Test
    public void mapNumbers_ComposingFunctions_Gof() {
        // arrange
        final List<Integer> numbers = getNumbers().boxed().collect(Collectors.toList());
        final Function<Integer, Integer> f = number -> number + 1;
        final Function<Integer, Integer> g = number -> number * 2;
        final Function<Integer, Integer> gof = f.andThen(g);

        // act
        final Stream<Integer> actualResult = ChapterThree.mapNumbers(numbers, gof);

        // assert
        assertThat(actualResult)
                .isNotNull();
    }

    @Test
    public void mapNumbers_ComposingFunctions_Fog() {
        // arrange
        final List<Integer> numbers = getNumbers().boxed().collect(Collectors.toList());
        final Function<Integer, Integer> f = number -> number + 1;
        final Function<Integer, Integer> g = number -> number * 2;
        final Function<Integer, Integer> gof = f.compose(g);

        // act
        final Stream<Integer> actualResult = ChapterThree.mapNumbers(numbers, gof);

        // assert
        assertThat(actualResult)
                .isNotNull();
    }
}
