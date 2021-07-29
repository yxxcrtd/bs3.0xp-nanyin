package com.joyintech.tams.invest.entrust.etrautomanagementaccount.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class EtrAutoManagementAccountValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object value) {
        EtrAutoManagementAccountDto dto = (EtrAutoManagementAccountDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage> ();
        if (StringUtil.equals(dto.getOperateType(), "submit") || StringUtil.equals(dto.getOperateType(), "update")) {
            if(StringUtil.isNotEmpty(dto.getEntrustAmt ())){
                if(dto.getEntrustAmt ().compareTo (BigDecimal.ZERO) <= 0){
                    resultList.add(new ValidMessage(this.getMessage("指令金额必须大于0", String.valueOf (dto.getEntrustAmt ()))));
                }
//                if(StringUtil.isNotEmpty(dto.getPortfolioAmt ())){
//                    if(dto.getEntrustAmt ().compareTo (dto.getPortfolioAmt ()) != 0){
//                         resultList.add(new ValidMessage(this.getMessage("组合分配金额加总与指令金额不同，无法提交", String.valueOf (dto.getEntrustAmt ()))));
//                    }
//                }
            }
        }else if (StringUtil.equals(dto.getOperateType(), "close") ) {
			//自主专户，不能关闭已完成状态的指令
        	if(!StringUtil.isEmptyList(dto.getSelectedItem())){
        		for(EtrAutoManagementAccountDto queryDto:dto.getSelectedItem()){
        			if(StringUtil.equals(queryDto.getEntrustStatus(), DictCodeList.委托状态_已完成.getValue())){
	               		 resultList.add(new ValidMessage(this.getMessage("不能关闭已完成状态的指令")));
	               	}
        		}
        	}
        	
		}
        return resultList;
    }
}
