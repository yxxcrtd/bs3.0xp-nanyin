/*
 * 文件名：PtlActivityPeriod003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.service.PtlActivityPeriod002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年12月16日
 * @see PtlActivityPeriod003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlActivityPeriod003")
@RequestMapping("/invest/portfolio/ptlactivityperiod/PtlActivityPeriod003")
public class PtlActivityPeriod003Controller extends BaseController{
    
    @Autowired
    private PtlActivityPeriod002Service ptlActivityPeriod002Service;
    
    @SuppressWarnings("unchecked")
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        Map<String,Object> map = RequestUtil.getPageParam();
        List<PtlActivityPeriod001Dto> list = new ArrayList<PtlActivityPeriod001Dto>();
        String childrenDetailsJson = (String)map.get("childrenDetailsJson");
        if(StringUtil.isNotEmpty(childrenDetailsJson)){
            PtlActivityPeriod001Dto childrenDetailsDtoArr[] = (PtlActivityPeriod001Dto[])JsonUtils.json2Object(childrenDetailsJson,
                PtlActivityPeriod001Dto[].class);  
            for(PtlActivityPeriod001Dto childrenDetailsDto:childrenDetailsDtoArr){
                list.add(childrenDetailsDto);
            }
        } 
        
        if(StringUtil.isEmptyList(list)) {
            if (StringUtil.isNotEmpty(workflowId) && !StringUtil.equals(workflowId, "null")) {
                PtlActivityPeriod001Dto dto = ptlActivityPeriod002Service.getBussinessEntityByWorkflowId(workflowId);
                if(StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getDetailsList())) {
                    for(PtlActivityPeriod001Dto detailsDto:dto.getDetailsList()) {
                        if(StringUtil.equals(detailsDto.getIsParentPortfolio(), DictCodeList.是否_是.getValue())) {
                            list = detailsDto.getChildrenDetailsList();
                        }
                    }
                }
             } else if(StringUtil.isNotEmpty(map.get("busiNo")) &&WorkFlowUtil.isNotFromToDoList()){
                 list = ptlActivityPeriod002Service.findDetailsByBusiNo(map);
             } else {
                 list = ptlActivityPeriod002Service.findChildrenPortfolioDetails(map);
             }  
        }
        model.addAttribute("childrenPortfolioDetails", list);
        return super.init(model); 
    }
    
}
