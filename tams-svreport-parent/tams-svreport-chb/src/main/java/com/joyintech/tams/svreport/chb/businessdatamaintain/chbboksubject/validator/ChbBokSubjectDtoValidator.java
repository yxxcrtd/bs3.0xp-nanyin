package com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.service.ChbBokSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wubingbing
 * @date 2022-03-28 14:42
 */
@Component
public class ChbBokSubjectDtoValidator implements BeanValidator {
    @Autowired
    private ChbBokSubjectService bokSubjectService;
    @Override
    public List<ValidMessage> valid(Object o) {
        return null;
    }

}
