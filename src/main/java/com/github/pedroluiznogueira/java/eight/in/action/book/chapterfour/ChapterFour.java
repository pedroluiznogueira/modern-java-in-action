package com.github.pedroluiznogueira.java.eight.in.action.book.chapterfour;

import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Introducing Streams
 *
 * Important features:
 *
 * Sequence of elements
 * - Streams are about computations... filter, map, reduce, etc.
 * while Collections are about data
 *
 * Source
 * - Streams consume from a data-providing source, such as Collections,
 * arrays and I/O resources... generating a Stream from  a ordered data
 * source, will preserve its order, for example generating a Stream from
 * a List
 *
 * Data-processing operations
 * - Streams support database alike operations and can be executed in parallel
 * and sequentially
 *
 * Pipelining
 * - Many stream operations return a stream themselves, allowing operations to
 * be chained to form a larger pipeline. This enables certain optimizations such
 * as laziness and short-circuiting.
 * </pre>
 * <p>
 * Internal iteration
 * - Stream operations do the iteration behind the scenes for you
 * <p>
 * Streams are lazily, they are computed and supplied on demand while collections
 * are computed to be stored in memory. An analogy would be:
 * - A Stream is like a marketplace that buys its products in relation to the demand
 * of its customers
 * - A Collection is like a marketplace that buys its products in advance an then
 * sell and re-buy them in relation to the demand of its customers
 **/
@Builder
public class ChapterFour {

    public static List<Integer> processPipelineWithTerminalOperation(final List<Apple> apples,
                                                                     final boolean process) {
        final List<Integer> list = new ArrayList<>();
        // no terminal operation is called, list will NOT have any value added
        if (!process) {
            apples.stream()
                    .map(apple -> {
                        System.out.println("mapping apple weight - intermediate operation");
                        list.add(apples.indexOf(apple));
                        return apple;
                    });
            // terminal operation is called, list will have values added
        } else {
            apples.stream()
                    .map(apple -> {
                        System.out.println("mapping apple weight - intermediate operation");
                        list.add(apples.indexOf(apple));
                        return apple;
                    }).collect(Collectors.toList());
        }
        return list;
    }

    public static List<Integer> processOnlyTheLimitedAmountOfData(final List<String> names,
                                                                  final int limit) {
        final List<Integer> list = new ArrayList<>();
        names.stream()
                // the pipeline doesn't map all the names in the list and then limit it
                .map(name -> {
                    System.out.println("mapping name length - intermediate operation");
                    list.add(names.indexOf(name));
                    return name.length();
                })
                // it only maps the amount of names accordingly to the limit
                .limit(limit)
                .collect(Collectors.toList());
        return list;
    }
}