/*
 * 文件名：ValDespositAssetQuery002CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.accountcurrentrate.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dao.AccountCurrentRate002Mapper;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 新建修改验证
 * 
 * @author HuangJian
 * @version 1.0 2018年10月20日
 * @see AccountCurrentRate002CheckValidator
 * @since 1.0
 */
@Component
public class AccountCurrentRate002CheckValidator implements BeanValidator {

    /**
     * accountCurrentRate002Mapper
     */
    @Autowired
    private AccountCurrentRate002Mapper accountCurrentRate002Mapper;

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

        AccountCurrentRate002Dto dto = (AccountCurrentRate002Dto)value;

        // 修改
        if (StringUtil.isNotEmpty(dto.getCashId())) {
            // check修改前的数据
            AccountCurrentRateDto checkDto = new AccountCurrentRateDto();
            checkDto.setAcctNo(dto.getAccountId());
            checkDto.setEffDate(dto.getEffDate1());
            if (accountCurrentRateService.checkBusinessDataInFlow(checkDto)) {
                String msg = this.getMessage("AccountCurrentRate.accountId") + checkDto.getAcctNo()
                             + this.getMessage("AccountCurrentRate.effDate");
                validMessageList.add(
                    new ValidMessage(getMessage("COMMON_E_0021", msg, checkDto.getEffDate())));
                return validMessageList;

            }

            if (!dto.getEffDate().equals(dto.getEffDate1())) {
                Map<String, Object> map = new HashMap<>();
                map.put("cashId", dto.getCashId());
                map.put("effDate", dto.getEffDate());
                if (accountCurrentRate002Mapper.checkAccNo(map)) {
                    String msg = this.getMessage("ACCOUNTCURRENTRATE_E_001");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }
            }

        }
        // 新建
        else {
            AccountCurrentRateDto checkDto = new AccountCurrentRateDto();
            checkDto.setAcctNo(dto.getAccountId());
            checkDto.setEffDate(dto.getEffDate());
            // 判断新建是否在流程中
            if (accountCurrentRateService.checkBusinessDataInFlow(checkDto)) {
                String msg = this.getMessage("AccountCurrentRate.accountId") + checkDto.getAcctNo()
                             + this.getMessage("AccountCurrentRate.effDate");
                validMessageList.add(
                    new ValidMessage(getMessage("COMMON_E_0021", msg, checkDto.getEffDate())));
                return validMessageList;

            }

            String accNo = dto.getAccountId();
            String accType = dto.getAccountType();
            if (!accountCurrentRate002Mapper.checkAccNo1(accNo, accType)) {
                String msg = this.getMessage("ACCOUNTCURRENTRATE_E_002");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;

            }

            String cashId = InterfHelper.getMd5CashId_(dto.getAccountId(),
                DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
            String effDate = dto.getEffDate();
            Map<String, Object> map = new HashMap<>();
            map.put("cashId", cashId);
            map.put("effDate", effDate);
            if (accountCurrentRate002Mapper.checkAccNo(map)) {
                String msg = this.getMessage("ACCOUNTCURRENTRATE_E_001");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;
            }

        }
        return validMessageList;

    }

}
