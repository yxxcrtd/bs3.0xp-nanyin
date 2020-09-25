/*
 * 文件名：TrdStandardBond002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrstandardbond.controller;

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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond002Dto;
import com.joyintech.tams.invest.entrust.etrstandardbond.service.EtrStandardBond002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 质押券入库
 * 〈功能详细描述〉
 *
 * @author lizhi
 * @version 1.0 2019年1月23日
 * @see EtrStandardBond002Controller
 * @since 1.0
 */
@PageId("EtrStandardBond002")
@RequestMapping("/invest/entrust/etrstandardbond/EtrStandardBond002")
@Controller
public class EtrStandardBond002Controller extends BaseController {

    @Autowired(required = false)
    private EtrStandardBond002Service etrStandardBond002Service;
    
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Override
    public String init(Model model) {

        EtrStandardBond002Dto dto = new EtrStandardBond002Dto();
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
    public List<EtrStandardBond002Dto> selectAvaPledgeBondList(@RequestParam Map<String, Object> params) {
        return etrStandardBond002Service.selectAvaPledgeBondList(params);
    }

    /**
     * 根据画面条件检索已入库债券信息
     *
     * @param params 画面参数
     * @return List<TrdStandardBond002Dto>
     */
    @RequestMapping("selectPledgeBondList")
    @ResponseBody
    public List<EtrStandardBond002Dto> selectPledgeBondList(@RequestParam Map<String, Object> params) {
        return etrStandardBond002Service.selectPledgeBondList(params);
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
    AjaxResult check(@Validated({ValidateGroupEdit.class}) @RequestBody EtrStandardBond002Dto dto, BindingResult result)
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
    @ResponseBody
    @RequestMapping("save")
    AjaxResult save(@Validated({ValidateGroupEdit.class}) @RequestBody EtrStandardBond002Dto dto, BindingResult result)
            throws Exception {
        if (result != null && result.hasErrors()) {
            return commonCheck(result, null);
        }
        if(StringUtil.isBlank(dto.getTradeId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_回购投资委托);
                codeParameter.setSeqNo("0521");
                dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            } else {
                dto.setTradeId(IDGenerator.generate());
            }
        }
        ProcessParameter<EtrStandardBond002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<EtrStandardBond002Dto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_回购投资委托.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment("质押券入库"+"_"+getMessage("组合代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getPortfolioId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("证券账户") + GlobalConstant.OPEN_PARENTHESIS +  entity.getPledgeBondList().get(0).getSecAcctId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("债券代码") + GlobalConstant.OPEN_PARENTHESIS + entity.getPledgeBondList().get(0).getFinprodMarketId() + GlobalConstant.CLOSE_PARENTHESIS));
        String msg = etrStandardBond002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
