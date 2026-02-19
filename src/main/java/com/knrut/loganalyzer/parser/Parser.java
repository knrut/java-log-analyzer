package com.knrut.loganalyzer.parser;

public interface Parser<T> {
    T parse(String line);
}
