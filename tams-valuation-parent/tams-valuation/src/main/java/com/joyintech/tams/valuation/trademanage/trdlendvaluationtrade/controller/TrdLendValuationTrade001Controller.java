/*
 * 文件名：TrdLendInvestTrade001.java
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;

import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service.TrdLendValuationTrade001Service;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service.TrdLendValuationTrade002Service;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.dto.TrdPlanValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 拆借投资交易查询
 */
@Controller
@PageId("TrdLendValuationTrade001")
@RequestMapping("/valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade001")
public class TrdLendValuationTrade001Controller extends BaseController {

    /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdLendValuationTrade001Service trdLendValuationTrade001Service;

    /**
     * TrdLendInvestTrade002Service
     */
    @Autowired
    private TrdLendValuationTrade002Service trdLendValuationTrade002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.拆借方向, DictCode.拆借类型,
                DictCode.交易流程状态);
        return super.init(model);
    }

    /**
     * @Description:页面查询，表格分页
     * @auther: yuzj
     * @date: 2021/12/30
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdLendValuationTradeDto> findPage()
            throws Exception {
        Page<TrdLendValuationTradeDto> page = trdLendValuationTrade001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }


}
