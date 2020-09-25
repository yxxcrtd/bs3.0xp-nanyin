/*
 * 文件名：AssetClassManagement001validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto.AssetClassManagement001Dto;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service.AssetClassManagement001Service;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service.AssetClassManagement002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2019年12月10日
 * @see AssetClassManagement001validator
 * @since 1.0
 */
@Component
public class AssetClassManagement001validator implements BeanValidator {
	 @Autowired
	 private AssetClassManagement002Service assetClassManagement002Service ;
	 @Autowired
	 private AssetClassManagement001Service assetClassManagement001Service ;
	 
	 Map<String,Object> parameters = new HashMap<String,Object>();
	@Override
	public List<ValidMessage> valid(Object value) {
	 	AssetClassManagement001Dto dto = (AssetClassManagement001Dto) value;
        Map<String, Object> param = new HashMap<>();
        param.put("customAssetTypeTwo",  dto.getCustomAssetTypeTwo());
        param.put("customAssetTypeThree", dto.getCustomAssetTypeThree());
        param.put("customAssetTypeFour", dto.getCustomAssetTypeFour());
        param.put("customAssetTypeFive", dto.getCustomAssetTypeFive());
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		
         
        if (StringUtil.equals(dto.getPageModel() ,"0")) {
        	Page<AssetClassManagement001Dto> resultList = assetClassManagement001Service.selectForPage(param);
        	if(resultList.getTotal() >=1) {
        		validList.add(new ValidMessage("同一产品不能存在相同类型资产分类！"));
        		return validList;
        	}
        } 
        return validList;
}
}