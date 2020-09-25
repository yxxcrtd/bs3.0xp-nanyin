/*
 * 文件名：MstSecuritiesAccountValidate.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lizhi
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.service.FundSecuritiesAccountService;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;

/**
 * 基金账户管理校验
 * 〈功能详细描述〉
 * @author lizhi
 * @version 1.0 2018年9月14日
 * @see FundSecuritiesAccountValidator
 * @since 1.0
 */
@Component
public class FundSecuritiesAccountValidator implements BeanValidator{
    /**
     * 基金账户Service
     */
    @Autowired
    private FundSecuritiesAccountService fundSecuritiesAccountService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FundSecuritiesAccountDto dto = (FundSecuritiesAccountDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<FundSecuritiesAccountDto> list = dto.getSelectedItem();
            List<FundSecuritiesAccountDto> tempList = fundSecuritiesAccountService.checkAllCodeIsUse(list);
            //判断组合表中是否连着这个基金账户
            if (tempList.size() > 0) {
                for (FundSecuritiesAccountDto tempDto : tempList) {
                    resultList.add(new ValidMessage("", getMessage("COMMON_E_0030",
                        getMessage("FundSecuritiesAccount001.secAcctId"),
                        tempDto.getSecAcctId())));
                       
                }
            }
        }
        else  if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        }
        else {// 新建校验
              // 判断新建的基金账户账号数据是否存在
            if (fundSecuritiesAccountService.checkBySecAcctId(dto) > 0) {
                StringBuffer sb = new StringBuffer();
                sb.append(getMessage("FundSecuritiesAccount001.secAcctId")).append(GlobalConstant.COLON).append(dto.getSecAcctId());
                resultList.add(
                    new ValidMessage("secAcctId", getMessage("BUSS_COM_I_0017", sb.toString())));
            }
        }

        return resultList;
    }

}