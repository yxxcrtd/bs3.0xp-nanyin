package com.joyintech.tams.invest.trade.bondmarkettrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade001Service;
import com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券上市流通
 *
 * @author 马数凯
 * @version 1.0 2021年3月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.controller.TrdBondMarketTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdBondMarketTrade002")
@RequestMapping("/invest/trade/bondmarkettrade/TrdBondMarketTrade002")
public class TrdBondMarketTrade002Controller extends BaseController {
    @Autowired
    TrdBondMarketTrade002Service trdBondMarketTrade002Service;

    @Autowired
    TrdBondMarketTrade001Service trdBondMarketTrade001Service;
    
    @Autowired
    private IPlatformDate iPlatformDate;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,  DictCode.交易场所);
        TrdBondMarketTradeDto dto = new TrdBondMarketTradeDto();
//        param.put("tradeDate",DateUtil.format(iPlatformDate.getSysDate()));
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String totalTradeId = this.getParam("totleTradeId");
        String finprodId = this.getParam("finprodId");
        String appearMarketId = this.getParam("appearMarketId");
        if (StringUtil.isNotEmpty(workflowId)){
            //流程中
            dto = trdBondMarketTrade002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("workflowId",workflowId);
            model.addAttribute("entity",dto);
        }else if (StringUtil.isNotEmpty(totalTradeId) && !StringUtil.isNotEmpty(workflowId)){
            //双击进入查看页面
            dto = trdBondMarketTrade002Service.selectByTotalTradeId(totalTradeId);
            model.addAttribute("entity",dto);
        }else if (!StringUtil.isNotEmpty(totalTradeId) && !StringUtil.isNotEmpty(workflowId) && StringUtil.isNotEmpty(finprodId)){
            //从主动提醒处进入新建页面
            dto.setFinprodId(finprodId);
            dto.setAppearMarketId(appearMarketId);
            Map<String, Object> params = new HashMap<>();
            params.put("initValue",finprodId);
            Page<SelectOptionDto> page = trdBondMarketTrade001Service.getFinprodId(params);
            if (page != null && page.getRoot() != null && page.getRoot().size() > 0){
                Map<String,Object> map = (Map<String, Object>) page.getRoot().get(0).getObject();
                String tradeMarket = (String) map.get("tradeMarket");
                dto.setTradeMarket(tradeMarket);
            }
            model.addAttribute("entity",dto);
        }
        model.addAttribute("platDate",iPlatformDate.getSysDateStr());
        return super.init(model);
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
    AjaxResult save(@RequestBody TrdBondMarketTradeDto dto)
            throws Exception {
        dto.setTotleTradeId(trdBondMarketTrade002Service.genTradeId());
        dto.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        ProcessParameter<TrdBondMarketTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondMarketTradeDto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessCode((entity) -> entity.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + entity.getTotleTradeId(),
                getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + entity.getFinprodAbbr(),
                getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(entity.getTradeDate())));
        trdBondMarketTrade002Service.setEntity(dto);
        String msg = trdBondMarketTrade002Service.start(processParameter, dto);

        return new AjaxResult(msg);
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
    public AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody TrdBondMarketTradeDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult res= new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        
        int count = trdBondMarketTrade002Service.selectIsInWorkflow(dto);
        if(count > 0){
            ValidMessage validMessage = new ValidMessage("appearMarketId",
                    "该上市代码已存在流程中数据，不可重复操作！");
            errorList.add(validMessage);
            res.setErrorList(errorList);
            res.setMessageType(MessageType.错误消息);
            return res;
        }

        count = trdBondMarketTrade002Service.selectDealNums(dto);
        if (count > 0){
            ValidMessage validMessage = new ValidMessage("finprodId",
                    "发行标的当天存在流程中或已生效的交易，请先撤销交易再做上市流通！");
            errorList.add(validMessage);
            res.setErrorList(errorList);
            res.setMessageType(MessageType.错误消息);
            return res;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
}
