package com.github.pedroluiznogueira.java.eight.in.action.book.chapterthree;

import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.ComplexObject;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.First;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.MutableApple;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.MutableRGB;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Second;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Third;
import com.github.pedroluiznogueira.java.eight.in.action.book.support.AppleSupport;
import com.github.pedroluiznogueira.java.eight.in.action.book.support.TriFunction;
import lombok.Builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <pre>
 * Lambda expressions
 *
 * Steps to pass behavior parameterization using lambdas:
 *
 * 1. Identity the common behavior
 * 2. Parameterize it as a functional interface, with its own Function Descriptor
 * 3. Pass the behavior
 * </pre>
 **/
@Builder
public class ChapterThree {
    private static final String FILE_PATH = "src/main/resources/data.txt";

    private final AppleSupport appleSupport;

    public ChapterThree(final AppleSupport appleSupport) {
        this.appleSupport = appleSupport;
    }

    /**
     * <pre>
     * Function Descriptor of the Adder functional interface:
     * (int, int) -> int
     * </pre>
     **/
    @FunctionalInterface
    interface Adder {
        int add(final int a, final int b);
    }

    /**
     * <pre>
     * Not a functional interface because it has more than one abstract method.
     * One from the parent interface and its own
     * </pre>
     **/
    interface SmartAdder extends Adder {
        int add(final double a, final double b);
    }

    /**
     * <pre>
     * Function Descriptor of the Runnable functional interface:
     * () -> void
     * </pre>
     **/
    public static void process(final Runnable runnable) {
        runnable.run();
    }

    /**
     * <pre>
     * Function Descriptor of the Callable functional interface:
     * () -> V
     * </pre>
     **/
    public static Callable<String> fetch() {
        return () -> "Tricky example";
    }

    /**
     * <pre>
     * Function Descriptor of the Predicate functional interface:
     * (T) -> boolean
     * </pre>
     **/
    public static Predicate<Apple> filter() {
        return apple -> apple.getWeight().equals(100D);
    }

