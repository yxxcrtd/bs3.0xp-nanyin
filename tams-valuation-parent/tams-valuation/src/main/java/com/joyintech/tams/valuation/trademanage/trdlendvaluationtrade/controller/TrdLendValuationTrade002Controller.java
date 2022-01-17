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
import com.joyintech.framework.util.*;

import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service.TrdLendValuationTrade002Service;

import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 拆借投资交易新建
 */
@Controller
@PageId("TrdLendValuationTrade002")
@RequestMapping("/valuation/trademanage/trdlendvaluationtrade/TrdLendValuationTrade002")
public class TrdLendValuationTrade002Controller extends BaseController {
//	@Autowired
//	private TradeCommonService tradeCommonService;
//
//    /**
//     * trdLendInvestTrade002Service
//     */
    @Autowired
    private TrdLendValuationTrade002Service trdLendValuationTrade002Service;


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
            TrdLendValuationTradeDto entity = trdLendValuationTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("entity",entity);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("tradeId", entity.getTradeId());
        }else if (!StringUtil.isNotEmpty(workflowId) && StringUtil.isNotEmpty(tradeId)){
            TrdLendValuationTradeDto entity = trdLendValuationTrade002Service.queryTradeInfoByTradeId(tradeId);
            model.addAttribute("entity", entity);
        }else{
            //初始化值
            TrdLendValuationTradeDto entity = new TrdLendValuationTradeDto();
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
            ValidateGroupEdit.class}) TrdLendValuationTradeDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }



//
//    private void setSelectOptions(Model model) {
//        this.setSelectOptionToModel(model, DictCode.回购拆借方向, DictCode.拆借类型,
//            DictCode.有效状态, DictCode.币种, DictCode.计息基础);
//        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
//        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
//        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
//        model.addAttribute("BANK_LENDING_TYPE_01", DictCodeList.拆借类型_普通拆借.getValue());
//        model.addAttribute("BANK_LENDING_TYPE_02", DictCodeList.拆借类型_特殊目的载体.getValue());
//        model.addAttribute("BACK_LEND_DIRECTION_01", DictCodeList.回购拆借方向_拆入.getValue());
//        model.addAttribute("BACK_LEND_DIRECTION_02", DictCodeList.回购拆借方向_拆出.getValue());
//        model.addAttribute("FINPROD_TYPE_TG_F08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
//        model.addAttribute("FINPROD_TYPE_TG_F09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
//        model.addAttribute("FINPROD_TYPE_TG_F25", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
//        model.addAttribute("FINPROD_TYPE_F08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
//        model.addAttribute("FINPROD_TYPE_F09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
//    }
//
//    @RequestMapping("check")
//    @ResponseBody
//    public AjaxResult check(@Valid @RequestBody TrdLendInvestTradeDto dto, BindingResult result) {
//        if(StringUtil.isNotEmpty(result) && result.getErrorCount()>0){
//            AjaxResult res= new AjaxResult(result);
//            return res;
//        }
//    	//huwenbin 指令接收交易录单验证数据已存在流程中
//       /*if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
//	   		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
//	   		if(StringUtil.isNotEmpty(str)){
//	   			AjaxResult res= new AjaxResult();
//					res.setMessage(str, MessageType.错误消息);
//	   			return res;
//	   		}
//    	   // 查询交易流程中录入的总金额	王贵奇	2019/9/3
//    	   BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
//    	   BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
//    	   if(entrustNum.compareTo(wflPrinAmt.add(dto.getShareAmt())) == -1) {
//    		   AjaxResult res= new AjaxResult();
//    		   List<ValidMessage> errorList = new ArrayList<>();
//          	 ValidMessage validMessage = new ValidMessage("prin",
//          			 "可用委托金额不足,当前最多可委托金额为:"+ entrustNum.subtract(wflPrinAmt));
//                   errorList.add(validMessage);
//              res.setErrorList(errorList);
//              res.setMessageType(MessageType.错误消息);
//              return res;
//    	   }
//    	}*/
//    	// 到期日如果是节假日 要提醒
//        AjaxResult ajaxResult = trdLendInvestTrade002Service.checkMdate(dto, result);
//        if (ajaxResult != null) {
//            return ajaxResult;
//        }
//
//        // 可用现金软校验
//        if (StringUtil.equals(dto.getBusiPs(), DictCodeList.回购拆借方向_拆出.getValue())
//            && dto.getFinTradeProduct() != null) {
//            if (dto.getAvalibleCash() == null || (dto.getFinTradeProduct().getPrin() != null &&
//                    dto.getFinTradeProduct().getPrin().compareTo(dto.getAvalibleCash()) > 0)) {
//                AjaxResult ar = new AjaxResult();
//                ar.setData(this.getMessage("TRD_C_0002"));
//                return ar;
//            }
//        }
//
//        return commonCheck(result, dto.getOperateType());
//    }
//
//    @RequestMapping("save")
//    @ResponseBody
//    AjaxResult save(@RequestBody TrdLendInvestTradeDto dto)
//        throws Exception {
//        if (StringUtil.isBlank(dto.getTradeId())) {
//            if (baseGetBusinessId != null) {
//                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
////                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆借投资交易);
////                codeParameter.setType1(
////                    DictCodeList.from(DictCode.拆借类型, dto.getPaBussType()));
//                if (StringUtil.equals(dto.getBusiPs(),
//                    DictCodeList.回购拆借方向_拆入.getValue())) {
//                    if (StringUtil.equals(dto.getPaBussType(),
//                        DictCodeList.拆借类型_特殊目的载体.getValue())) {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_特拆交易);
//                    }
//                    else {
//                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆入交易);
//                    }
//                }
//                else {
//                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆出交易);
//                }
//                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
//            }
//            else {
//                dto.setTradeId(IDGenerator.generate());
//            }
//        }
//
//        ProcessParameter<TrdLendInvestTradeDto> processParameter = new ProcessParameter<>();
//        WorkFlowParameter<TrdLendInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
//        processParameter.setWorkFlowParameter(workFlowParameter);
//        processParameter.setPageId(this.getPageId());
//        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
//            processParameter.setOperationType(OperationType.NEW);
//        }
//        else {
//            processParameter.setOperationType(OperationType.MODIFY);
//        }
//        workFlowParameter.setPageId(this.getPageId());
//        workFlowParameter.setElementId(this.getParam("elementId"));
//        workFlowParameter.setBusinessCode(dto.getTradeId());
//        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_拆借投资交易.getValue());
//        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
//            getMessage("TrdLendInvestTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
//            getMessage("TrdLendInvestTrade002.paBussType") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.拆借类型, entity.getPaBussType()) + GlobalConstant.CLOSE_PARENTHESIS,
//            getMessage("TrdLendInvestTrade002.tradeDate")+ GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));
//        String msg = trdLendInvestTrade002Service.start(processParameter, dto);
//        return new AjaxResult(msg);
//    }
}
