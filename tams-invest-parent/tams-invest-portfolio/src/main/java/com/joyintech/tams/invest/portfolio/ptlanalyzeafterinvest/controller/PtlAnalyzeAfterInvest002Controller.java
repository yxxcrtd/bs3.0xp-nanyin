/*
 * 文件名：PtlAnalyzeAfterInvest002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.3.1
 * 修改人：王贵奇
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.3.1
 */
package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest002Dto;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.service.PtlAnalyzeAfterInvest002Service;

/**
 * 质押股票盯市 
 * @author 王贵奇
 * @version 1.0 2019年10月23日
 * @since 1.0
 */
@Controller
@PageId("PtlAnalyzeAfterInvest002")
@RequestMapping("/invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest002")
public class PtlAnalyzeAfterInvest002Controller extends BaseController{
	
	/**
     *ptlAnalyzeAfterInvest002Service
     */
    @Autowired
    private PtlAnalyzeAfterInvest002Service ptlAnalyzeAfterInvest002Service;
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	
    	model.addAttribute("finprodId", getParam("finprodMarketId"));
    	model.addAttribute("finprodName", getParam("finprodAbbr"));
    	model.addAttribute("pledgeStockId", getParam("pledgeStockId"));
    	model.addAttribute("pledgeStockName", getParam("pledgeStockName"));
    	model.addAttribute("warningLine", getParam("warningLine"));
    	model.addAttribute("closeLine", getParam("closeLine"));
    	
    	
        return super.init(model);
    }
    
    /**
     * 获取行情波动图数据源
     * @return Page<PtlAnalyzeAfterInvest002Dto>
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getSourceData")
    public List<PtlAnalyzeAfterInvest002Dto> getSourceData(@RequestBody Map<String, Object> param)
            throws Exception {
        return ptlAnalyzeAfterInvest002Service.getSourceData(param);
    }
    /**
     * 导出
     * @return ResponseEntity<byte[]>
     * @throws Exception
     */
//    @ResponseBody
//    @RequestMapping("export")
//    public ResponseEntity<byte[]> export()
//            throws Exception {
//        return ptlAnalyzeAfterInvest002Service.export(RequestUtil.getParameters(getRequest()));
//    }
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlAnalyzeAfterInvest002Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