    /**
     * <pre>
     * Function Descriptor of the BufferedReaderProcessor functional interface:
     * (BufferedReader) -> String
     * </pre>
     **/
    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(final BufferedReader bufferedReader) throws IOException;
    }

    public static String processFile() throws IOException {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            // stuck to only one behavior, returning only one line
            return bufferedReader.readLine();
        }
    }

    public static String processFile(final BufferedReaderProcessor processor) throws IOException {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            // we can return any kinds of behaviors
            return processor.process(bufferedReader);
        }
    }

    public static <T> Stream<T> filter(final Collection<T> collection, final Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate);
    }

    /**
     * <pre>
     * Function Descriptor of the IntPredicate functional interface:
     * (int) -> boolean
     * </pre>
     **/
    public static IntStream filter(final IntStream intStream, final IntPredicate intPredicate) {
        return intStream.filter(intPredicate);
    }

    /**
     * <pre>
     * Function Descriptor of the Consumer functional interface:
     * (T) -> void
     * </pre>
     **/
    public static <T> void forEach(final Collection<T> collection, final Consumer<T> consumer) {
        collection.forEach(consumer);
    }

    /**
     * <pre>
     * Function Descriptor of the Function functional interface:
     * (T) -> R
     * </pre>
     **/
    public static <T, R> Stream<R> map(final Collection<T> collection, final Function<T, R> function) {
        return collection.stream()
                .map(function);
    }

    public static <T, U, R> List<R> listCombiner(final List<T> firstList,
                                                 final List<U> secondList,
                                                 final BiFunction<T, U, R> combiner) {
        return firstList.stream()
                .map(item -> combiner.apply(firstList.get(firstList.indexOf(item)), secondList.get(firstList.indexOf(item))))
                .collect(Collectors.toList());
    }

    /**
     * <pre>
     * Restrictions related to local variables:
     *
     * - Instance variables are stored on the Heap
     * - Local variables are stored in the Stack
     *
     * If a lambda used in a Thread could access a local variable directly
     * then the Thread using the variable could try to access it after the
     * Thread that allocated the variable had deallocated it.
     *
     * Hence Java implements access to a free local variable as access to a
     * copy of it, rather than access to the original Stack variable.
     *
     * Capture of instance variables are fine because they live on the heap,
     * which is shared across threads.
     * </pre>
     **/
    public static Runnable capturingLambdas() {
        // capturing an effective final variable (is only assigned once)
        int portNumber = 8080;
        final Runnable runnable = () -> System.out.println(portNumber);

        // capturing a final variable
        final String name = "pedro";
        return () -> System.out.println("@" + Integer.toHexString(name.hashCode()));
    }

    /**
     * <pre>
     * Method reference to: Integer.parseInt(String s);
     * </pre>
     **/
    public static IntStream staticMethodReference(final List<String> numbers) {
        return numbers.stream()
                .mapToInt(Integer::parseInt);
    }

    /**
     * <pre>
     * Method reference to: new String().length();
     * </pre>
     **/
    public static IntStream instanceMethodReference(final List<String> names) {
        return names.stream()
                .mapToInt(String::length);
    }

    /**
     * <pre>
     * Method reference to: this.appleSupport.getColorsLength(Apple apple);
     * </pre>
     **/
    public Stream<Integer> instanceMethodReferenceFromExistingObject(final List<Apple> apples) {
        return apples.stream()
                .map(appleSupport::getColorsLength);
    }

    /**
     * <pre>
     * Method reference to: new String();
     * </pre>
     **/
    public static String emptyConstructorMethodReference(final Supplier<String> newStringSupplier) {
        return newStringSupplier.get();
    }

    /**
     * <pre>
     * Method reference to: new MutableApple(String color);
     * </pre>
     **/
    public static MutableApple oneParameterConstructorMethodReference(final Function<String, MutableApple> newAppleFunction, final String color) {
        return newAppleFunction.apply(color);
    }

    /**
     * <pre>
     * Method reference to: new MutableApple(String color, Double weight);
     * </pre>
     **/
    public static MutableApple twoParametersConstructorMethodReference(final BiFunction<String, Double, MutableApple> newAppleBiFunction,
                                                                       final String color,
                                                                       final Double weight) {
        return newAppleBiFunction.apply(color, weight);
    }

    /**
     * <pre>
     * Method reference to: new MutableRGB(String first, String second, String third);
     * </pre>
     **/
    public static MutableRGB multiParameterConstructorMethodReference(final TriFunction<String, String, String, MutableRGB> newRgbTriFunction,
                                                                      final String first,
                                                                      final String second,
                                                                      final String third) {
        return newRgbTriFunction.apply(first, second, third);
    }

    /**
     * <pre>
     * Composing and chaining Comparators
     *
     * - We can sort it without composing, with just: comparing(Apple::getWeight);
     * - We can compose it returning it in the reversed order: comparing(Apple::getWeight).reversed();
     * - We can even compare after those comparisons chaining comparators: ...thenComparing(Apple::getColor);
     * </pre>
     **/
    public static Stream<Apple> sortApples(final List<Apple> apples, final Comparator<Apple> comparator) {
        return apples.stream()
                .sorted(comparator);
    }

    /**
     * <pre>
     * Composing Predicates
     *
     * - We can negate an existing predicate: redApplesPredicate.negate();
     * - We can combine two predicates: redApplesPredicate.and(heavyApplesPredicate);
     * - We can combine two predicates and an or condition: redApplesPredicate.and(heavyApplesPredicate).or(greenApplesPredicate);
     * </pre>
     **/
    public static Stream<Apple> filterApples(final List<Apple> apples, final Predicate<Apple> predicate) {
        return apples.stream()
                .filter(predicate);
    }

    /**
     * <pre>
     * Composing Functions
     *
     * - We can make use of math composed functions
     * - The end function can be a composition of two other functions
     * - With Function f and Function g
     * - g[f(x)] -> f.andThen(g); (gof)
     * - f[g(x)] -> f.compose(g); (fog) == f[g(x)] -> g.andThen(f); (fog)
     * </pre>
     **/
    public static Stream<Integer> mapNumbers(final List<Integer> numbers,
                                             final Function<Integer, Integer> function) {
        return numbers.stream()
                .map(function);
    }

    /**
     * <pre>
     * Composing Functions with complex objects
     * </pre>
     **/
    public static String mapComplexObject(final ComplexObject complexObject,
                                           final Function<ComplexObject, String> function) {
        return Optional.ofNullable(complexObject)
                .map(function)
                .orElseThrow(() -> new IllegalArgumentException("Unable to find it"));
    }
}