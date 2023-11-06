package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class GenericIteratorWithPolicy<T> implements IterableWithPolicy<T> {

    private T[] array;
    public GenericIteratorWithPolicy(T[] array) {
        this.array = array;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }

    private class Iterator implements java.util.Iterator<T> {
        
        private int index = 0;

        public T next() {
            return GenericIteratorWithPolicy.this.array[index++];
        }

        public boolean hasNext() {
            return GenericIteratorWithPolicy.this.array.length > this.index;
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return this.new Iterator();
    }
}