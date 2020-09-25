/*
 * 文件名：PtlFundAllocation001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundallocation.validate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;

/**
 * 资金划拨检测类
 * @author fanshuanghu
 * @version 1.0 2019年2月18日
 * @see PtlFundAllocation001Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlFundAllocation001Validator implements BeanValidator{
    public List<ValidMessage> valid(Object value){
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlFundAllocationDto dto = (PtlFundAllocationDto)value;
        if(StringUtil.equals(dto.getOperateType(), "submit") || StringUtil.equals(dto.getOperateType(), "save")){
            if(dto.getMyAcctAmount() != null && dto.getMyAcctAmount().compareTo(dto.getSettleAmt()) < 0){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0035",
                    getMessage("PtlFundAllocation001.settleAmts"),getMessage("PtlDvpFundAllocation001.myAcctAmount"))));
            }
            if(dto.getBussTrdTransferOrder() == null || dto.getBussTrdTransferOrder().size() == 0){
                errorList.add(new ValidMessage(getMessage("FW_E_0006",getMessage("PtlActivityPeriod001.stlPayOrders"))));
            }
        }
        return errorList;
    }
}
