/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.service.MarketRiskStressTestView002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月13日
 * @see MarketRiskStressTestView002Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskStressTestView002")
@RequestMapping("/invest/risk/stresstestmanage/marketriskstresstestview/MarketRiskStressTestView002")
//invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTestView002
public class MarketRiskStressTestView002Controller extends BaseController{
    @Autowired(required = false)
    private MarketRiskStressTestView002Service marketRiskStressTestView002Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
    	//this.setSelectOptionToModel(model, DictCode.开放模式); // 适用产品
        model.addAttribute("entity1", RequestUtil.getPageParam());
        Map<String, Object> pageParamsMap=RequestUtil.getPageParam();//全部参数map
        model.addAttribute("testId", pageParamsMap.get("testId"));//测试编号
        model.addAttribute("testName", pageParamsMap.get("testName"));//测试名称
        
        model.addAttribute("sceneId", pageParamsMap.get("sId"));//情景编号
        model.addAttribute("sceneName", pageParamsMap.get("sName"));//情景名称
        if((pageParamsMap.get("sId"))!=null && (pageParamsMap.get("sName"))!=null){
        model.addAttribute("sceneIdAndsceneName", (pageParamsMap.get("sId").toString())+':'+(pageParamsMap.get("sName").toString()));
        }
        
        model.addAttribute("openMode", pageParamsMap.get("openMode"));//适用产品--编号
        model.addAttribute("openModeStr", pageParamsMap.get("openModeStr"));//适用产品--中文
        model.addAttribute("startTime", pageParamsMap.get("startTime"));//压力测试执行时间
        model.addAttribute("baseDate", pageParamsMap.get("baseDate"));//分析基准日
        model.addAttribute("analysisVDate", pageParamsMap.get("analysisVDate"));//分析开始日期
        model.addAttribute("analysisMDate", pageParamsMap.get("analysisMDate"));//分析结束日期
        model.addAttribute("remark", pageParamsMap.get("remark"));//压力测试备注
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("findOnePage")
    public Page<MarketRiskStressTestView002Dto> selectForPage()
        throws Exception {
       Map<String, Object> map = RequestUtil.getParameters(getRequest());
       Page<MarketRiskStressTestView002Dto> page = marketRiskStressTestView002Service.selectAll(map);
        return page;
    }
    @RequestMapping("export")
	public ResponseEntity<byte[]> export()
            throws Exception {
    	Map<String, Object> Params=RequestUtil.getParameters(getRequest());
    	//树状图导出功能:此处写比较猥琐，为了展示报告时将现金余额放在一起，导出时不影响树状结构
    	Params.put("isExport", "Y");
        return marketRiskStressTestView002Service.export(this.getPageId(),Params);
    }
}
