/*
 * 文件名：TrdLendInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.lend.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.common.controller.TrdCommonController;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 拆借投资交易新建
 */
@Controller
@PageId("TrdLendInvestTrade002")
@RequestMapping("/invest/trade/lend/TrdLendInvestTrade002")
public class TrdLendInvestTrade002Controller extends BaseController {
    @Autowired
    private TradeCommonService tradeCommonService;

    /**
     * trdLendInvestTrade002Service
     */
    @Autowired
    private TrdLendInvestTrade002Service trdLendInvestTrade002Service;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    @Autowired
    private TrdCommonController trdCommonController;

    @Override
    public String init(Model model) {
        setSelectOptions(model);
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

        TrdLendInvestTradeDto dto = new TrdLendInvestTradeDto();

        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = trdLendInvestTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(dto.getRiskTrialRetDto()));
            if (StringUtil.isBlank(dto.getTradeId())) {
                // 根据工作流代码查询交易编号
                dto.setTradeId(trdLendInvestTrade002Service.selectTradeIdByWorkflowId(workflowId));
            }
        } else if (StringUtil.isNotEmpty(tradeId)) {
            dto = trdLendInvestTrade002Service.getTrdLendInvestTradeDto(tradeId);
        } else {
            String entrustId = this.getParam("entrustId");
            if (StringUtil.isNotEmpty(entrustId)) {
                dto.setEntrustId(entrustId);
            }
        }

        if (StringUtil.isNotBlank(tradeId)) {
            model.addAttribute("tradeId", tradeId);
        } else {
            model.addAttribute("tradeId", dto.getTradeId());
        }
        if (StringUtil.isNotBlank(workflowId)) {
            model.addAttribute("workflowId", workflowId);
        } else {
            model.addAttribute("workflowId", dto.getWorkflowId());
        }

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
        model.addAttribute("PINAN_ASSET_TYPE_F08", DictCodeList.平安资产类型_拆借.getValue());
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("BaseRiskMathComm");
        Map<String, String> map = PageElementUtil.isPageElementShow(pageElementConfigDto); // 风险试算页面
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        isEleShow.putAll(map);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    private void setSelectOptions(Model model) {
        this.setSelectOptionToModel(model, DictCode.回购拆借方向, DictCode.拆借类型,
                DictCode.有效状态, DictCode.币种, DictCode.计息基础);
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
        model.addAttribute("BANK_LENDING_TYPE_01", DictCodeList.拆借类型_普通拆借.getValue());
        model.addAttribute("BANK_LENDING_TYPE_02", DictCodeList.拆借类型_特殊目的载体.getValue());
        model.addAttribute("BACK_LEND_DIRECTION_01", DictCodeList.回购拆借方向_拆入.getValue());
        model.addAttribute("BACK_LEND_DIRECTION_02", DictCodeList.回购拆借方向_拆出.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F25", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
        model.addAttribute("FINPROD_TYPE_F08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
        model.addAttribute("FINPROD_TYPE_F09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
    }

    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdLendInvestTradeDto dto, BindingResult result) {
        if (StringUtil.isNotEmpty(result) && result.getErrorCount() > 0) {
            AjaxResult res = new AjaxResult(result);
            return res;
        }
        //huwenbin 指令接收交易录单验证数据已存在流程中
       /*if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
	   		String str = tradeCommonService.check(dto.getEntrustId(), this.getPageId());
	   		if(StringUtil.isNotEmpty(str)){
	   			AjaxResult res= new AjaxResult();
					res.setMessage(str, MessageType.错误消息);
	   			return res;
	   		}
    	   // 查询交易流程中录入的总金额	王贵奇	2019/9/3
    	   BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dto.getEntrustId());
    	   BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dto.getEntrustId(), dto.getPortfolioId());
    	   if(entrustNum.compareTo(wflPrinAmt.add(dto.getShareAmt())) == -1) {
    		   AjaxResult res= new AjaxResult();
    		   List<ValidMessage> errorList = new ArrayList<>();
          	 ValidMessage validMessage = new ValidMessage("prin",
          			 "可用委托金额不足,当前最多可委托金额为:"+ entrustNum.subtract(wflPrinAmt));
                   errorList.add(validMessage);
              res.setErrorList(errorList);
              res.setMessageType(MessageType.错误消息);
              return res;
    	   }
    	}*/
        // 到期日如果是节假日 要提醒
        AjaxResult ajaxResult = trdLendInvestTrade002Service.checkMdate(dto, result);
        if (ajaxResult != null) {
            return ajaxResult;
        }

        // 托管户剩余现金軟校驗
        if (StringUtil.equals(dto.getBusiPs(), DictCodeList.回购拆借方向_拆出.getValue())
                && dto.getFinTradeProduct() != null) {
            if (dto.getSecAcctBalance() == null || (dto.getFinTradeProduct().getPrin() != null &&
                    dto.getFinTradeProduct().getPrin().compareTo(dto.getSecAcctBalance()) > 0)) {
                AjaxResult ar = new AjaxResult();
                ar.setData(this.getMessage("TRD_C_0008"));
                return ar;
            }
        }

        // 可用现金软校验
        if (StringUtil.equals(dto.getBusiPs(), DictCodeList.回购拆借方向_拆出.getValue())
                && dto.getFinTradeProduct() != null) {
            if (dto.getAvalibleCash() == null || (dto.getFinTradeProduct().getPrin() != null &&
                    dto.getFinTradeProduct().getPrin().compareTo(dto.getAvalibleCash()) > 0)) {
                AjaxResult ar = new AjaxResult();
                ar.setData(this.getMessage("TRD_C_0002"));
                return ar;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }

    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdLendInvestTradeDto dto)
            throws Exception {
        if (StringUtil.isBlank(dto.getTradeId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
//                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆借投资交易);
//                codeParameter.setType1(
//                    DictCodeList.from(DictCode.拆借类型, dto.getPaBussType()));
                if (StringUtil.equals(dto.getBusiPs(),
                        DictCodeList.回购拆借方向_拆入.getValue())) {
                    if (StringUtil.equals(dto.getPaBussType(),
                            DictCodeList.拆借类型_特殊目的载体.getValue())) {
                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_特拆交易);
                    } else {
                        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆入交易);
                    }
                } else {
                    codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_拆出交易);
                }
                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            } else {
                dto.setTradeId(IDGenerator.generate());
            }
        }

        //----------add by jjpei 20200114 核风险版本 start----------
        if (dto.getOperateType().equals(BussinessConstant.SUBMIT) && CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
            AjaxResult ar = interfRiskRdsService.checkRiskRds(new InterfRiskRdsDto(), BussinessDictCodeListRds.事件类型_投资交易.getValue(),
                    BussinessDictCodeListRds.接口系统ID_实占接口.getValue(), DictCodeList.平安资产类型_拆借.getValue(),
                    (en) -> interfRiskRdsService.sendRiskRdsData(dto, en));
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
        }
        //----------add by jjpei 20200114 核风险版本 end----------
        ProcessParameter<TrdLendInvestTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdLendInvestTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        } else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode(dto.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_拆借投资交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdLendInvestTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdLendInvestTrade002.paBussType") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.拆借类型, entity.getPaBussType()) + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdLendInvestTrade002.tradeDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = trdLendInvestTrade002Service.start(processParameter, dto);

        //授信占用额度
//        trdCommonController.delLinesDeal(dto.getRiskCreditListCommonDto(),dto.getTradeId());
        return new AjaxResult(msg);
    }
}
