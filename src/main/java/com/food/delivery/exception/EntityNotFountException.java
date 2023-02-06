package com.food.delivery.exception;

import java.util.Locale;

public class EntityNotFountException extends RuntimeException{

    public EntityNotFountException(Class<?> clazz, Object entity){
        super(EntityNotFountException.getMessage(clazz.getSimpleName(), entity));
    }

    private static String getMessage(String entityClassName, Object entity){
        return new StringBuilder().append(entityClassName.toUpperCase(Locale.ROOT))
                .append(" Entity not found with value: ").append(entity).toString();
    }
}
