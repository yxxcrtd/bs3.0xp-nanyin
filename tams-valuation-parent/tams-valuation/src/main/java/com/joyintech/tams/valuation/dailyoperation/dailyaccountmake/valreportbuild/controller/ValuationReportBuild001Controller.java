/*
 * 文件名：ValuationReportBuild001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valreportbuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valreportbuild.service.ValuationReportBuild001Service;


/**
 * 估值报表生成
 * @author zhangwei
 * @version 1.0 2018年7月26日
 * @see ValuationReportBuild001Controller
 * @since 1.0
 */
@Controller
@PageId("ValuationReportBuild001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valreportbuild/ValuationReportBuild001")
public class ValuationReportBuild001Controller extends BaseController{

    @Autowired
    private ValuationReportBuild001Service valuationReportBuild001Service;
    
    /**
     * 估值报表生成，页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 根据条件分页查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BaseEntity> findPage() throws Exception{
        return valuationReportBuild001Service.findPage(RequestUtil.getPageParam());
    }
    
    
    
}
