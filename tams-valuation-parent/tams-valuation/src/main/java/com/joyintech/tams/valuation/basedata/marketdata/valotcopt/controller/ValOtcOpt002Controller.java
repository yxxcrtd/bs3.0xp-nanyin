/*
 * 文件名：ValOtcOpt002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valotcopt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valotcopt.service.ValOtcOpt001Service;

/**
 * 场外期权交易
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOtcOpt002Controller
 * @since 1.0
 */
@Controller
@PageId("ValOtcOpt002")
@RequestMapping("/valuation/basedata/marketdata/valotcopt/ValOtcOpt002")
public class ValOtcOpt002Controller extends BaseController {
	
    /**
     * ValOtcOpt001Service
     */
    @Autowired
    private ValOtcOpt001Service valOtcOpt001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	String totleTradeId = getParam("totleTradeId");
    	Map<String, Object> params = Maps.newHashMap();
		params.put("totleTradeId", totleTradeId);
		ValOtcOpt001Dto dto = new ValOtcOpt001Dto();
		try {
			dto = valOtcOpt001Service.findAll(params).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("entity", dto);
    	super.setSelectOptionToModel(model, DictCode.交易方向);
        super.setSelectOptionToModel(model, DictCode.期权费支付类型);
        super.setSelectOptionToModel(model, DictCode.交易流程状态);
        return super.init(model);
    }
    
    /**
     * 联动显示账套名称
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetName")
    @ResponseBody
    public ValOtcOpt001Dto getBooksetName() throws Exception {
        return valOtcOpt001Service.findAll(RequestUtil.getPageParam()).get(0);
    }
    
}
