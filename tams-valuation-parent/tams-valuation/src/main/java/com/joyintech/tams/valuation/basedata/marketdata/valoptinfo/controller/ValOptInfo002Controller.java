/*
 * 文件名：ValOptInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.service.ValOptInfo001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 场外期权标的
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOptInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ValOptInfo002")
@RequestMapping("/valuation/basedata/marketdata/valoptinfo/ValOptInfo002")
public class ValOptInfo002Controller extends BaseController {
	
    /**
     * ValOptInfo001Service
     */
    @Autowired
    private ValOptInfo001Service valOptInfo001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	String finprodId = getParam("finprodId");
		String operateType = getParam("operateType");
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		ValOptInfo001Dto dto = new ValOptInfo001Dto();
		if (StringUtils.isNotEmpty(finprodId)) {
			Map<String, Object> params = Maps.newHashMap();
    		params.put("finprodId", finprodId);
        	try {
				dto = valOptInfo001Service.findByTargetId(params).get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
		    dto = valOptInfo001Service.getBussinessEntityByWorkflowId(workflowId);
		}
		dto.setOperateType(operateType);
		model.addAttribute("entity", dto);
    	super.setSelectOptionToModel(model, DictCode.金融产品类型_投管用);
    	super.setSelectOptionToModel(model, DictCode.交易方向);
    	super.setSelectOptionToModel(model, DictCode.币种);
    	super.setSelectOptionToModel(model, DictCode.期权费支付类型);
    	super.setSelectOptionToModel(model, DictCode.估值交易市场);
    	super.setSelectOptionToModel(model, DictCode.结构类型);
        return super.init(model);
    }
    
}
