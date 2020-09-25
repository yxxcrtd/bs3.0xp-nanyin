package com.joyintech.tams.component.business.report.asset.plan.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产管理计划中债报送业务验证
 * @author liuwei
 * @version 1.0 2018年10月11日
 * @see BussRepAssetMngProdInfoValidator
 * @since 1.0
 */
@Component
public class BussRepAssetMngProdInfoValidator implements BeanValidator {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepAssetMngProdInfo dto = (BussRepAssetMngProdInfo) value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.isEmpty(dto.getRepAssetType())) {
            errorList.add(new ValidMessage("repAssetType",
                    getMessage("COMMON_E_0057", getMessage("BussRepAssetMngProdInfo.repAssetType"))));
        }
        if(StringUtil.isNotEmpty(dto.getRepTradeMarket())&&StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())
                &&!StringUtil.isNotEmpty(dto.getRepRemark())){
            errorList.add(new ValidMessage("repRemark", getMessage("BUSS_COM_E_0038", getMessage("BussRepAssetMngProdInfo.remark"))));
        }
        if(StringUtil.equals(dto.getRepYieldFlag(),DictCodeList.是否_中债_是.getValue())){
            if(!StringUtil.isNotEmpty(dto.getRepMaxRate())){
                errorList.add(new ValidMessage("repMaxRate",getMessage("BUSS_COM_E_0038", getMessage("BussRepAssetMngProdInfo.repMaxRate"))));
            }
            if(!StringUtil.isNotEmpty(dto.getRepMinRate())){
                errorList.add(new ValidMessage("repMinRate",getMessage("BUSS_COM_E_0038", getMessage("BussRepAssetMngProdInfo.repMinRate"))));
            }
        }
        if(StringUtil.isNotEmpty(dto.getRepMaxRate())&&StringUtil.isNotEmpty(dto.getRepMinRate())){
            if(dto.getRepMaxRate().compareTo(dto.getRepMinRate())<0){
                errorList.add(new ValidMessage("repMaxRate",getMessage("BUSS_COM_E_0004",getMessage("BussRepAssetMngProdInfo.repMaxRate"),
                        getMessage("BussRepAssetMngProdInfo.repMinRate"))));
            }
        }
        return errorList;
    }
}
