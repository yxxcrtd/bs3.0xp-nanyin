/*
 * 文件名：MstMarketInstitutionsValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.feetrade.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeDetailDto;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeManagementDto;
/**
 * @version 1.0 2019年12月23日
 * @see FeeTradeManagementValidator
 * @since 1.0
 */
@Component
public class FeeTradeManagementValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
    	FeeTradeManagementDto dto = (FeeTradeManagementDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if(!"delete".equalsIgnoreCase(dto.getOperateType())) {
        	List<FeeTradeDetailDto> list = dto.getFeeTradeList();
            for(FeeTradeDetailDto detailDto : list) {
            	if(!StringUtil.isNotEmpty(detailDto.getBookSetId())
        			||!StringUtil.isNotEmpty(detailDto.getSubBookSetId())
            			||!StringUtil.isNotEmpty(detailDto.getSubBookSetId())
            				||!StringUtil.isNotEmpty(detailDto.getPayAmt())
            					||!StringUtil.isNotEmpty(detailDto.getIsNeedTan())
            						||!StringUtil.isNotEmpty(detailDto.getIsCountOver())) {
            		errorList.add(new ValidMessage("交易明细列表不允许空值"));
            		break;
            	}
            }
        }
        return errorList;
    }
}
