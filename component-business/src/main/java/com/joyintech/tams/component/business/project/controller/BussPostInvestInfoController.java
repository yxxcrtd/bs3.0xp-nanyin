/*
 * 文件名：BussPostInvestInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.project.BussPostInvestInfoService;
import com.joyintech.tams.component.business.project.dto.BussPostInvestInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 投后信息--业务组件
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月26日
 * @see BussPostInvestInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/project/BussPostInvestInfo")
public class BussPostInvestInfoController extends BaseController{

    @Autowired
    private BussPostInvestInfoService bussPostInvestInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
        // 画面参数设置
        setSelectOptionToModel(model, bussPostInvestInfoService.getInitDictCode());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussPostInvestInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("BussPostInvestInfo_PageId",this.getParam("pageId"));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        //主画面资产类别id
        model.addAttribute("TYPE6_ID", this.getParam("type6Id"));
        model.addAttribute("ASSET_TYPE_PROJECT_01", DictCodeList.项目类一级分类_非标债权.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_02", DictCodeList.项目类一级分类_结构化证券.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_03", DictCodeList.项目类一级分类_其他权益类投资.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_04", DictCodeList.项目类一级分类_其他产业投资基金.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_05", DictCodeList.项目类一级分类_股票定向增发投资.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_06", DictCodeList.项目类一级分类_基础设施基金.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_07", DictCodeList.项目类一级分类_券商债权收益权投资.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_08", DictCodeList.项目类一级分类_信贷资产流转项目.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_09", DictCodeList.项目类一级分类_券商收益凭证.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_10", DictCodeList.项目类一级分类_股票质押式回购.getValue());
        model.addAttribute("ASSET_TYPE_PROJECT_11", DictCodeList.项目类一级分类_理财直接融资工具.getValue());
        model.addAttribute("counterType",DictCodeListExt1.交易对手类型_分行.getValue());
        // 设置画面初始值
        setEntity(model);
        return "component/project/BussPostInvestInfo";
    }
    
    private void setEntity(Model model) {
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussPostInvestInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体--投后信息
            BussPostInvestInfoDto bussPostInvestInfoDto = new BussPostInvestInfoDto();
            FinPostInvestInfo finPostInvestInfo = bussPostInvestInfoService.getBussPostInvestInfoByProductId(finprodId);
            if(finPostInvestInfo!=null){
                BeanUtil.copyPropertiesIgnoreNull(bussPostInvestInfoDto, finPostInvestInfo);
            }
            model.addAttribute(GlobalConstant.ENTITY, bussPostInvestInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussPostInvestInfoDto bussPostInvestInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussPostInvestInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussPostInvestInfoDto);
        }
    }

    /**
     * 投后管理机构
     * */
    @RequestMapping("/selectCounterId")
    @ResponseBody
    public Page<SelectOptionDto> selectCounterId(){
        Map<String, Object> params = RequestUtil.getPageParam();
        return bussPostInvestInfoService.selectCounterId(params);
    }
    /**
     * 重写pageId
     * {@inheritDoc}
     */
    @Override
    public String getPageId(){
        return this.getParam(GlobalConstant.PAGE_ID);
    }
}
