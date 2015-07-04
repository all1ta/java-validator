/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import com.all1ta.validator.annotations.Email;
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
public class EmailProcessor implements AnnotationProcessor {
    String emailPattern_0 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@";
    String emailPattern_1 = "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
        try {
            Object value=field.get(bean);
            if(value==null)
                return true;
            
            Email email = Email.class.cast(annotation);
            Class clazz = value.getClass();
            
            if(clazz.equals(String.class))
            {
                String val = value.toString();
                Pattern pattern;
                if(email.domain().equals(""))
                {
                    pattern = Pattern.compile(emailPattern_0+emailPattern_1);
                } else {
                    pattern = Pattern.compile(emailPattern_0+email.domain().replace(".", "\\.").replace("*", ".+"),Pattern.CASE_INSENSITIVE);
                }
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
