/*
 * 文件名：TrdConBond003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.bond.controller.TrdBondInvestTrade002Controller;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 可转债申购确认
 *
 * @author Zane.Y
 * @version 1.0 2019年11月14日
 * @see TrdConBond003Controller
 * @since 1.0
 */
@Controller
@PageId("TrdConBond003")
@RequestMapping("/invest/trade/conbond/TrdConBond003")
public class TrdConBond003Controller extends BaseController {
	@Autowired
	private TradeCommonService tradeCommonService;
    /**
     * TrdConBond003Service
     */
    @Autowired
    private TrdConBond003Service trdConBond003Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Override
    public String init(Model model) {
        setSelectOptions(model);
        TrdConBond003Dto dto = new TrdConBond003Dto();
        String tradeId = this.getParam("busiCode");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String pageModel = getParam("pageModel");
        String ps = this.getParam("ps");
        String validStatus= this.getParam("validStatus");
        if (StringUtil.isNotEmpty(tradeId)&&(!StringUtil.equals(validStatus, DictCodeListExt1.交易流程状态_流程中.getValue()))) {
            dto = trdConBond003Service.getTrdConBond003Dto(tradeId);
            if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            	dto.setbTradeId(tradeId);
            	dto.setBussTrdTransferOrder(null);
            	dto.setBusiCode(null);
            	if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    dto.setPs(DictCodeList.交易方向_申购确认.getValue());
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                    dto.setPs(DictCodeListExt3.交易方向_退款到账.getValue());
                }
            }
            
            
        } 
        else if ((StringUtil.equals(validStatus, DictCodeListExt1.交易流程状态_流程中.getValue())&&(StringUtil.equals(ps, DictCodeListExt1.交易方向_保证金回款.getValue())||StringUtil.equals(ps, DictCodeListExt3.交易方向_退款到账.getValue())))
                ) {
        	String isFromCheck = this.getParam("isFromCheck");
        	String statusValid = this.getParam("statusValid");
        	if(StringUtil.equals(isFromCheck, "true")){
                if(StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())){
                	dto = trdConBond003Service.getTrdConBond003Dto(tradeId);
                }else{
                	dto = trdConBond003Service.getTrdConBondDtoForAccount(tradeId);
                	model.addAttribute("single", 1);//该字段判断是否来自来账分拣
                    model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                }
        		
            }else{
            	if(StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())){
                	dto = trdConBond003Service.getTrdConBond003Dto(tradeId);
                }else{
                	dto = trdConBond003Service.getTrdConBondDtoForTradeId(tradeId);
                }
            }
        }
        else if ((StringUtil.equals(validStatus, DictCodeListExt1.交易流程状态_流程中.getValue())&&!StringUtil.equals(ps, DictCodeListExt1.交易方向_保证金回款.getValue()))
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
                dto = trdConBond003Service.getBussinessEntityByWorkflowId(
                    workflowId);
                model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
                model.addAttribute("workflowId",workflowId);
            }
        else {
            String entrustId = this.getParam("entrustId");
            if (StringUtil.isNotEmpty(entrustId)) {
                dto.setEntrustId(entrustId);
            }
        }
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        if (StringUtil.isNotBlank(tradeId)&&!StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            model.addAttribute("tradeId", tradeId);
        }
