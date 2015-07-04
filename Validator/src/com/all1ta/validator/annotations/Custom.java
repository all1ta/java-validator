package com.all1ta.validator.annotations;

import com.all1ta.validator.AnnotationProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author all1ta
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Custom {
    Class<? extends AnnotationProcessor> type();
}
