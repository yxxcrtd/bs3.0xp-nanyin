/*
 * 文件名：TrdForwexInvestTrade001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.forwex.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeDto;
import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.forwex.service.TrdForwexInvestTrade001Service;
import com.joyintech.tams.invest.trade.forwex.service.TrdForwexInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  外汇远期交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月2日
 * @see TrdForwexInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdForwexInvestTrade001")
@RequestMapping("/invest/trade/forwex/TrdForwexInvestTrade001")
public class TrdForwexInvestTrade001Controller extends BaseController {

	@Autowired
	private TrdForwexInvestTrade001Service trdForwexInvestTrade001Service;
	
	@Autowired
	private TrdForwexInvestTrade002Service trdForwexInvestTrade002Service;
	
	/**
	 * 初始化界面
	 * {@inheritDoc}
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.外汇交易类型, DictCode.货币对,DictCode.交易方向,
        		DictCode.结算方式,DictCode.交易流程状态);
        return super.init(model);
    }
	
	 /**
     * 分页查询
     *
     * @return Page<TrdForwexInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdForwexInvestTradeDto> findPage()
        throws Exception {
        Page<TrdForwexInvestTradeDto> pageRet = trdForwexInvestTrade001Service.findPage(RequestUtil.getPageParam());
        return  pageRet;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     *
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdForwexInvestTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 撤销前check
     *
     * @param inputDto 画面输入参数dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdForwexInvestTradeRevokeDto inputDto,
                            BindingResult result) {
        return commonCheck(result, inputDto.getOperateType());
    }

    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdForwexInvestTradeDto inputDto)
        throws Exception {

        ProcessParameter<TrdForwexInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdForwexInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdForwexInvestTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdForwexInvestTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_外汇远期投资交易.getValue());
        workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment("交易编号：" + inputDto.getTradeId(),
        		"货币对：" + inputDto.getCurPair(),
            "交易日：" + DateUtil.format(inputDto.getTradeDate())));
        
        String msg = trdForwexInvestTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
	
}
