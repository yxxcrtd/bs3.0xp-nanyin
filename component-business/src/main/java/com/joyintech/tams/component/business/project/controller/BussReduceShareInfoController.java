/*
 * 文件名：BussReduceShareInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
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

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.project.BussReduceShareInfoService;
import com.joyintech.tams.component.business.project.dto.BussReduceShareInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  减持信息--业务组建
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/project/BussReduceShareInfo")
public class BussReduceShareInfoController extends BaseController{

    @Autowired
    private BussReduceShareInfoService bussReduceShareInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussReduceShareInfoService.DEFAULT_ENTITY_NAME);
        model.addAttribute("entityName", entityName);
        return "component/project/BussReduceShareInfo";
    }
    
    /**
     * 初始化减持列表
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initInfo")
    @ResponseBody
    public List<FinReduceShareInfo> initInfo() {
        List<FinReduceShareInfo> finReduceShareInfoList = new ArrayList<FinReduceShareInfo>();
        String workflowId = this.getParam("workflowId");
        String finprodId = this.getParam("finprodId");
        if(StringUtil.isNotEmpty(workflowId)){
            String workFlowId = this.getParam("workflowId");
            String entityName = StringUtil.defaultString(this.getParam("entityName"),
                BussReduceShareInfoService.DEFAULT_ENTITY_NAME);
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussReduceShareInfoDto bussReduceShareInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussReduceShareInfoDto.class);
            if(null!=bussReduceShareInfoDto){
                finReduceShareInfoList = bussReduceShareInfoDto.getFinReduceShareInfoList();
            }
        }else if(StringUtil.isNotEmpty(finprodId)){
            //查询减持信息
            finReduceShareInfoList = bussReduceShareInfoService.getBussReduceShareInfoByProductId(finprodId);
        }
        return finReduceShareInfoList;
    }
}
