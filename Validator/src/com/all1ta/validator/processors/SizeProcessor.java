/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import com.all1ta.validator.annotations.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author all1ta
 */
public class SizeProcessor implements AnnotationProcessor{

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
        try {
            Object value=field.get(bean);
            if(value==null)
                return true;
            
            Size size = Size.class.cast(annotation);
            
            if(value instanceof Collection)
            {
                int val = ((Collection)value).size();
                return size.min()<=val && size.max()>=val;
            } else if(value instanceof Map)
            {
                int val = ((Map)value).size();
                return size.min()<=val && size.max()>=val;
            }
            
            return false;
        } catch (Exception ex) {
            Logger.getLogger(RangeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
