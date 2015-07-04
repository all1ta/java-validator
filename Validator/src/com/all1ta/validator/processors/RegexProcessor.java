package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import com.all1ta.validator.annotations.Regex;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author all1ta
 */
public class RegexProcessor implements AnnotationProcessor {

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
       try {
            Object value=field.get(bean);
            if(value==null)
                return true;
            
            Regex regex = Regex.class.cast(annotation);
            Class clazz = value.getClass();
            
            if(clazz.equals(String.class))
            {
                String val = value.toString();
                
                if(val.matches(regex.value()))
                    return true;
                
                Pattern pattern = Pattern.compile(regex.value());
                Matcher match = pattern.matcher(val);
                
                if(match.find())
                    return true;
            }
            
            return false;
        } catch (Exception ex) {
            Logger.getLogger(RangeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
