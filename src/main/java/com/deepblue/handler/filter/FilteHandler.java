package com.deepblue.handler.filter;

public abstract class FilteHandler<T> {

    public T filter(T t) {
        return doFilter(t);
    }

    public abstract T doFilter(T t);
}