//        else {
//            //zhujun BSBS-441@2019-05-20 兼容指令接收交易录单附件不显示问题
//            model.addAttribute("busiCode", dto.getBusiCode()==null?this.getParam("entrustId"):dto.getBusiCode());
//        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        }
        else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }
        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        model.addAttribute("formatDate",formatDate);
        model.addAttribute("entity", dto);
        model.addAttribute("entity_trdInvestPersonInfo",
            dto.getTrdInvestPersonInfo() == null ? null : JsonUtils.object2Json(
                dto.getTrdInvestPersonInfo()));
        // 划款信息
        if (dto != null && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_bussRepFinProductInfoDto",
                JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }

        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
        model.addAttribute("PS_T58", DictCodeListExt3.交易方向_退款到账.getValue());
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return super.init(model);
    }

    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所,
             DictCode.交易平台, DictCode.币种,
            DictCode.投资目的, DictCode.交易市场,  DictCode.是否,
            DictCode.申购方式,DictCode.申购类型,
            DictCode.WIND一级分类_含债券一级分类等);
        model.addAttribute("PURCH_MODLE_01", DictCodeListExt2.申购方式_网上申购.getValue());
        model.addAttribute("PURCH_MODLE_02", DictCodeListExt2.申购方式_线下申购.getValue());
        model.addAttribute("PURCH_TYPE_01", DictCodeListExt2.申购类型_优先配售.getValue());
        model.addAttribute("PURCH_TYPE_02", DictCodeListExt2.申购类型_普通申购.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("IE_FLAG_01", DictCodeListExt1.收支方向_收入.getValue());
        model.addAttribute("IE_FLAG_02", DictCodeListExt1.收支方向_支出.getValue());
        model.addAttribute("COUNTER_TYPE_01", DictCodeListExt1.交易对手类型_市场机构.getValue());
    }


    /**
     * 保存提交前的数据检查
     *
     * @param dto 画面输入参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody TrdConBond003Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
     // 确认提醒
        if (result != null && result.getErrorCount() == 0) {
        	String confirmMsg = "";
            String changeLineMark = "<br/>";
            //如果头寸不足时
            if (StringUtil.isNotEmpty(dto.getProAvailableCash())
                    && StringUtil.isNotEmpty(dto.getSupplementaryAmt())
                    && dto.getProAvailableCash().compareTo(dto.getSupplementaryAmt()) < 0){
            	 confirmMsg += "组合可用现金不足,当前最多可用金额为:<br/>" +
            			 dto.getProAvailableCash() +
                         "请确认是否继续？";
            }
            if(StringUtil.isNotEmpty(dto.getSecManageAcctId())&& StringUtil.equals(dto.getPurchType(), DictCodeListExt2.申购类型_普通申购.getValue())
            		&& StringUtil.equals(dto.getPurchModle(), DictCodeListExt2.申购方式_网上申购.getValue())
            		&&StringUtil.isNotEmpty(dto.getTradeMarketCash())&& StringUtil.isNotEmpty(dto.getSupplementaryAmt())
                    && dto.getTradeMarketCash().compareTo(dto.getSupplementaryAmt()) < 0){
                if (StringUtil.isNotBlank(confirmMsg)){
                    confirmMsg += changeLineMark;
                }
                confirmMsg += getMessage("TRD_C_0001");
            }
        	if (StringUtil.isNotEmpty(dto.getChlId())&&StringUtil.isNotEmpty(dto.getChannelAvailableCash())
                    && StringUtil.isNotEmpty(dto.getSupplementaryAmt())
                    && dto.getChannelAvailableCash().compareTo(dto.getSupplementaryAmt()) < 0){
        		if (StringUtil.isNotBlank(confirmMsg)){
                    confirmMsg += changeLineMark;
                }
        		confirmMsg += getMessage("TRD_C_0004",getMessage("invest.channelId"));
        	 }
        	if(StringUtil.isNotEmpty(confirmMsg)){
        		AjaxResult wr = new AjaxResult();
            	wr.setData(confirmMsg);
            	return wr;
        	}
        }
        return commonCheck(result, dto.getOperateType());  
    }

    /**
     * 保存提交
     *
     * @param dto
     * @return 画面输入参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdConBond003Dto dto)
        throws Exception {
    	List<TrdConBond003Dto> dtoList = new ArrayList<>();
		TrdConBond003Dto tempDto = new TrdConBond003Dto();
		BeanUtil.copyProperties(tempDto, dto);
		if (baseGetBusinessId != null) {
			BussinessCodeParameter codeParameter = new BussinessCodeParameter();
			codeParameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_可转债申购交易));
			tempDto.setBusiCode(baseGetBusinessId.buildBussinessCode(codeParameter));
		}
		else {
			tempDto.setBusiCode(IDGenerator.generate());
		}
        ProcessParameter<TrdConBond003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdConBond003Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getBusiCode());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getBusiCode(),
            getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodName(),
            getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));

        if(StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), dto.getPs())){
        	trdConBond003Service.prepareEntity(tempDto);
        }else{
        	trdConBond003Service.prepareEntity2(tempDto);
        }
        String msg = trdConBond003Service.start(processParameter, tempDto);
        return new AjaxResult(msg);
    }
    

}
