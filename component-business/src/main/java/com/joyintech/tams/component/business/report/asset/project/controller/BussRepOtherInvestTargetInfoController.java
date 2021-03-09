/*
 * 文件名：BussRepOtherInvestTargetInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.controller;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.project.BussRepOtherInvestTargetInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepOtherInvestTargetInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 其他资产报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepOtherInvestTargetInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/project/BussRepOtherInvestTargetInfo")
public class BussRepOtherInvestTargetInfoController extends BaseController{

    @Autowired
    private BussRepOtherInvestTargetInfoService bussRepOtherInvestTargetInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepOtherInvestTargetInfoService.getInitDictCode());
        setSelectOptionToModel(model, DictCode.是否_中债);
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepOtherInvestTargetInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        return "component/report/asset/project/BussRepOtherInvestTargetInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepOtherInvestTargetInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussRepOtherInvestTargetInfoDto bussRepOtherInvestTargetInfoDto = bussRepOtherInvestTargetInfoService.getBussRepOtherInvestTargetInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepOtherInvestTargetInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepOtherInvestTargetInfoDto bussRepOtherInvestTargetInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepOtherInvestTargetInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepOtherInvestTargetInfoDto);
        }
    }
}
