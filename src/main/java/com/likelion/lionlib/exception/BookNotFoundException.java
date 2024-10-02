package com.likelion.lionlib.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("Book를 찾을 수 없습니다.");
    }

}