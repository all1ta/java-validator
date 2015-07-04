package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import com.all1ta.validator.annotations.Range;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author all1ta
 */
public class RangeProcessor implements AnnotationProcessor{

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
        try {
            Object value=field.get(bean);
            if(value==null)
                return true;
            
            Range range = Range.class.cast(annotation);
            Class clazz = value.getClass();
            
            if(clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Short.class) || clazz.equals(Byte.class))
            {
                Long val = Long.parseLong(value.toString());
                return range.min()<=val && range.max()>=val;
            } else if(clazz.equals(Float.class) || clazz.equals(Double.class))
            {
                Double val = Double.valueOf(value.toString());
                return range.min()<=val && range.max()>=val;
            } else if(clazz.equals(String.class))
            {
                int val = value.toString().length();
                return range.min()<=val && range.max()>=val;
            }
            
            return false;
        } catch (Exception ex) {
            Logger.getLogger(RangeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
