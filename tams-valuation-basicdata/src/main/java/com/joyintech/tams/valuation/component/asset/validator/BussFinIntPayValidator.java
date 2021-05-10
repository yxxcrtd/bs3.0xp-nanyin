/*
 * 文件名：BussFinIntPayValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.asset.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.valuation.component.asset.dto.BussFinIntPayDto;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月8日
 * @see BussFinIntPayValidator
 * @since 1.0
 */
@Component
public class BussFinIntPayValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinIntPayDto dto = (BussFinIntPayDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        if (StringUtil.equals("01",dto.getFundType()) || StringUtil.equals("03",dto.getProfitType())){
            if (!StringUtil.isNotEmpty(dto.getPayType())){
                resultList.add(new ValidMessage(" 请输入分红方式。"));
            }
            if (!StringUtil.isNotEmpty(dto.getPayCycle())){
                resultList.add(new ValidMessage(" 请输入分红频率。"));
            }
            /**
             * 基金类型为货币基金时分红规则可见；分红频率为每日时分红日、营业日准则、是否按日复利不可用；分红频率不为每日时，分红日、营业日准则、是否按日复利字段可用
             */
            if (StringUtil.equals("02",dto.getPayCycle())){
                if (!StringUtil.isNotEmpty(dto.getDayValue())){
                    resultList.add(new ValidMessage("请输入分红日。"));
                }
                if (!StringUtil.isNotEmpty(dto.getEndTdayRule())){
                    resultList.add(new ValidMessage("请输入营业日准则。"));
                }
                if (!StringUtil.isNotEmpty(dto.getIsCompoundInt())){
                    resultList.add(new ValidMessage("请输入是否按日复利。"));
                }
            }
        }

        if(StringUtil.equals(dto.getPayCycle(),DictCodeList.收益结转频率_按月结转.getValue())){
            if(null == dto.getDayValue() || "".equals(dto.getDayValue())){
//                resultList.add(new ValidMessage("dayValue",
//                    getMessage("COMMON_E_0057", getMessage("BussFinIntPay.dayValue"))));
            }
        }
        if(StringUtil.equals(dto.getFundType(), DictCodeList.基金类资产类型_货币基金.getValue()) &&
            StringUtil.equals(dto.getPayCycle(), DictCodeList.收益结转频率_按月结转.getValue())){
            if(dto.getEndTdayRule() == null 
                || "".equals(dto.getEndTdayRule()) ){
                resultList.add(new ValidMessage("endTdayRule",
                    getMessage("COMMON_E_0057", getMessage("BussFinIntPay.endTdayRule"))));
            }
            if(dto.getEndTdayRule() == null 
                ||"".equals(dto.getPayTdayRule()) ){
                resultList.add(new ValidMessage("payTdayRule",
                    getMessage("COMMON_E_0057", getMessage("BussFinIntPay.payTdayRule"))));
            }
        }
        return resultList;
    }

}
