/*
 * 文件名：PtlAnalyzeAfterInvest003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.3.1
 * 修改人：wangguiqi
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.3.1
 */
package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.service.PtlAnalyzeAfterInvest003Service;
/**
 * 触发统计
 * @author 王贵奇
 * @version 1.0 2019年10月23日
 * @see PtlAnalyzeAfterInvest003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlAnalyzeAfterInvest003")
@RequestMapping("/invest/portfolio/ptlanalyzeafterinvest/PtlAnalyzeAfterInvest003")
public class PtlAnalyzeAfterInvest003Controller extends BaseController{
	
	@Autowired
    PtlAnalyzeAfterInvest003Service ptlAnalyzeAfterInvest003Service;
	
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	model.addAttribute("categoryData", getParam("categoryData"));
    	model.addAttribute("values", getParam("values"));
    	model.addAttribute("warningLine", getParam("warningLine"));
    	model.addAttribute("closeLine", getParam("closeLine"));
    	
        return super.init(model);
    }
    
    /**
     * 导出
     * @return ResponseEntity<byte[]>
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlAnalyzeAfterInvest003Service.export(RequestUtil.getParameters(getRequest()));
    }
}
