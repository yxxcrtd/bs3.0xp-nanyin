/*
 * 文件名：BussFinProductFloatFeeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.FinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 浮动管理费
 * 
 * @author 张亮
 * @version 1.0 2018年10月11日
 * @see BussFinProductFloatFeeController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductFloatFee")
public class BussFinProductFloatFeeController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFloatFeeService bussFinProductFloatFeeService;

    @Override
    public String init(Model model) {
    	// 一体化对应etliu start
//        super.setSelectOptionToModel(model, DictCode.计费基数_费用品种, DictCode.计息基础,
//            DictCode.业绩基准规则, DictCode.资产净值日类型);
    	super.setSelectOptionToModel(model, DictCode.计费基数_费用品种, DictCode.计息基础,
                DictCode.业绩报酬计算规则, DictCode.基数日期类型);
        // 一体化对应etliu end
        
        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());

        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面收益类型
        model.addAttribute("profitType", StringUtil.defaultString(this.getParam("profitType")));
        // 父画面生效日期
        model.addAttribute("effDate", StringUtil.defaultString(this.getParam("effDate")));

        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID,
                this.getParam(GlobalConstant.WORKFLOW_ID));
            model.addAttribute(GlobalConstant.ENTITY,
                bussFinProductFloatFeeService.productFloatFeeInfoQueryByWflId(
                    this.getParam(GlobalConstant.WORKFLOW_ID),
                    StringUtil.defaultString(this.getParam("entityName"),
                        BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME)));
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            model.addAttribute(GlobalConstant.ENTITY,
                bussFinProductFloatFeeService.queryFloatFeeInfoByFinprodId(
                    this.getParam("finprodId"), this.getParam("effDate")));
        }

        return "component/prod/BussFinProductFloatFee";
    }

    /**
     * 查询费用品种信息下拉框
     * 
     * @return 费用品种信息下拉框
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeNameInfoList")
    public List<SelectOptionDto> queryFeeNameInfoList() {
        return bussFinProductFloatFeeService.queryFeeInfoList(RequestUtil.getPageParam());
    }

    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryMstFeeinfoByFeeId")
    public MstFeeinfo queryMstFeeinfoByFeeId(String feeId) {
        return bussFinProductFloatFeeService.queryMstFeeinfoByFeeId(feeId);
    }

    /**
     * 查询业绩报酬计算规则明细列表
     * 
     * @return 业绩报酬计算规则明细列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("productFloatFeeQuery")
    public List<FinProductFloatFeeDto> productFloatFeeQuery() {
        List<FinProductFloatFeeDto> list = new ArrayList<FinProductFloatFeeDto>();
        // 金融产品代码
        String finprodId = this.getParam("finprodId");
        // 工作流代码
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        // 生效日期
        String effDate = this.getParam("effDate");
        if (StringUtil.isNotEmpty(workflowId)
            && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            BussFinProductFloatFeeDto bussFinProductFloatFeeDto = bussFinProductFloatFeeService.productFloatFeeInfoQueryByWflId(
                workflowId, StringUtil.defaultString(this.getParam("entityName"),
                    BussFinProductFloatFeeService.DEFAULT_ENTITY_NAME));
            if (bussFinProductFloatFeeDto != null) {
                list = bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList();
            }

        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            list = bussFinProductFloatFeeService.queryFloatFeeDetailByFinprodId(finprodId,
                effDate);
        }
        return list;
    }

    /**
     * 根据金融产品代码查询浮动管理费信息
     * 
     * @param finprodId 金融产品代码
     * @return 浮动管理费信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFloatFeeInfoByFinprodId")
    public BussFinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId) {
        return bussFinProductFloatFeeService.queryFloatFeeInfoByFinprodId(finprodId, null);
    }
}
