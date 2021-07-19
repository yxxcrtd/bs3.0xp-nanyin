/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.service.CeLineService;

@Component
public class CreditTagInfoValidator implements BeanValidator{
	

	@Autowired
	private CeLineService ceLineService;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		CreditTagInfoDto dto = (CreditTagInfoDto)value;
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			 if (StringUtil.isNotEmpty(dto.getCreditType())){
		            if (StringUtil.equals(dto.getCreditType(), DictCodeListRsk.额度分类_债券投资类额度.getValue())){
			           if("02".equals(CacheUtil.getSysParam("IS_NB_CREDIT"))){	             
		            	if (StringUtil.isEmpty(dto.getIsPerpetualSec())){
		                    errorList.add(new ValidMessage("isPerpetualSec", this.getMessage("BUSS_COM_E_0038",
		                            this.getMessage("CeLine002.isPerpetualSec"))));
		                }
		                if (StringUtil.isEmpty(dto.getIssueType())){
		                    errorList.add(new ValidMessage("issueType", this.getMessage("BUSS_COM_E_0038",
		                                this.getMessage("CeLine002.issueType"))));
		                }

		            }
	             }
		      }
			 
			 if (StringUtil.isNotEmpty(dto.getIsFx()) && StringUtil.equals(dto.getIsFx(), DictCodeList.是否_是.getValue())) {
	                if (StringUtil.isEmpty(dto.getCurPair())){
	                    errorList.add(new ValidMessage("curPair", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.curPair"))));
	                }
	               if (dto.getTermValue() ==null || "".equals(dto.getTermValue())){
	                    errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.termValue"))));
	                }
				 
			 }
			 //期限不为空时校验期限单位不能为空
			 if (StringUtil.isNotEmpty(dto.getTermValue())) {
			     if(dto.getTermValue()<=0) {
			         errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0036",
                         this.getMessage("CeLine002.termValue"),"0"))); 
			     }
			     
				 if (StringUtil.isEmpty(dto.getTermUnit())){
	                    errorList.add(new ValidMessage("termUnit", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.termUnit"))));
	             }
			 }
			 //期限单位不为空时校验期限不能为空
             if (StringUtil.isNotEmpty(dto.getTermUnit())) {
                 if (!StringUtil.isNotEmpty(dto.getTermValue())){
                        errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("CeLine002.termValue"))));
                 }
             }
			 
			 if(StringUtil.isNotEmpty(dto.getCreditTagName()) || StringUtil.isNotEmpty(dto.getCreditTagId())) {
	             boolean issame= ceLineService.checkTag(dto);
	             if(issame){
	                errorList.add(new ValidMessage("creditTagName", this.getMessage("RSK_W_0073",
	                        this.getMessage("CeLine002.creditTagName"))));
	              }
			 }
			 
			 if (StringUtil.isNotEmpty(dto.getRiskRatio())) {
			     if(BigDecimal.ZERO.compareTo(dto.getRiskRatio())>0) {
			         errorList.add(new ValidMessage("riskRatio", this.getMessage("BUSS_COM_E_0004",
                         this.getMessage("CeLine002.riskRatio"),"0"))); 
			     }
			 }

		}
		else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) && !StringUtil.equals(dto.getOperateType(),"edit")){
			 if (StringUtil.isNotEmpty(dto.getCreditType())){
		            if (StringUtil.equals(dto.getCreditType(), DictCodeListRsk.额度分类_债券投资类额度.getValue())){
		            	if("02".equals(CacheUtil.getSysParam("IS_NB_CREDIT"))){
		            	if (StringUtil.isEmpty(dto.getIsPerpetualSec())){
		                    errorList.add(new ValidMessage("isPerpetualSec", this.getMessage("BUSS_COM_E_0038",
		                            this.getMessage("CeLine002.isPerpetualSec"))));
		                }
		                if (StringUtil.isEmpty(dto.getIssueType())){
		                    errorList.add(new ValidMessage("issueType", this.getMessage("BUSS_COM_E_0038",
		                                this.getMessage("CeLine002.issueType"))));
		                }
		            	}
		            }
		        }
			 if (StringUtil.isNotEmpty(dto.getIsFx()) && StringUtil.equals(dto.getIsFx(), DictCodeList.是否_是.getValue())) {
	                if (StringUtil.isEmpty(dto.getCurPair())){
	                    errorList.add(new ValidMessage("curPair", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.curPair"))));
	                }
		            if (dto.getTermValue() ==null || "".equals(dto.getTermValue())){
	                    errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.termValue"))));
	                }
				 
			 }
			 
             //期限不为空时校验期限单位不能为空
             if (StringUtil.isNotEmpty(dto.getTermValue())) {
                 if(dto.getTermValue()<=0) {
                     errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0036",
                         this.getMessage("CeLine002.termValue"),"0"))); 
                 }
                 
                 if (StringUtil.isEmpty(dto.getTermUnit())){
                        errorList.add(new ValidMessage("termUnit", this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("CeLine002.termUnit"))));
                 }
             }
             //期限单位不为空时校验期限不能为空
             if (StringUtil.isNotEmpty(dto.getTermUnit())) {
                 if (!StringUtil.isNotEmpty(dto.getTermValue())){
                        errorList.add(new ValidMessage("termValue", this.getMessage("BUSS_COM_E_0038",
                                    this.getMessage("CeLine002.termValue"))));
                 }
             }
			 
             if(StringUtil.isNotEmpty(dto.getCreditTagName()) || StringUtil.isNotEmpty(dto.getCreditTagId())) {
                 boolean issame= ceLineService.checkTag(dto);
                 if(issame){
                    errorList.add(new ValidMessage("creditTagName", this.getMessage("RSK_W_0073",
                            this.getMessage("CeLine002.creditTagName"))));
                  }
             }
             
             if (StringUtil.isNotEmpty(dto.getRiskRatio())) {
                 if(BigDecimal.ZERO.compareTo(dto.getRiskRatio())>0) {
                     errorList.add(new ValidMessage("riskRatio", this.getMessage("BUSS_COM_E_0004",
                         this.getMessage("CeLine002.riskRatio"),"0"))); 
                 }
             }
		}
		if (StringUtil.equals(dto.getOperateType(),"delete")){
			List<CreditTagInfoDto> list = dto.getSelectedItem();
			list.forEach(item -> {
				int count = ceLineService.checkTagCanDelete(item);
				if (count > 0){
					errorList.add(new ValidMessage(item.getCreditTagName() + "标签已经在机构授信中存在关联关系，不允许删除"));
				}
			});
		}
		return errorList;
	}

}
