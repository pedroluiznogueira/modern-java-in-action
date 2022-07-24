package com.github.pedroluiznogueira.java.eight.in.action.book.chapterfour;

import com.github.pedroluiznogueira.java.eight.in.action.book.AbstractUnitTest;
import com.github.pedroluiznogueira.java.eight.in.action.book.domain.Apple;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChapterFourTest extends AbstractUnitTest {

    @Test
    public void generateStreamFromAnOrderedSourcePreservesItsOrder() {
        // arrange
        final List<Integer> orderedNumbers = List.of(1, 2, 3);

        // act
        final Stream<Integer> orderedNumbersAsStream = orderedNumbers.stream();
        final List<Integer> collectedStream = orderedNumbersAsStream.collect(Collectors.toList());

        // assert
        assertThat(collectedStream.get(0))
                .isLessThan(collectedStream.get(2));
    }

    @Test
    public void streamPipelineIsNotProcessedWhenNotCallingTerminalOperation() {
        // arrange
        final List<Apple> apples = getApples();

        // act
        final var nonProcessed = ChapterFour.processPipelineWithTerminalOperation(apples, false);

        // assert
        assertThat(nonProcessed)
                .hasSize(0);
    }

    @Test
    public void streamPipelineIsProcessedWhenCallingTerminalOperation() {
        // arrange
        final List<Apple> apples = getApples();

        // act
        final var nonProcessed = ChapterFour.processPipelineWithTerminalOperation(apples, true);

        // assert
        assertThat(nonProcessed)
                .hasSize(apples.size());
    }

    @Test
    public void streamPipelineOnlyProcessTheAmountOfDataLimitedByLimitIntermediateOperation() {
        // arrange
        final List<String> names = getNames();
        final int limit = 2;

        // act
        final var limitedList = ChapterFour.processOnlyTheLimitedAmountOfData(names, limit);

        // assert
        assertThat(limitedList)
                .hasSize(limit);
    }

}