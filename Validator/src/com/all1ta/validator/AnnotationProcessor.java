package com.all1ta.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author all1ta
 */
public interface AnnotationProcessor {

    /**
     *
     * @param annotation
     * @param field
     * @param bean
     * @return
     */
    public Boolean process(Annotation annotation, Field field, Object bean);
}
