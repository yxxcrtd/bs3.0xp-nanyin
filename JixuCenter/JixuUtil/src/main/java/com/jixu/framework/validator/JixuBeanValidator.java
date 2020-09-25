package com.jixu.framework.validator;

import java.util.List;

public interface JixuBeanValidator {
   
    public List<JixuValidMessage> valid(Object value);
    
    
}
