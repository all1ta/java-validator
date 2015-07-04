package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import com.all1ta.validator.annotations.Custom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author all1ta
 */
public class CustomProcessor implements AnnotationProcessor{

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
       try {
            Custom c = Custom.class.cast(annotation);
            if(!c.type().isInterface())
            {
               return AnnotationProcessor.class.cast(c.type().newInstance()).process(annotation, field, bean); 
            } else {
               return false;
            }
       } catch (InstantiationException | IllegalAccessException ex) {
               Logger.getLogger(CustomProcessor.class.getName()).log(Level.SEVERE, null, ex);
               return false;
       }
    }
    
}
