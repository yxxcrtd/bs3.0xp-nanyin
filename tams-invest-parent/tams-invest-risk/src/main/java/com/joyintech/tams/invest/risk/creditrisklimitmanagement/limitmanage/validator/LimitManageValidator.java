/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfo;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.service.LimitManage001Service;

@Component
public class LimitManageValidator implements BeanValidator{
	

	@Autowired
	private LimitManage001Service limitManageService;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		LimitManageInfo dto = (LimitManageInfo)value;
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){/*
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
			 if (StringUtil.isNotEmpty(dto.getTermValue())) {
				 if (StringUtil.isEmpty(dto.getTermUnit())){
	                    errorList.add(new ValidMessage("termUnit", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.termUnit"))));
	                }
			 }
			 
			 boolean issame= ceLineService.checkTag(dto);
			 if(issame){
                errorList.add(new ValidMessage("creditTagName", this.getMessage("RSK_W_0073",
                        this.getMessage("CeLine002.creditTagName"))));
              }*/
		}
		else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) && !StringUtil.equals(dto.getOperateType(),"edit")){/*
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
			 if (StringUtil.isNotEmpty(dto.getTermValue())) {
				 if (StringUtil.isEmpty(dto.getTermUnit())){
	                    errorList.add(new ValidMessage("termUnit", this.getMessage("BUSS_COM_E_0038",
	                                this.getMessage("CeLine002.termUnit"))));
	                }
			 }
			 
			 boolean issame= ceLineService.checkTag(dto);
			 if(issame){
                errorList.add(new ValidMessage("creditTagName", this.getMessage("RSK_W_0073",
                        this.getMessage("CeLine002.creditTagName"))));
              }*/
		}
		return errorList;
	}

}
