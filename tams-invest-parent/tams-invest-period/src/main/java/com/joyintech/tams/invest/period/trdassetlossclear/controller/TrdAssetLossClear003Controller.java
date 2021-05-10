/*
 * 文件名：TrdFundDuration002Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：dingzhipeng
 * 修改时间：2018年10月15日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdassetlossclear.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdassetlossclear.dto.TrdAssetLossClear001Dto;
import com.joyintech.tams.invest.period.trdassetlossclear.service.TrdAssetLossClear001Service;
import com.joyintech.tams.invest.period.trdplannetduration.dto.TrdPlanNetDuration001Dto;
import com.joyintech.tams.invest.period.trdplannetduration.service.TrdPlanNetDuration001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author dingzhipeng
 * @version 1.0 2018年10月15日
 * @see TrdAssetLossClear002Controller
 * @since 1.0
 */
@PageId("TrdAssetLossClear003")
@RequestMapping("/invest/period/trdassetlossclear/TrdAssetLossClear003")
@Controller
public class TrdAssetLossClear003Controller extends BaseController {
    
    @Autowired
    private TrdAssetLossClear001Service trdAssetLossClear001Service;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	String totleTradeId = getParam("totleTradeId");
    	String finprodId = getParam("finprodId");   	
    	model.addAttribute("totleTradeId", totleTradeId);
    	model.addAttribute("finprodId", finprodId);
    	if(StringUtil.isNotEmpty(getParam("tradeDate"))){
    		model.addAttribute("clearDate", DateUtil.format(new Date(Long.parseLong(getParam("tradeDate")))));	
    	}    	
        return super.init(model);
    }
    
    /**
     * 新建页面画面联动
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("selectByKey")
    public Page<TrdAssetLossClear001Dto> selectByKey() throws Exception{
        String totleTradeId = getParam("totleTradeId");
        Page<TrdAssetLossClear001Dto> dto = trdAssetLossClear001Service.selectByKey(totleTradeId);
        return dto;
    }
   
}
