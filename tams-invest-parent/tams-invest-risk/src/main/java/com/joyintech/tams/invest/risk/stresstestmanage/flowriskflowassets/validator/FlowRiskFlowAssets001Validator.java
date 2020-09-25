/*
 * 文件名：RskPortfolioIndicator002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.service.FlowRiskFlowAssets001Service;

/**
 * 组合关联指标验证
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月13日
 * @see FlowRiskFlowAssets001Validator
 * @since 1.0
 */
@Component
public class FlowRiskFlowAssets001Validator implements BeanValidator{

	@Autowired(required = false)
	private FlowRiskFlowAssets001Service flowRiskFlowAssets001Service;
	  
	
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        FlowRiskFlowAssets001Dto dto = (FlowRiskFlowAssets001Dto)value;
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        	//一级资产不能为空
        	if(!StringUtil.isNotEmpty(dto.getSelectedFlowAss1()) || dto.getSelectedFlowAss1().size() == 0){
        		 resultList.add(new ValidMessage("",getMessage("RSK_W_F_0001")));
        	}
        	//二级资产不能为空
        	if(!StringUtil.isNotEmpty(dto.getSelectedFlowAss2()) || dto.getSelectedFlowAss2().size() == 0){
                resultList.add(new ValidMessage("",getMessage("RSK_W_F_0002")));
            }
        	//一级二级资产不能重复
        	if(StringUtil.isNotEmpty(dto.getSelectedFlowAss1()) && StringUtil.isNotEmpty(dto.getSelectedFlowAss2())){
        	    List<String> flowAss1 = new ArrayList<>();
        	    List<String> flowAss2 = new ArrayList<>();
        	    for(FlowRiskFlowAssets001Dto dto1:dto.getSelectedFlowAss1()){
        	        flowAss1.add(dto1.getName());
        	    }
        	    for(FlowRiskFlowAssets001Dto dto2:dto.getSelectedFlowAss2()){
                    flowAss2.add(dto2.getName());
                }
        	    //一级二级资产同一级下不能重复
        	    List<String> intersection = flowAss1.stream().filter(item -> flowAss2.contains(item)).collect(Collectors.toList());
                if(intersection !=null && !intersection.isEmpty() ){
                    for(String flowAs:intersection){
                        resultList.add(new ValidMessage("", getMessage("RSK_W_F_0003",flowAs)));
                    }
                    
                }
                //一级二级资产不同级下不能重复
                for(FlowRiskFlowAssets001Dto entity:dto.getSelectedFlowAss1()){
                	for(FlowRiskFlowAssets001Dto entity2:dto.getSelectedFlowAss2()){
                		//一级中的父节点（E表示为父节点）
                    	if(StringUtil.equals(entity.getpId(), "E")){
                    		//二级中的子节点
                    		if(!StringUtil.equals(entity2.getpId(), "E")){
                    			if(StringUtil.equals(entity.getId(), entity2.getpId())){
                    				resultList.add(new ValidMessage("", getMessage("RSK_W_F_0011",entity.getName(),entity2.getName())));
                    			}
                    		}
                    	}
                    	//一级 中的子节点
                    	else{
                    		//二级中的父节点
                    		if(StringUtil.equals(entity2.getpId(), "E")){
                    			if(StringUtil.equals(entity2.getId(), entity.getpId())){
                    				resultList.add(new ValidMessage("", getMessage("RSK_W_F_0012",entity2.getName(),entity.getName())));
                    			}
                    		}
                    	}
                	}
                	
                }
                
                
           }
        }
        return resultList;
    }

}
