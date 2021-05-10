package com.joyintech.tams.valuation.bokdetailidupdate.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate002CheckValidatorDto;

@Component
public class BokDetailIdUpdate002CheckValidator implements BeanValidator{

	/**
     * 系统工具类
     */
    @Autowired
    private SystemToolService systemToolService;
    
	@Override
	public List<ValidMessage> valid(Object value) {
		BokDetailIdUpdate002CheckValidatorDto dto = (BokDetailIdUpdate002CheckValidatorDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        //业务明细类型为债券时：投资目的不可为空
        if(DictCodeListExt1.金融产品类型_债券.getValue().equals(dto.getBusiDetailType())){
        	if (StringUtils.isEmpty(dto.getInvAim())) {
	       		 String msg = this.getMessage("VAL_COMMON_E_0002",this.getMessage("BokDetailIdUpdate.invAim"));
	             errorList.add(new ValidMessage(msg));
	       	 }
        }
        //业务明细类型为计划类资产时：收益类型不可为空
        if(DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(dto.getBusiDetailType())){
        	if (StringUtils.isEmpty(dto.getProfitType())) {
	       		 String msg = this.getMessage("VAL_COMMON_E_0002",this.getMessage("BokDetailIdUpdate.profitType"));
	             errorList.add(new ValidMessage(msg));
	       	 }
        }
        //业务明细类型为计划类型资产，收益类型为预估收益型时：投资目的不可为空
        if(DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(dto.getBusiDetailType())
        		&& DictCodeList.收益类型_收益型.getValue().equals(dto.getProfitType())){
        	if (StringUtils.isEmpty(dto.getInvAim())) {
	       		 String msg = this.getMessage("VAL_COMMON_E_0002",this.getMessage("BokDetailIdUpdate.invAim"));
	             errorList.add(new ValidMessage(msg));
	       	 }
        }
        //业务明细类型 为理财子产品时：理财子产品起息日不可为空
        if(DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())){
        	if(dto.getVdate()==null){
        		String msg = this.getMessage("VAL_COMMON_E_0002",this.getMessage("BokDetailIdUpdate.vdate"));
	            errorList.add(new ValidMessage(msg));
        	}
        }
        //业务明细类型 IN (资金账户类型)时：币种不可为空
        if(systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), dto.getBusiDetailType()) != null){
        	if (StringUtils.isEmpty(dto.getCcy())) {
	       		 String msg = this.getMessage("VAL_COMMON_E_0002",this.getMessage("BokDetailIdUpdate.ccy"));
	             errorList.add(new ValidMessage(msg));
	       	 }
        }
        return errorList;
	}

}
