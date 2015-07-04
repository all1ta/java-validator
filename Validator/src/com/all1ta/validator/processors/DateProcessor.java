/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all1ta.validator.processors;

import com.all1ta.validator.AnnotationProcessor;
import com.all1ta.validator.annotations.Date;
import com.all1ta.validator.enums.DateType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 *
 * @author all1ta
 */
public class DateProcessor implements AnnotationProcessor{

    @Override
    public Boolean process(Annotation annotation, Field field, Object bean) {
        try
        {
            Object val = field.get(bean);
            if(val==null)
                return true;
            
            Date d = Date.class.cast(annotation);
            Calendar now = Calendar.getInstance();
            Calendar calendar = null;
            if(val.getClass().equals(java.util.Date.class)){
                calendar = Calendar.getInstance();
                calendar.setTime((java.util.Date) val);
            }
            else if(val instanceof Calendar)
            {
                calendar = (Calendar) val;
            }
            System.out.println(val.getClass());
            if(calendar!=null)
            { 
                if(d.value()==DateType.FUTURE)
                    return calendar.after(now);
                if(d.value()==DateType.PAST)
                    return calendar.before(now);
                return false;
            } else {
                return false;
            }
            
        }catch(Exception e)
        {
            return false;
        }
    }
    
}
