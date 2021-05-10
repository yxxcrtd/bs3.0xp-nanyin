/*
 * 文件名：ValManualVoucher003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003CheckValidatorDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 手工凭证模板确认校验
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月22日
 * @see ValManualVoucherTemplete003CheckValidator
 * @since 1.0
 */
@Component(value="serviceValManualVoucherTemplete003CheckValidator")
public class ValManualVoucherTemplete003CheckValidator implements BeanValidator {

    /**
     * 列表确认时校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValManualVoucherTemplete003CheckValidatorDto dto = (ValManualVoucherTemplete003CheckValidatorDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(dto.getSubjectNo())) {
            return errorList;
        }

        // 借方金额、贷方金额都不为空时
        if ((dto.getdAmt() != null) && (dto.getcAmt() != null)) {
            // 借方金额、贷方金额只能填写一个
            String msg = this.getMessage("MANUAL_VOUCHER_E_002",
                this.getMessage("ValManualVoucher.dAmt"),
                this.getMessage("ValManualVoucher.cAmt"));
            errorList.add(new ValidMessage(msg));
            // 借方金额、贷方金额都为空时
        }
//        else if ((dto.getdAmt() == null) && (dto.getcAmt() == null)) {
//            // 借方金额、贷方金额不能同时为空
//            String msg = this.getMessage("MANUAL_VOUCHER_E_004",
//                this.getMessage("ValManualVoucher.dAmt"),
//                this.getMessage("ValManualVoucher.cAmt"));
//            errorList.add(new ValidMessage(msg));
//        }
        if(((dto.getdAmt() != null) || (dto.getcAmt() != null)) && dto.getCcy()==null){
            String msg = this.getMessage("VAL_COMMON_E_0002",
                    this.getMessage("ValManualVoucher.ccy"));
            errorList.add(new ValidMessage(msg));
        }
        return errorList;
    }

}
