/*
 * 文件名：TrdStandardBond003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdstandardbond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond003Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.service.TrdStandardBond003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 添加质押券
 * 〈功能详细描述〉
 *
 * @author 裴家俊
 * @version 1.0 2018年12月18日
 * @see TrdStandardBond003Controller
 * @since 1.0
 */
@PageId("TrdStandardBond003")
@RequestMapping("/invest/trade/trdstandardbond/TrdStandardBond003")
@Controller
public class TrdStandardBond003Controller extends BaseController {

    @Autowired(required = false)
    private TrdStandardBond003Service trdStandardBond003Service;

    @Override
    public String init(Model model) {
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

        TrdStandardBond003Dto dto = new TrdStandardBond003Dto();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)) {
            dto = trdStandardBond003Service.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("outBondList", StringUtil.isEmptyList(dto.getOutBondList()) ? null : JsonUtils.object2Json(dto.getOutBondList()));
            model.addAttribute("workflowId", workflowId);
        }
        model.addAttribute("portfolioId", this.getParam("portfolioId"));
        model.addAttribute("bondCode", this.getParam("bondCode"));
        model.addAttribute("initBondCode", this.getParam("initBondCode"));
        model.addAttribute("initPortfolioId",this.getParam("initPortfolioId"));
        model.addAttribute("tradePlace", this.getParam("tradePlace"));
        model.addAttribute("secAcctId", this.getParam("secAcctId"));

        return super.init(model);
    }

    /**
     * 质押券出库信息一览查询
     *
     * @return List<TrdStandardBond001Dto>
     * @see
     * @since 1.0
     */
    @RequestMapping("selectOutListData")
    @ResponseBody
    public List<TrdStandardBond003Dto> selectOutListData() {
        return trdStandardBond003Service.selectOutListData(RequestUtil.getPageParam());
    }

    /**
     * 保存处理
     *
     * @param dto dto
     * @return
     * @throws Exception
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody TrdStandardBond003Dto dto)
            throws Exception {
        ProcessParameter<TrdStandardBond003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdStandardBond003Dto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessCode((entity) -> dto.getOutBondList().get(0).getSecAcctId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_质押式回购交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment("标准券出库"));
        /*如果是提交，需要生成tradeId,并保存到留痕表，doinsert的修改并不会影响到留痕表*/
        if ("submit".equals(dto.getOperateType())&&!StringUtil.isEmptyList(dto.getOutBondList())) {
        	trdStandardBond003Service.setTradeIdList(dto);
        }
        String msg = trdStandardBond003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
