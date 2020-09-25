/*
 * 文件名：BussRepAssetMngProdInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.plan.controller;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.plan.BussRepAssetMngProdInfoService;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;

/**
 * 资产管理计划报送信息组件Controller
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/plan/BussRepAssetMngProdInfo")
public class BussRepAssetMngProdInfoController extends BaseController{
    /**
     * 资产管理计划中债报送信息组件接口
     */
    @Autowired(required = false)
    private BussRepAssetMngProdInfoService bussRepAssetMngProdInfoService;

    /**
     * 工作流共通服务接口
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.交易流通场所, DictCode.资金实际投向,
            DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,DictCode.资管计划属性,DictCode.是否_中债,
            DictCode.购买结构,DictCode.管理方式,DictCode.资产_负债二级类别,DictCode.是否});
        model.addAttribute("FLAG_YES",DictCodeList.是否_中债_是.getValue());
        model.addAttribute("TRADE_MARKET_99",DictCodeList.交易流通场所_其他交易流通场所.getValue());
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME));
        // 设置画面初始值
        setEntity(model);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return "component/report/asset/plan/BussRepAssetMngProdInfo";
    }

    /**
     * 设置界面初始值
     * @param model
     */
    private void setEntity(Model model) {
        String workflowId= StringUtil.defaultString(this.getParam("workflowId"));
        String finprodId = StringUtil.defaultString(this.getParam("finprodId"));
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            BussRepAssetMngProdInfo param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId, "bussRepAssetMngProdInfo", BussRepAssetMngProdInfo.class);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        else if(StringUtil.isNotEmpty(finprodId) && null!=bussRepAssetMngProdInfoService){
            BussRepAssetMngProdInfo param = bussRepAssetMngProdInfoService.getByFinprodId(finprodId);
            //add by yzg 通道查看费率X100 20200408
            if(StringUtil.equals("ChannelAsset002", this.getParam("pageId"))) {
            	param.setRepMaxRate(ArithmeticUtil.multiply(param.getRepMaxRate(),new BigDecimal(100)));
            	param.setRepMinRate(ArithmeticUtil.multiply(param.getRepMinRate(),new BigDecimal(100)));
            	param.setRepMgrFeeRate(ArithmeticUtil.multiply(param.getRepMgrFeeRate(),new BigDecimal(100)));
            	param.setRepTruFeeRate(ArithmeticUtil.multiply(param.getRepTruFeeRate(),new BigDecimal(100)));
            	param.setRepTradeFeeRate(ArithmeticUtil.multiply(param.getRepTradeFeeRate(),new BigDecimal(100)));
            	param.setRepAgencyFeeRate(ArithmeticUtil.multiply(param.getRepAgencyFeeRate(),new BigDecimal(100)));
            	param.setRepOtherFeeRate(ArithmeticUtil.multiply(param.getRepOtherFeeRate(),new BigDecimal(100)));
            }
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        else{
            BussRepAssetMngProdInfo bussRepAssetMngProdInfo = new BussRepAssetMngProdInfo();
            bussRepAssetMngProdInfo.setRepPlanProp(DictCodeListExt2.资管计划属性_集合.getValue());
            bussRepAssetMngProdInfo.setRepBuyStruct(DictCodeListExt2.购买结构_优先.getValue());
            bussRepAssetMngProdInfo.setRepManageType(DictCodeListExt2.管理方式_主动_自主管理.getValue());
            bussRepAssetMngProdInfo.setRepIsCtrlBottom(DictCodeList.是否_是.getValue());
            model.addAttribute(GlobalConstant.ENTITY, bussRepAssetMngProdInfo);
        }
    }

    /**
     * 重写pageId {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
}
