/*
 * 文件名：FinAssetConfigPlan002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.asset.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetConfigPlan001Dto;
import com.joyintech.tams.invest.product.productplanning.asset.service.FinAssetConfigPlan002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlan002Validator
 * @since 1.0
 */
@Component
public class FinAssetConfigPlan002Validator implements BeanValidator {

    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;
    @Autowired
    private FinAssetConfigPlan002Service finAssetConfigPlan002Service;
    
    /**
     * 验证如果生成日、基准日、配置月和Db中资产配置计划表相同，
     * 则提示数据库中已有改记录，请修改！																									
     *
     * @param Object value
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> messageList = new ArrayList<>();

        if (WorkFlowUtil.isNotFromToDoList()) {
        	FinAssetConfigPlan001Dto dto = (FinAssetConfigPlan001Dto)value;
        	Map<String, Object> param = new HashMap<String, Object>();
	    	param.put("benchDate", DateUtil.format(dto.getBenchDate()));
	    	param.put("genDate", DateUtil.format(dto.getGenDate()));
	    	param.put("configMonth", dto.getConfigMonth2());
	    	//生成日、基准日、配置月和Db中资产配置计划表相同,则提示数据库中已有改记录，请修改
        	List<AssetConfigPlan> list = finAssetConfigPlan002Service.queryAssetConfigPlan(param);
            if (list!=null && list.size()>0) {
                messageList.add(new ValidMessage(getMessage("FinAssetConfigPlan002_001")));
            }
        }
        return messageList;
	}

}
