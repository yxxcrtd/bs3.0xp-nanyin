/*
 * 文件名：PtlDvpFundAllocation001RevokeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptldvpfundallocation.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationRevokeDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.service.PtlDvpFundAllocation001Service;

/**
 * Dvp资金划拨撤销检测类
 *
 * @author etliu
 * @version 1.0 2019年4月3日
 * @see PtlDvpFundAllocation001RevokeValidator
 * @since 1.0
 */
@Transactional
@Component
public class PtlDvpFundAllocation001RevokeValidator implements BeanValidator {
    
    /**
     * PtlDvpFundAllocation001Service
     */
    @Autowired
    private PtlDvpFundAllocation001Service ptlDvpFundAllocation001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlDvpFundAllocationRevokeDto inputDto = (PtlDvpFundAllocationRevokeDto)value;
        
        // etliu 20190719 指令已经发送成功，原逻辑不允许撤销，改成：确认消息，允许继续撤销。
//        // 指令是否已发送检查
//        if (ptlDvpFundAllocation001Service.isOrderSended(inputDto.getOrderId())) {
//            errorList.add(new ValidMessage(getMessage("PTL_COM_E_0010")));
//        }

        return errorList;
    }
}
