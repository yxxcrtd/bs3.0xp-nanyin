/*
 * 文件名：ValManualVoucher003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003AddDto;
import com.joyintech.tams.common.base.dao.BokSubjectMapper;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.BokSubjectKey;

/**
 * 手工凭证新建校验
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003AddValidator
 * @since 1.0
 */
@Component
public class ValManualVoucher003AddValidator implements BeanValidator{

    @Autowired
    private BokSubjectMapper valBokSubjectMapper;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        /*ValManualVoucher003AddDto dto = (ValManualVoucher003AddDto) value;
        BokSubjectKey key = new BokSubjectKey();
        //TODO 暂时写死
        key.setBooksetId(dto.getBooksetId());
        key.setSubjectNo(dto.getSubjectNo());*/
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        /*if(StringUtil.isNotEmpty(dto.getSubjectNo())){
            BokSubject valBokSubject = valBokSubjectMapper.selectByPrimaryKey(key);
            if(!StringUtil.isNotEmpty(valBokSubject)){
                String msg = this.getMessage("VAL_COMMON_E_0016", 
                  this.getMessage("ValManualVoucher.subjectNo"));
                errorList.add(new ValidMessage("subjectNo",msg));
            }
        }*/
        return errorList;
    }

}
