/*
 * 文件名：ValDespositAssetQuery002CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate001Dto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;

/**
 * 新建修改验证
 * 
 * @author HuangJian
 * @version 1.0 2018年10月20日
 * @see AccountCurrentRate001CheckValidator
 * @since 1.0
 */
@Component(value="serviceAccountCurrentRate001CheckValidator")
public class AccountCurrentRate001CheckValidator implements BeanValidator {

    /**
     * accountCurrentRateService
     */
    @Autowired
    private AccountCurrentRateService accountCurrentRateService;

    /**
     * {@inheritDoc}
     */
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();

        AccountCurrentRate001Dto dto = (AccountCurrentRate001Dto)value;
        // 批量还是一个
        // 一个
        if (StringUtil.isEmptyList(dto.getListAccountCurrentRate001Dto())) {
            // 流程是否存在
            AccountCurrentRateDto checkDto = new AccountCurrentRateDto();
            checkDto.setAcctNo(dto.getAcctNo());
            checkDto.setEffDate(dto.getEffDate());
            checkDto.setAcctName(dto.getAcctName());
            if (accountCurrentRateService.checkBusinessDataInFlow(checkDto)) {
                String msg = this.getMessage("AccountCurrentRate.accountId")
                             + checkDto.getAcctName()
                             + this.getMessage("AccountCurrentRate.effDate");
                validMessageList.add(
                    new ValidMessage(getMessage("COMMON_E_0021", msg, checkDto.getEffDate())));
                return validMessageList;

            }
            return validMessageList;
        }
        // 批量
        else {
            List<AccountCurrentRate001Dto> listDto = dto.getListAccountCurrentRate001Dto();
            for (AccountCurrentRate001Dto item : listDto) {
                // 流程是否存在
                AccountCurrentRateDto checkDto = new AccountCurrentRateDto();
                checkDto.setAcctNo(item.getAcctNo());
                checkDto.setEffDate(item.getEffDate());
                if (accountCurrentRateService.checkBusinessDataInFlow(checkDto)) {
                    String msg = this.getMessage("AccountCurrentRate.accountId")
                                 + checkDto.getAcctName()
                                 + this.getMessage("AccountCurrentRate.effDate");
                    validMessageList.add(
                        new ValidMessage(getMessage("COMMON_E_0021", msg, checkDto.getEffDate())));

                }

            }
            return validMessageList;
        }

    }

}
