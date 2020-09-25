/*
 * 文件名：BussRepAssetCreditNonstdInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.controller;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetCreditNonstdInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 信贷及非标资产报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepAssetCreditNonstdInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/project/BussRepAssetCreditNonstdInfo")
public class BussRepAssetCreditNonstdInfoController extends BaseController{

    @Autowired
    private BussRepAssetCreditNonstdInfoService bussRepAssetCreditNonstdInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepAssetCreditNonstdInfoService.getInitDictCode());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        // 设置画面初始值
        setEntity(model);
        // 中债新增 20200403 start
        setSelectOptionToModel(model, DictCode.计息类型);
        setSelectOptionToModel(model, DictCode.利息分布方式);
        setSelectOptionToModel(model, DictCode.结构档次);
        setSelectOptionToModel(model, DictCode.还本方式);
        setSelectOptionToModel(model, DictCode.分期还本条款标识);
        setSelectOptionToModel(model, DictCode.抵质押物类型);
        setSelectOptionToModel(model, DictCode.担保人与融资人关系);
        setSelectOptionToModel(model, DictCode.含权类型);
        setSelectOptionToModel(model, DictCode.担保性质);
        setSelectOptionToModel(model, DictCode.资产行权方式);
        setSelectOptionToModel(model, DictCode.永续条款类型);
        setSelectOptionToModel(model, DictCode.利息递延条款类型);
        setSelectOptionToModel(model, DictCode.是或否);

        model.addAttribute("SVR_PERPETUAL_CLAUSE_TYPE_03", DictCodeListExt1.永续条款类型_无.getValue());
        model.addAttribute("SVR_PERPETUAL_CLAUSE_TYPE_01", DictCodeListExt1.永续条款类型_可续期条款.getValue());
        model.addAttribute("SVR_PERPETUAL_CLAUSE_TYPE_02", DictCodeListExt1.永续条款类型_永续条款.getValue());
        model.addAttribute("SVR_DEFERRED_INTEREST_TYPE_01", DictCodeListExt1.利息递延条款类型_无.getValue());
        model.addAttribute("SVR_EXERCISE_TYPE_02", DictCodeListExt1.行权方式_监管报送_欧式期权.getValue());
        model.addAttribute("SVR_EXERCISE_TYPE_03", DictCodeListExt1.行权方式_监管报送_百慕大期权.getValue());
        model.addAttribute("SVR_GUA_TYPE_01", DictCodeListExt2.担保方式_信用担保.getValue());
        model.addAttribute("SVR_GUA_TYPE_02", DictCodeListExt2.担保方式_第三方保证.getValue());
        model.addAttribute("SVR_GUA_TYPE_03", DictCodeListExt2.担保方式_抵押.getValue());
        model.addAttribute("SVR_GUA_TYPE_04", DictCodeListExt2.担保方式_质押.getValue());
        model.addAttribute("SVR_CONTAIN_TYPE_01", DictCodeListExt1.含权类型_发行人赎回选择权.getValue());
        model.addAttribute("SVR_CONTAIN_TYPE_04", DictCodeListExt1.含权类型_投资人回售选择权.getValue());
        // 中债新增 20200403 end
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepAssetCreditNonstdInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        model.addAttribute("IS_SHOW_ASSIGNEE", CacheUtil.getSysParam("IS_SHOW_ASSIGNEE")==null?"01":CacheUtil.getSysParam("IS_SHOW_ASSIGNEE"));
        return "component/report/asset/project/BussRepAssetCreditNonstdInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepAssetCreditNonstdInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussRepAssetCreditNonstdInfoDto bussRepAssetCreditNonstdInfoDto = bussRepAssetCreditNonstdInfoService.getBussRepAssetCreditNonstdInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepAssetCreditNonstdInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepAssetCreditNonstdInfoDto bussRepAssetCreditNonstdInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepAssetCreditNonstdInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepAssetCreditNonstdInfoDto);
        }
    }
}
