package com.example.asd.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String name){
        super("Користувач " + name + " вже існує");
    }
}
