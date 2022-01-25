/*
 * 文件名：TrdLendInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTrade003Dto;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service.TrdLendValuationTrade003Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 拆借投资交易新建
 */
@Controller
@PageId("TrdLendValuationTrade003")
@RequestMapping("/valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade003")
public class TrdLendValuationTrade003Controller extends BaseController {
//	@Autowired
//	private TradeCommonService tradeCommonService;
//
//    /**
//     * trdLendInvestTrade002Service
//     */
    @Autowired
    private TrdLendValuationTrade003Service trdLendValuationTrade003Service;


    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.拆借方向, DictCode.拆借类型,
            DictCode.有效状态, DictCode.币种, DictCode.计息基础);// 新增资产二、三、四级分类 王贵奇 2019/8/1
        // 一体化对应etliu start
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
        // 一体化对应etliu end

        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        String portfolioId = getParam("portfolioId");
        String tradeId = getParam("tradeId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            TrdLendValuationTrade003Dto entity = trdLendValuationTrade003Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("entity",entity);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("tradeId", entity.getTradeId());
        }else if (!StringUtil.isNotEmpty(workflowId) && StringUtil.isNotEmpty(tradeId)){
            TrdLendValuationTrade003Dto entity = trdLendValuationTrade003Service.queryTradeInfoByTradeId(tradeId);
            model.addAttribute("entity", entity);
        }else{
            //初始化值
            TrdLendValuationTrade003Dto entity = new TrdLendValuationTrade003Dto();
//            entity.setOtherAmt(new BigDecimal("0.00"));   //其他费用
            entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            model.addAttribute("entity", entity);
        }
//        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        return super.init(model);

    }


    /**
     * 保存按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdLendValuationTrade003Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    
}
