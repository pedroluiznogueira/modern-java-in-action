package com.github.pedroluiznogueira.java.eight.in.action.book.support;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(final T t, final U u, final V v);
}
