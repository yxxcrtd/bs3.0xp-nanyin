/*
 * 文件名：ValuationSolutionManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dto.ValuationSolutionManage001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.service.ValuationSolutionManage001Service;


@Controller
@PageId("ValuationSolutionManage001")
@RequestMapping("/valuation/valuationparametermanage/valuationsolution/ValuationSolutionManage001")
public class ValuationSolutionManage001Controller extends BaseController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValuationSolutionManage001Service valuationSolutionManage001Service;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValuationSolutionManage001Dto> queryPage()
        throws Exception {
        Page<ValuationSolutionManage001Dto> page = valuationSolutionManage001Service.findPage(RequestUtil.getParameters(getRequest()));
        return page;
    }
    @ResponseBody
    @RequestMapping("selectSolutionList")
    public List<SelectOptionDto> selectSolutionList()
        throws Exception {
        return valuationSolutionManage001Service.selectSolutionList();
    }
    
}
