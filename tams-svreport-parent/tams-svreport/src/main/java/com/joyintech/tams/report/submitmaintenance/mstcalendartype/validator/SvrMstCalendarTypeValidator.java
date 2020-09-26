/*
 * 文件名：MstCalendarTypeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstcalendartype.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年8月20日
 * @see SvrMstCalendarTypeValidator
 * @since 1.0
 */
@Component
public class SvrMstCalendarTypeValidator implements BeanValidator {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        /*if (WorkFlowUtil.isNotFromToDoList()) {
            if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                if(!StringUtil.isNotEmpty(dto.getCalendarName())){
                    errorList.add(new ValidMessage("calendarName", getMessage("BUSS_COM_E_0038",
                            getMessage("MstCalendarType002.calendarName"))));
                }
                if(!StringUtil.isNotEmpty(dto.getCalendarType())){
                    errorList.add(new ValidMessage("calendarType", getMessage("BUSS_COM_E_0038",
                            getMessage("MstCalendarType002.calendarTypeInclude"))));
                }
            }
            else if (StringUtil.equals(dto.getOperateType(), "search")) {
                    if (!StringUtil.isNotEmpty(dto.getStartCDate()) && !StringUtil.isNotEmpty(dto.getEndCDate())) {
                        errorList.add(new ValidMessage("cDate", getMessage("BUSS_COM_E_0038",
                            getMessage("MstCalendarType004.cDate"))));
                }
            }
        }
        else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)||
                StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            if(!StringUtil.isNotEmpty(dto.getCalendarName())){
                errorList.add(new ValidMessage("calendarName", getMessage("BUSS_COM_E_0038",
                        getMessage("MstCalendarType002.calendarName"))));
            }
            if(!StringUtil.isNotEmpty(dto.getCalendarType())){
                errorList.add(new ValidMessage("calendarType", getMessage("BUSS_COM_E_0038",
                        getMessage("MstCalendarType002.calendarTypeInclude"))));
            }
        }*/
        return errorList;
    }
}
