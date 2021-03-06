/*
 * 文件名：BussRepAssetAstBillInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.controller;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetAstBillInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetAstBillInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepAssetAstBillInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/project/BussRepAssetAstBillInfo")
public class BussRepAssetAstBillInfoController extends BaseController{

    @Autowired
    private BussRepAssetAstBillInfoService bussRepAssetAstBillInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepAssetAstBillInfoService.getInitDictCode());
        setSelectOptionToModel(model, DictCode.是否_中债);
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepAssetAstBillInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("CHB_ASSET_TYPE_PROJECT_1201", DictCodeListExt2.资产_负债二级类别_票据类.getValue());
        model.addAttribute("CHB_ASSET_TYPE_PROJECT_1210", DictCodeListExt2.资产_负债二级类别_信用证.getValue());
        return "component/report/asset/project/BussRepAssetAstBillInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepAssetAstBillInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussRepAssetAstBillInfoDto bussRepAssetAstBillInfoDto = bussRepAssetAstBillInfoService.getBussRepAssetAstBillInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepAssetAstBillInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepAssetAstBillInfoDto bussRepAssetAstBillInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepAssetAstBillInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepAssetAstBillInfoDto);
        }
    }
}
