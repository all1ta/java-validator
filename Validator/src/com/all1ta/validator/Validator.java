package com.all1ta.validator;

import com.all1ta.validator.annotations.*;
import com.all1ta.validator.processors.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author all1ta
 */
public class Validator {
    private static final Map<Class,AnnotationProcessor> map;
    
    static
    {
        map = new HashMap<>();
        map.put(NotNull.class, new NotNullProcessor()); 
        map.put(Range.class, new RangeProcessor());
        map.put(Regex.class, new RegexProcessor());
        map.put(Email.class, new EmailProcessor());
        map.put(Size.class, new SizeProcessor());
        map.put(Custom.class, new CustomProcessor());
        map.put(Date.class, new DateProcessor());
    }
    
    /**
     *
     * @param obj Bean object for validating.
     * @return If object is valid return true else false
     */
    
    public static Boolean validate(Object obj)
    {
        AnnotationProcessor ap;
        Annotation[] annotations;
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields)
        {
            if(!field.isAccessible())
                field.setAccessible(true);
            
            annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations)
            {
                try
                {
                //    System.out.println(annotation.annotationType());
                    ap = map.get(annotation.annotationType());
                    if(ap != null && !ap.process(annotation, field, obj))
                            return false;
                } catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}
