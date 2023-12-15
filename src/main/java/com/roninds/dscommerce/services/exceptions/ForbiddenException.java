package com.roninds.dscommerce.services.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String s) {
        super(s);
    }
}
