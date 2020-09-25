/*
 * 文件名：BussRepOutInvestAgrInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.trust.controller;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.trust.BussRepOutInvestAgrInfoService;
import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;

/**
 * 委外投资—协议方式中债报送信息组件Controller
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/trust/BussRepOutInvestAgrInfo")
public class BussRepOutInvestAgrInfoController extends BaseController{
    /**
     * 资产管理计划中债报送信息组件接口
     */
    @Autowired(required = false)
    private BussRepOutInvestAgrInfoService bussRepOutInvestAgrInfoService;

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
        setSelectOptionToModel(model, new DictCode[] {DictCode.资金实际投向,DictCode.交易流通场所,
            DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,DictCode.委外投资属性_中债,DictCode.是否,DictCode.是否_中债,
            DictCode.资产_负债二级类别});
        model.addAttribute("TRADE_MARKET_99",DictCodeList.交易流通场所_其他交易流通场所.getValue());
        model.addAttribute("FLAG_YES",DictCodeList.是否_是.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME));
        // 设置画面初始值
        setEntity(model);
        return "component/report/asset/trust/BussRepOutInvestAgrInfo";
    }
    /**
     * 设置界面初始值
     * @param model
     */
    private void setEntity(Model model) {
        String workflowId= this.getParam("workflowId");
        String finprodId = this.getParam("finprodId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            BussRepOutInvestAgrInfo param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId, "bussRepOutInvestAgrInfo", BussRepOutInvestAgrInfo.class);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        else if(StringUtil.isNotEmpty(finprodId) && null!=bussRepOutInvestAgrInfoService){
            BussRepOutInvestAgrInfo param = bussRepOutInvestAgrInfoService.getByFinprodId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        else{
            BussRepOutInvestAgrInfo bussRepOutInvestAgrInfo = new BussRepOutInvestAgrInfo();
            bussRepOutInvestAgrInfo.setRepTrustInvestAttribute(DictCodeList.委外投资属性_中债_集合管理.getValue());
            model.addAttribute(GlobalConstant.ENTITY, bussRepOutInvestAgrInfo);
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
