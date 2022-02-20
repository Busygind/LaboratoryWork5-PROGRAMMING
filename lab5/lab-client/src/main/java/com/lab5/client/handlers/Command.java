package com.lab5.client.handlers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, присваиваемая методам, которые привязаны к командам пользователя
 */
@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.METHOD)
public @interface Command {

    String name();

    String args();

    String desc();

    int countOfArgs();

    String[] aliases();

}
