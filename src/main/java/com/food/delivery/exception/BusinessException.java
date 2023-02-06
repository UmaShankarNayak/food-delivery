package com.food.delivery.exception;


public class BusinessException extends RuntimeException{

    public BusinessException(String exceptionType, Object message){
        super(BusinessException.getMessage(exceptionType, message));
    }

    private static String getMessage(String exceptionType, Object message){
        return new StringBuilder()
                .append(exceptionType).append(message).toString();
    }
}
