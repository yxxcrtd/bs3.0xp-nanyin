/*
 * 文件名：TrdStandardBond002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdstandardbond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond002Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.service.TrdStandardBond002Service;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 质押券入库
 * 〈功能详细描述〉
 *
 * @author 陈志力
 * @version 1.0 2019年1月23日
 * @see TrdStandardBond002Controller
 * @since 1.0
 */
@PageId("TrdStandardBond002")
@RequestMapping("/invest/trade/trdstandardbond/TrdStandardBond002")
@Controller
public class TrdStandardBond002Controller extends BaseController {

    @Autowired(required = false)
    private TrdStandardBond002Service trdStandardBond002Service;
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Override
    public String init(Model model) {
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

        TrdStandardBond002Dto dto = new TrdStandardBond002Dto();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)) {
            dto = trdStandardBond002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("pledgeBondList", StringUtil.isEmptyList(dto.getPledgeBondList()) ? null : JsonUtils.object2Json(dto.getPledgeBondList()));
            model.addAttribute("avaPledgeBondList", StringUtil.isEmptyList(dto.getAvaPledgeBondList()) ? null : JsonUtils.object2Json(dto.getAvaPledgeBondList()));
            model.addAttribute("workflowId", workflowId);
        }
        dto.setPortfolioId(this.getParam("portfolioId"));
        dto.setTradePlace(this.getParam("tradePlace"));
        dto.setSecAcctId(this.getParam("secAcctId"));
        model.addAttribute("entity", dto);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        return super.init(model);
    }

    /**
     * 根据画面条件检索可质押券信息
     *
     * @param params 画面参数
     * @return List<TrdStandardBond002Dto>
     */
    @RequestMapping("selectAvaPledgeBondList")
    @ResponseBody
    public List<TrdStandardBond002Dto> selectAvaPledgeBondList(@RequestParam Map<String, Object> params) {
        return trdStandardBond002Service.selectAvaPledgeBondList(params);
    }

    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("check")
    @ResponseBody
    AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody TrdStandardBond002Dto dto, BindingResult result)
            throws Exception {
        return commonCheck(result, null);
    }

    /**
     * 保存处理
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@Validated({ValidateGroupEdit.class}) @RequestBody TrdStandardBond002Dto dto, BindingResult result)
            throws Exception {
        if (result != null && result.hasErrors()) {
            return commonCheck(result, null);
        }
        if(StringUtil.isBlank(dto.getTradeId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_质押式回购交易);
                codeParameter.setSeqNo("0520");
                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            } else {
                dto.setTradeId(IDGenerator.generate());
            }
        }
        ProcessParameter<TrdStandardBond002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdStandardBond002Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId(this.getPageId());
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        } else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        dto.setBranch((Long) Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_质押式回购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment("质押券入库"+"_"+getMessage("组合代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getPortfolioId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("证券账户") + GlobalConstant.OPEN_PARENTHESIS +  entity.getPledgeBondList().get(0).getSecAcctId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("债券代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getBondCode() + GlobalConstant.CLOSE_PARENTHESIS));
        //当类型为提交时需要提前生成交易编号，从而保存到留痕表
        if(StringUtil.equals(dto.getOperateType(), "submit")) {
        	trdStandardBond002Service.setTradeIdList(dto);
        }
        String msg = trdStandardBond002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
