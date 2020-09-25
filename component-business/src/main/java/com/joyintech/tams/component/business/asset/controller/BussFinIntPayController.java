/*
 * 文件名：BussFinIntPay.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.asset.BussFinIntPayService;
import com.joyintech.tams.component.business.asset.dto.BussFinIntPayDto;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 分红计划组件
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see BussFinIntPayController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/asset/BussFinIntPay")
public class BussFinIntPayController extends BaseController{
    /**
     * bussFinIntPayService
     */
    @Autowired(required = false)
    private BussFinIntPayService bussFinIntPayService;
    
    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 初始化开放计划组件的画面元素
        setSelectOptionToModel(model, new DictCode[] {DictCode.收益结转频率,
            DictCode.营业日准则, DictCode.分红方式});
        model.addAttribute("Cycle_01", DictCodeList.收益结转频率_按日结转.getValue());
        model.addAttribute("Cycle_02", DictCodeList.收益结转频率_按月结转.getValue());
        // 画面参数设置
        model.addAttribute("fundType", StringUtil.defaultString(this.getParam("fundType")));
        model.addAttribute("h_01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("fundTypeElement", this.getParam("fundTypeElement"));
        model.addAttribute("bussFinIntPayElement", this.getParam("bussFinIntPayElement"));    
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussFinIntPayService.DEFAULT_ENTITY_NAME));
        setEntity(model);
        return "component/asset/BussFinIntPay";
    }
    /**
     * 设置分红计划
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workFlowId = StringUtil.defaultString(this.getParam("workflowId"));
        String finprodId = StringUtil.defaultString(this.getParam("finprodId"));
        // 20191025 wangguiqi 修改 来自待办时 计划类货币型资产 是否按日复利 被置空
        model.addAttribute("isNotFromToDoList", WorkFlowUtil.isNotFromToDoList());
        if(StringUtil.isNotEmpty(workFlowId)){
            BussFinIntPayDto param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, "bussFinIntPayDto", BussFinIntPayDto.class);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }
        //        修改快录时分红计划要素带不出来问题 by zhuyongkang
        else if(StringUtil.isNotEmpty(finprodId) && null != bussFinIntPayService) {
                    BussFinIntPayDto param = bussFinIntPayService.getByFinprodId(finprodId);
                    model.addAttribute(GlobalConstant.ENTITY, param);
                }
            else{
                BussFinIntPayDto payDto = new BussFinIntPayDto();
                payDto.setPayType(DictCodeList.分红方式_红利再投.getValue());
                payDto.setPayCycle(DictCodeList.收益结转频率_按日结转.getValue());
                payDto.setEndTdayRule(DictCodeList.营业日准则_向后调整.getValue());
                payDto.setPayTdayRule(DictCodeList.营业日准则_向后调整.getValue());
                model.addAttribute("entity", payDto);
            }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        String pageId = getParam("pageId");
        return pageId;
    }
}
