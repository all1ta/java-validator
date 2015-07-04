package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author all1ta
 */
public class NotNullProcessor implements AnnotationProcessor {

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
        try
        {
            return field.get(bean)!=null;
        }catch(Exception ex)
        {
            Logger.getLogger(RangeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
