/*
 * 文件名：PtlChlFundAllocation002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service.PtlChlFundAllocation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 通道资金划拨新建Controller
 *
 * @author etliu
 * @version 1.0 2019年4月4日
 * @see PtlChlFundAllocation002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlChlFundAllocation002")
@RequestMapping("/invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002")
public class PtlChlFundAllocation002Controller extends BaseController {

    /**
     * PtlChlFundAllocation002Service
     */
    @Autowired
    private PtlChlFundAllocation002Service ptlChlFundAllocation002Service;

    /**
     * 基础Mapper代理Service
     */

    /**
     * 业务编号生成类
     */
    @Autowired(required =false)
    public BaseGetBusinessId baseGetBussinessId;

    @Autowired(required =false)
    public BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 重写init方法
     *
     * @param model 模型
     * @return 返回pageId
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.交易方向, DictCode.币种);
        model.addAttribute("Fin_Type", DictCodeList.共通业务类型_通道资金划拨管理.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
        model.addAttribute("payTypeHK10", DictCodeListExt2.交易方向_通道回款.getValue());
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());

        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String orderId = getParam("orderId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            PtlChlFundAllocationDto dto = ptlChlFundAllocation002Service.getBussinessEntityByWorkflowId(
                    workflowId);
            if (dto.getBussTrdTransferOrder() != null) {
                model.addAttribute("stlPayOrdersList",
                        JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
            }
            else {
                model.addAttribute("tradeId", dto.getTradeId());
                dto.setBusiNo(dto.getOrderId());
            }
            model.addAttribute("entity", dto);
        }
        else if (StringUtil.isNotEmpty(orderId)) {
            PtlChlFundAllocationDto dto = ptlChlFundAllocation002Service.getTradeDetailData(
                    orderId);
            if (!StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                model.addAttribute("stlPayOrdersList",
                        JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
            }
            model.addAttribute("tradeId", dto.getTradeId());
            model.addAttribute("entity", dto);
        }
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        return super.init(model);
    }

    @RequestMapping("selectPagePortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> selectPagePortfolioId()
            throws Exception {
        return ptlChlFundAllocation002Service.selectPagePortfolioId(RequestUtil.getPageParam());
    }

    /**
     * 查询收款账号
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getCounterAcctNo")
    @ResponseBody
    public List<SelectOptionDto> getCounterAcctNo(){
        return ptlChlFundAllocation002Service.getCounterAcctNo(RequestUtil.getPageParam());
    }

    /**
     * 查询付款账号
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getMyAcctNo")
    @ResponseBody
    public List<SelectOptionDto> getMyAcctNo(){
        return ptlChlFundAllocation002Service.getMyAcctNo(RequestUtil.getPageParam());
    }

    /**
     * 获取账户余额
     * @return 账户余额
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAmount")
    @ResponseBody
    public BigDecimal getAmount() {
        return ptlChlFundAllocation002Service.getAmount(RequestUtil.getPageParam());
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid PtlChlFundAllocationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Valid PtlChlFundAllocationDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getBusiNo())){
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资金划拨管理);
            dto.setTradeId(baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate());
            if(dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().get(0) != null){
                dto.setBusiNo(dto.getBussTrdTransferOrder().get(0).getOrderId());
            }
        }
        dto.setOrderId(dto.getBussTrdTransferOrder().get(0).getOrderId());

        ProcessParameter<PtlChlFundAllocationDto> processParameter = new ProcessParameter<PtlChlFundAllocationDto>();
        WorkFlowParameter<PtlChlFundAllocationDto> params = new WorkFlowParameter<PtlChlFundAllocationDto>();
        params.setPageId("PtlChlFundAllocation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(DictCodeList.共通业务类型_通道资金划拨管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
                entity -> this.getMessage("PtlChlFundAllocation001.portfolioId") + "("
                        + entity.getPortfolioId() + ")" + "-"
                        + this.getMessage("PtlChlFundAllocation001.portfolioName") + "("
                        + entity.getPortfolioName() + ")" + "-"
                        + this.getMessage("PtlChlFundAllocation001.orderId") + "("
                        + entity.getBussTrdTransferOrder().get(0).getOrderId() + ")" );
        processParameter.setWorkFlowParameter(params);
        String msg = ptlChlFundAllocation002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
