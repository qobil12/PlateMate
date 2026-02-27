package com.company.Exceptions;

public class ItemMismatchException extends RuntimeException{
    public ItemMismatchException(String message) {
        super(message);
    }
}
