package com.api.demon.exception;

public class studentAlreadyExistsException extends RuntimeException{
    public studentAlreadyExistsException(String message){
        super(message);
    }
}
