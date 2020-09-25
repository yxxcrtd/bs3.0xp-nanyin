/*
 * 文件名：FinNetProductDurationDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.cfetsup.secrequestquote.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 质押式回购报价校验
 * @author zcf
 * @version 1.0
 * @date 2020-01-09
 */
@Component
public class FxSecRequestQuoteDtoValidator implements BeanValidator{


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FxSecRequestQuoteDto  dto = (FxSecRequestQuoteDto) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        //委托编号
        if (StringUtils.isEmpty(dto.getTrustid())) {
            errorList.add(new ValidMessage("委托编号不能为空！"));
            return errorList;
        }
        //组合代码
        if (StringUtils.isEmpty(dto.getAccountid())) {
            errorList.add(new ValidMessage("产品代码不能为空！"));
            return errorList;
        }
        //本币本方
        if (StringUtils.isEmpty(dto.getHomecstpid())||StringUtils.isEmpty(dto.getHomecstpname())) {
            errorList.add(new ValidMessage("本币本方不能为空，请检查产品是否关联交易账户"));
            return errorList;
        }

        //本方交易员
        if (StringUtils.isEmpty(dto.getHometradername())||StringUtils.isEmpty(dto.getHometrader())) {
            errorList.add(new ValidMessage("本方交易员不能为空！请检查当前用户是否关联外汇交易中心交易员"));
            return errorList;
        }
        return errorList;
    }

}
