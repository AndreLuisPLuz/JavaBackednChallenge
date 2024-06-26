package com.challenge.interfaces.structure;

public sealed interface Result<T> {
    record Ok<T>(T result) implements Result<T> {}
    record Error<T>(String message) implements Result<T> {}
}