/*
 * 文件名：MaintainProductRedemtionDataController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhaoyang
 * 修改时间：2019年8月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.forecast.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastDealAmontDto;
import com.joyintech.tams.invest.product.forecast.service.FinFlowForecastService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 流动性预测表维护-产品申赎数据
 * 〈功能详细描述>
 * @author zhangzhaoyang
 * @version 1.0 2019年8月9日
 * @see MaintainProductRedemtionDataController
 * @since 1.0
 */
@Controller
@PageId("MaintainProductRedemptionData001")
@RequestMapping("/invest/product/forecast/MaintainProductRedemptionData001")
public class MaintainProductRedemtionDataController extends BaseController {
    
   
    
    @Autowired
    private FinFlowForecastService flowForecastService;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        /*FlowForecastConfig entity = new FlowForecastConfig();
        entity.setQueryDate(DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        model.addAttribute("entity", entity);*/
        return super.init(model);
    }
    
    /**
     * 查询
     * 
     * @return List<FlowForecastDealAmontDto>
     * @exception Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findMaintainProductRedemptionData")
    public Page<FinFlowForecastDealAmontDto> findMaintainProductRedemptionData()
        throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        List<String> accountList = new ArrayList<String>();
        String localAccounts = (String)params.get("localAccount");
        if(StringUtil.isNotEmpty(localAccounts)){
            String[] localAccountsArr = localAccounts.split(",");
            for (String localAccount : localAccountsArr) {
                accountList.add(localAccount);
            }
            params.put("localAccount", accountList);
            Page<FinFlowForecastDealAmontDto> maintainProductRedemptionDataList = flowForecastService.findMaintainProductRedemptionData(
                params);
            return maintainProductRedemptionDataList;
        }else{
            //本方账户为空时查询所有账户
            accountList.add("F16_0900099C93");
            accountList.add("F16_0800099C91");
            accountList.add("F16_1100099C88");
            accountList.add("F16_1300099C89");
            accountList.add("F16_1801ZQ0244");
            accountList.add("F16_1801ZQ0406");
            accountList.add("F16_1801LB0001");
            params.put("localAccount", accountList);
            Page<FinFlowForecastDealAmontDto> maintainProductRedemptionDataList = flowForecastService.findMaintainProductRedemptionData(
                params);
            return maintainProductRedemptionDataList;
        }
    }
    
    /**
     *检查
     */
    @ResponseBody
    @RequestMapping("check")
    public String check() {
        
        String plateFormDate = DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN);
       
        
        return plateFormDate;
    }
}