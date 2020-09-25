/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.service.CreditRightsManagementService;

@Component
public class CreditRightsManagementValidator implements BeanValidator{
	
	/**
	 * 
	 */
	@Autowired
	private CreditRightsManagementService creditRightsManagementService;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> erroList = new ArrayList<ValidMessage>();
		
		CreditRightsManagementDto dto = (CreditRightsManagementDto)value;
		
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			//1.同一个资产二级类型、同一生效日只能有一条数据
			if(StringUtil.isNotEmpty(dto.getAssetTypeOne()) && StringUtil.isNotEmpty(dto.getEffDateStr())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("assetTypeOne", dto.getAssetTypeOne());
				map.put("assetTypeTwo", StringUtil.isNotEmpty(dto.getAssetTypeTwo())?dto.getAssetTypeTwo():null);
				map.put("effDate", dto.getEffDateStr());
				if(creditRightsManagementService.checkalreadyhave(map).size()>0){
					erroList.add(new ValidMessage("","同一个资产一级类型、资产二级类型、同一生效日只能有一条数据"));
				}
			}
			
		}
		else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) && !StringUtil.equals(dto.getOperateType(),"edit")){
			//1.同一个资产二级类型、同一生效日只能有一条数据
			if(StringUtil.isNotEmpty(dto.getAssetTypeOne()) &&StringUtil.isNotEmpty(dto.getEffDateStr())){
				if(dto.getEffDateOld().compareTo(dto.getEffDate()) != 0){
					if(StringUtil.isNotEmpty(dto.getAssetTypeTwo()) && StringUtil.isNotEmpty(dto.getEffDateStr())){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("assetTypeOne", dto.getAssetTypeOne());
						map.put("assetTypeTwo", StringUtil.isNotEmpty(dto.getAssetTypeTwo())?dto.getAssetTypeTwo():null);
						map.put("effDate", dto.getEffDateStr());
						if(creditRightsManagementService.checkalreadyhave(map).size()>0){
							erroList.add(new ValidMessage("","同一个资产一级类型、资产二级类型、同一生效日只能有一条数据"));
						}
					}
				}
			}
			
		}
		return erroList;
	}

}
