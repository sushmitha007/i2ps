package com.stackroute.exceptions;

public class EntityNotFoundException extends Exception {
    private String message;

    public EntityNotFoundException(){}

    public EntityNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
