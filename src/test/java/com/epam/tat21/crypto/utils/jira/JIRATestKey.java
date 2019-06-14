package com.epam.tat21.crypto.utils.jira;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface JIRATestKey {
    String key();

    boolean disabled() default false;
}