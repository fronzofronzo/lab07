package it.unibo.inner.impl;

import java.lang.reflect.Parameter;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

/**
 * Generic class that implemets IterableWithPolicy
 */
public class GenericIteratorWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] array;
    private  Predicate<T> filter;
    
    public GenericIteratorWithPolicy(T[] array, Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    /**
     * Costructor of GenericIteratorWithPolicy 
     * @param array is an array of T elements that will initialize the 
     * array of GenericIteratorWithPolicy
     */
    public GenericIteratorWithPolicy(T[] array) {
        this(array, new Predicate<T>() {
            public boolean test(T t) { return true; };
        } );
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    private class Iterator implements java.util.Iterator<T> {
        
        private int index = 0;

        public T next() {
            if(hasNext()) {
                while(true) {
                    T elem = GenericIteratorWithPolicy.this.array[this.index++];
                    if(filter.test(elem)) {
                        return elem;
                    } 
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        public boolean hasNext() {
            if(this.index < GenericIteratorWithPolicy.this.array.length) {
                for(int copy = this.index; copy < GenericIteratorWithPolicy.this.array.length; copy++ ) {
                    final T element = GenericIteratorWithPolicy.this.array[copy];
                    if(filter.test(element)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return this.new Iterator();
    }
}