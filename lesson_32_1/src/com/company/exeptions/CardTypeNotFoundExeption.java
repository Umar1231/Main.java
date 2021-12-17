package com.company.exeptions;

public class CardTypeNotFoundExeption extends RuntimeException{
    public CardTypeNotFoundExeption(String message) {
        super(message);
    }
}
