package com.joyintech.tams.component.business.report.asset.trust.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 委外投资—协议方式中债报送业务验证
 * @author liuwei
 * @version 1.0 2018年10月11日
 * @see BussRepOutInvestAgrInfoValidator
 * @since 1.0
 */
@Component
public class BussRepOutInvestAgrInfoValidator implements BeanValidator {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepOutInvestAgrInfo dto = (BussRepOutInvestAgrInfo)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if(StringUtil.isNotEmpty(dto.getRepTradeMarket())&&StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())
                &&!StringUtil.isNotEmpty(dto.getRepRemark())){
            errorList.add(new ValidMessage("repRemark", getMessage("BUSS_COM_E_0038", getMessage("BussRepOutInvestAgrInfo.remark"))));
        }
        if(StringUtil.equals(dto.getRepContainExpectedYield(), DictCodeList.是否_中债_是.getValue())){
            if(!StringUtil.isNotEmpty(dto.getRepMaxExpectedYield())){
                errorList.add(new ValidMessage("repMaxExpectedYield",getMessage("BUSS_COM_E_0038", getMessage("BussRepOutInvestAgrInfo.repMaxExpectedYield"))));
            }
            if(!StringUtil.isNotEmpty(dto.getRepMinExpectedYield())){
                errorList.add(new ValidMessage("repMinExpectedYield",getMessage("BUSS_COM_E_0038", getMessage("BussRepOutInvestAgrInfo.repMinExpectedYield"))));
            }
        }
        if(StringUtil.isNotEmpty(dto.getRepMaxExpectedYield())&&StringUtil.isNotEmpty(dto.getRepMinExpectedYield())){
            if(dto.getRepMaxExpectedYield().compareTo(dto.getRepMinExpectedYield())<0){
                errorList.add(new ValidMessage("repMaxExpectedYield",getMessage("BUSS_COM_E_0004",getMessage("BussRepOutInvestAgrInfo.repMaxExpectedYield"),
                        getMessage("BussRepOutInvestAgrInfo.repMinExpectedYield"))));
            }
        }
        return errorList;
    }
}
