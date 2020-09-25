/*
 * 文件名：PtlDvpFundAllocation001RevokeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationRevokeDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service.PtlChlFundAllocation001Service;

/**
 * 通道资金划拨撤销检测类
 *
 * @author etliu
 * @version 1.0 2019年4月3日
 * @see PtlChlFundAllocation001RevokeValidator
 * @since 1.0
 */
@Transactional
@Component
public class PtlChlFundAllocation001RevokeValidator implements BeanValidator {
    
    /**
     * PtlChlFundAllocation001Service
     */
    @Autowired
    private PtlChlFundAllocation001Service ptlChlFundAllocation001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlChlFundAllocationRevokeDto inputDto = (PtlChlFundAllocationRevokeDto)value;
        
        // 划款指令状态为“处理成功”的划款或回款不能撤销；
        if (ptlChlFundAllocation001Service.isOrderSendOK(inputDto.getOrderId())) {
            errorList.add(new ValidMessage(getMessage("PTL_COM_E_0010")));
        }

        return errorList;
    }
}
