/*
 * 文件名：RskPortfolioIndicator002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.validator;

import java.util.ArrayList;
import java.util.List;


import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint002Service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 组合关联指标验证
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月13日
 * @see RskPortfolioIndicator002Validator
 * @since 1.0
 */
@Component
public class RskPortfolioIndicator002Validator implements BeanValidator{

	@Autowired(required = false)
	private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;
	  
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    @Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        RskPortfolioIndicator002Dto dto = (RskPortfolioIndicator002Dto)value;
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        	if(!StringUtil.isNotEmpty(dto.getStartDate())){
        		 resultList.add(new ValidMessage("startDate",getMessage("COMMON_E_0057", getMessage("RskSupervisionPortfPoint002.startDate"))));
        	}
        	if(StringUtil.isNotEmpty(dto.getStartDate())&&StringUtil.isNotEmpty(dto.getEndDate())){
                if(DateUtil.compareDate(dto.getStartDate(), dto.getEndDate())==1){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0003", getMessage("RskSupervisionPortfPoint002.startDate"),getMessage("RskSupervisionPortfPoint002.endDate"))));
                }
            }
        	// 有效区间开始日大于等于平台日的check
        	if(StringUtil.isNotEmpty(dto.getStartDate()) && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	    if (DateUtil.compareDate(dto.getStartDate(), iPlatformDate.getSysDate()) < 0) {
        	        resultList.add(new ValidMessage("startDate",
                        getMessage("BUSS_COM_E_0004", this.getMessage("RskSupervisionPortfPoint002.effDateS"),
                            this.getMessage("RskSupervisionPortfPoint002.platDate"))));
                }
        	}
        	if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        		if (rskSupervisionPortfPoint002Service.checkBusinessDataInFlow(dto)) {
	            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getIndName())));
	            }
            	 if(!StringUtil.isNotEmpty(rskSupervisionPortfPoint002Service.getBussinessEntityByPortfId(dto.getPortfolioId(),dto.getIndId()))){
            		 resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
            	 }
        	}
        	//----2019/02/18---------校验组合包是否已经配置了该指标----------
        	 if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
       		  dto.getSelectedItem().forEach(item -> {
       			   if(BussinessDictCodeListRds.组合层次_单组合.getValue().equals(dto.getPortfolioLevel())){
			           if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortf(item.getIndId(),dto.getPortfolioId())>0){
			        	    resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0038",dto.getPortfolioName())));
			           }
       			   }
       			   else{
       				   if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBagSingle(item.getIndId(),dto.getPortfolioId())>0){
			        	   resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0040",dto.getPortfolioName())));
			           }
       				   if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBag(item.getIndId(),dto.getPortfolioId())>0){
			        	   resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0039",dto.getPortfolioName())));
			           }
       			   }
                 });
        	 }
        }
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
        	  if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
        		  for(RskPortfolioIndicator002Dto item:dto.getSelectedItem()){
        			  if (rskSupervisionPortfPoint002Service.checkBusinessDataInFlow(item)) {
  		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getIndName())));
  		            }
  		            if (!StringUtil.isNotEmpty(rskSupervisionPortfPoint002Service.getBussinessEntityByPortfId(item.getPortfolioId(),item.getIndId()))) {
  		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
  		            }
  		          //判断删除流程中是否存在的设置流程为了更改修改之后再删除没有报流程中的BUG
  		            WflValidInputParameter params = new WflValidInputParameter();
      		        params.setBusinessDataPk1(item.getPortfolioId());
      		        params.setBusinessDataPk2(item.getIndId());
      		        params.setPageId("RskSupervisionPortfPoint004");
      		        if(null != mstWorkflowCommonService && 
      		            mstWorkflowCommonService.isInWflProcess(params)){
      		        	resultList.add(
      		                new ValidMessage(this.getMessage("BUSS_COM_E_0023")));
      		        	return resultList;
      		        }
  		          //判断删除流程中是否存在个性化参数的设置流程
  		            params = new WflValidInputParameter();
      		        params.setBusinessDataPk1(item.getPortfolioId());
      		        params.setBusinessDataPk2(item.getIndId());
      		        params.setPageId("RskSupervisionIndividuation001");
      		        if(null != mstWorkflowCommonService && 
      		            mstWorkflowCommonService.isInWflProcess(params)){
      		        	resultList.add(
      		                new ValidMessage(this.getMessage("BUSS_COM_E_0023")));
      		        	return resultList;
      		        }
        		  }
        		
        	  }
        	  //判断删除流程中是否存在个性化参数的设置流程
        	  /*if(!StringUtil.isEmptyList(dto.getRskSupervisionIndicator002DtoList())){
        		  for(RskSupervisionIndicator002Dto e:dto.getRskSupervisionIndicator002DtoList()){
        			  WflValidInputParameter params = new WflValidInputParameter();
        		        params.setBusinessDataPk1(e.getPortfolioId());
        		        params.setBusinessDataPk2(e.getIndId());
        		        params.setPageId("RskSupervisionIndividuation001");
        		        //查询该指标是否有修改或删除流程流程
        		        if(null != mstWorkflowCommonService && 
        		            mstWorkflowCommonService.isInWflProcess(params)){
        		        	resultList.add(
        		                new ValidMessage(this.getMessage("BUSS_COM_E_0023")));
        		        }
        		  }
        	  }*/
        }
        return resultList;
    }

}
