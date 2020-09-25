/*
 * 文件名：MstMarketInstitutionsValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.publish.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.publish.dto.ProductPublishCounting001Dto;

/**
 * @version 1.0 2019年12月23日
 * @see ProductPublishCountingValidator
 * @since 1.0
 */
@Component
public class ProductPublishCountingValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
    	ProductPublishCounting001Dto dto = (ProductPublishCounting001Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();

        if (StringUtil.equals(dto.getOperateType(), "delete")) {
        	
        }
        else {
        	if(!StringUtil.isNotEmpty(dto.getPrdIssueplanName())){
                errorList.add(new ValidMessage("发行规划名称不能为空"));
            }
        	if(!StringUtil.isNotEmpty(dto.getVdate())){
                errorList.add(new ValidMessage("发行规划起始日不能为空"));
            }
        	if(!StringUtil.isNotEmpty(dto.getEdate())){
                errorList.add(new ValidMessage("发行规划中止日不能为空"));
            }
        	if(!StringUtil.isNotEmpty(dto.getPrdIssuePlanStrategyList())){
                errorList.add(new ValidMessage("发行规划策略不能为空"));
            }
        	if(!StringUtil.isNotEmpty(dto.getPrdIssuePlanAssetList())){
                errorList.add(new ValidMessage("发行规划资产大类不能为空"));
            }
        	List<ProductPublishCounting001Dto> prdIssuePlanStrategyList = dto.getPrdIssuePlanStrategyList();
        	for(ProductPublishCounting001Dto prdIssuePlanStrategy : prdIssuePlanStrategyList) {
        		if(prdIssuePlanStrategy.getPlanChangeAmt().compareTo(BigDecimal.ZERO)<0) {
        			errorList.add(new ValidMessage("发行系列配置剩余规划变动量不能小于0"));
        		}
        	}
        	List<ProductPublishCounting001Dto> assetList = dto.getPrdIssuePlanAssetList();
        	for(ProductPublishCounting001Dto assetDto : assetList) {
        		if(assetDto.getPlanChangeAmt().compareTo(BigDecimal.ZERO)<0) {
        			errorList.add(new ValidMessage("资产大类剩余规划变动量不能小于0"));
        		}
        	}
        }
        return errorList;
    }
}
