package com.example.asd.exceptions;

public class NoSuchTaskException extends RuntimeException{
    public NoSuchTaskException(Long id) {
        super("Didn`t find exception with id:" + id);
    }
}
