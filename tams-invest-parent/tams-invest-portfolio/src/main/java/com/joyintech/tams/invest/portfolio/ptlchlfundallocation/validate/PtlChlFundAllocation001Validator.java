/*
 * 文件名：PtlFundAllocation001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;

/**
 * 通道资金划拨检测类
 * 
 * @author etliu
 * @version 1.0 2019年2月18日
 * @see PtlChlFundAllocation001Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlChlFundAllocation001Validator implements BeanValidator {

    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlChlFundAllocationDto dto = (PtlChlFundAllocationDto)value;
        if (StringUtil.equals(dto.getOperateType(), "submit")
            || StringUtil.equals(dto.getOperateType(), "save")) {
            
            // 划拨金额必须大于0
            if (dto.getSettleAmt() != null && BigDecimal.ZERO.compareTo(dto.getSettleAmt()) >= 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0036",
                    getMessage("PtlChlFundAllocation001.settleAmts"), "0")));
            }

            // 划拨金额不能大于付款账户余额
            if (dto.getMyAcctAmount() != null
                && dto.getSettleAmt() != null
                && dto.getMyAcctAmount().compareTo(dto.getSettleAmt()) < 0) {
                errorList.add(new ValidMessage(
                    getMessage("BUSS_COM_E_0003", getMessage("PtlChlFundAllocation001.settleAmts"),
                        getMessage("PtlChlFundAllocation001.myAcctAmount"))));
            }

            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
            // 通道回款时本金收益必填
            if (dto.getPayType() != null
                && DictCodeListExt2.交易方向_通道回款.getValue().equals(dto.getPayType())) {
                if (dto.getPrinAmt() == null) {
                    errorList.add(new ValidMessage("prinAmt",
                        getMessage("FW_E_0006", getMessage("PtlChlFundAllocation001.prinAmt"))));
                }
                if (dto.getIntAmt() == null) {
                    errorList.add(new ValidMessage("intAmt",
                        getMessage("FW_E_0006", getMessage("PtlChlFundAllocation001.intAmt"))));
                }
            }
            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
            // 划款指令必填
            if (StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("PtlActivityPeriod001.stlPayOrders"))));
            }
        }
        return errorList;
    }
}
