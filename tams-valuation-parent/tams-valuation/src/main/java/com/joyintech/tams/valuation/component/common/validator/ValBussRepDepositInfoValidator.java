/*
 * 文件名：BussRepDepositInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.component.common.dto.ValBussRepDepositInfoDto;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 存款中债报送业务验证
 * @author zhangnian
 * @version 1.0 2018年9月18日
 * @see ValBussRepDepositInfoValidator
 * @since 1.0
 */
@Component
public class ValBussRepDepositInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValBussRepDepositInfoDto dto = (ValBussRepDepositInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        String repIsStruct = dto.getRepIsStruct();
        String type6 = dto.getType6();
        if(StringUtil.equals(type6, DictCodeList.存款类资产类型_存放同业.getValue())){
            if(StringUtil.isEmpty(dto.getRepAssetType())){
                resultList.add(new ValidMessage("repAssetType",
                    getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repAssetType"))));
            }
            if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                resultList.add(new ValidMessage("repTradeMarket",
                    getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTradeMarket"))));
            }
            if(StringUtil.equals(repIsStruct, DictCodeList.是否_是.getValue())){
                if(StringUtil.isEmpty(dto.getRepTarget())) {
                    resultList.add(new ValidMessage("repTarget",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTarget"))));
                }
                if(StringUtil.isEmpty(dto.getRepTargetType())) {
                    resultList.add(new ValidMessage("repTargetType",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTargetType"))));
                }
            }
            if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                if(StringUtil.isEmpty(dto.getRepRemark())){
                    resultList.add(new ValidMessage("repRemark",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repRemark"))));
                }
                
            }
            if(!StringUtil.isEmpty(dto.getRepRemark())){
                if(StringUtil.length(dto.getRepRemark())>300){
                    resultList.add(new ValidMessage("repRemark",
                        getMessage("BUSS_COM_E_0010", getMessage("BussRepDepositInfo.repRemark"))));
                }
            }
        }
        return resultList;
    }
}
