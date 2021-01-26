/*
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.project.BussSubscriptionInfoService;
import com.joyintech.tams.component.business.project.dto.BussSubscriptionInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/project/BussSubscriptionInfo")
public class BussSubscriptionInfoController extends BaseController{

    @Autowired
    private BussSubscriptionInfoService bussSubscriptionInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussSubscriptionInfoService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityName", entityName);
        model.addAttribute("isToDoList", !WorkFlowUtil.isNotFromToDoList());
        return "component/project/BussSubscriptionInfo";
    }
    
    /**
     * 初始化认购列表
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initInfo")
    @ResponseBody
    public List<FinSubscriptionInfo> initInfo() {
        List<FinSubscriptionInfo> finSubscriptionInfoList = new ArrayList<FinSubscriptionInfo>();
        String finprodId = this.getParam("finprodId");
        String isToDoList = this.getParam("isToDoList");
        if(StringUtil.isNotEmpty(finprodId)){
            if(StringUtil.equals(isToDoList,"true")){
                String workFlowId = this.getParam("workflowId");
                String entityName = StringUtil.defaultString(this.getParam("entityName"),
                    BussSubscriptionInfoService.DEFAULT_ENTITY_NAME);
                //根据工作流与留痕处理提供的共通方法获取业务实体
                BussSubscriptionInfoDto bussSubscriptionInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussSubscriptionInfoDto.class);
                if(null!=bussSubscriptionInfoDto){
                    finSubscriptionInfoList = bussSubscriptionInfoDto.getFinSubscriptionInfoList();
                }
            }else{
                //查询减持信息
                finSubscriptionInfoList = bussSubscriptionInfoService.getBussSubscriptionInfoByProductId(finprodId);
            }
        }
        return finSubscriptionInfoList;
    }
}
