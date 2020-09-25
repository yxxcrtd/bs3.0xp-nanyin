/*
 * 文件名：PtlAccountCurrentInterest003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest001Service;

/**
 * 账户活期利率信息显示
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlAccountCurrentInterest003")
@RequestMapping("/invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest003")
public class PtlAccountCurrentInterest003Controller extends BaseController{
    /**
     * 账户活期利率Service
     */
    @Autowired
    private PtlAccountCurrentInterest001Service ptlAccountCurrentInterest001Service;
    
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("cashId", (String)map.get("cashId"));
        model.addAttribute("effDate", (String)map.get("effDate"));
        model.addAttribute("rateType", (String)map.get("rateType"));
        return super.init(model);
    }
    /**
     * 利率信息查询
     * @return page对象
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("ratePage")
    public Page<PtlAccountCurrentInterest001Dto> findPage(){
        return ptlAccountCurrentInterest001Service.ratePage(RequestUtil.getPageParam());
    }
}
