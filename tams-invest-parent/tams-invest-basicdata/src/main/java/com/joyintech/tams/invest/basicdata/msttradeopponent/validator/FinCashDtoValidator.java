package com.joyintech.tams.invest.basicdata.msttradeopponent.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.FinCashDto;

/**
 * 现金流实体类检查
 * 
 * @author songyawei
 * @version 1.0 2020/0712
 * @see FinCashDtoValidator
 * @since 1.0
 */
@Transactional
@Component
public class FinCashDtoValidator implements BeanValidator {

    /**
     * 传入参数检查 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        FinCashDto dto = (FinCashDto)value;
            // 判断操作类型
        if(DictCodeList.收费类型_计提式费用.getValue().equals(dto.getFeeType())) {
        	//判断计费基数，计息基础是否为空
        	if(!StringUtils.isNotEmpty(dto.getBasicBill())) {
        		//计费基础
        		errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006", "计提式费用 计费基础不为空")));
        	}
        	//判断计费基数，计算基础是否为空
        	if(!StringUtils.isNotEmpty(dto.getBasis())) {
        		//计算基础
        		errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006", "计提式费用 计算基础不为空")));
        	}
        }
        return errorList;
    }

}
