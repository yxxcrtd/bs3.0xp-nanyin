/*
 * 文件名：EntryDeleteValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2017年2月8日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.accountentry.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dao.ValAccountsAdjustAccountEntry001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry001Service;

/**
*
* 文件名：EntryDeleteValidator.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：删除的check
* 系统名称：估值核算系统V1.0
*/
@Component
public class EntryDeleteValidator implements BeanValidator {

    /**
     * AccountsAdjustAccountEntry001Mapper
     */
//    @Autowired
//    private ValAccountsAdjustAccountEntry001Mapper valAccountsAdjustAccountEntry001Mapper;
    @Autowired
    private ValAccountsAdjustAccountEntry001Service valAccountsAdjustAccountEntry001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        ValAccountsAdjustAccountEntry001Dto dto = (ValAccountsAdjustAccountEntry001Dto)value;
        Date updateTime = valAccountsAdjustAccountEntry001Service.findAccountEntryByNodeId(dto.getSceneNodeId());
        if (updateTime == null || updateTime.getTime() != dto.getUpdateTimeFromPage()) {
            errorList.add(new ValidMessage(getMessage("VAL_COMMON_E_0064")));
        }
        return errorList;
    }

}
