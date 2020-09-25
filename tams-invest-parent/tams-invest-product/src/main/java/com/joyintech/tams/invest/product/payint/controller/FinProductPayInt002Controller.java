/*
 * 文件名：FinProductPayInt002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt001Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002IntDetailDto;
import com.joyintech.tams.invest.product.payint.service.FinProductPayInt002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品中间付息确认
 *
 * @author etliu
 * @version 1.0 2019年1月6日
 * @see FinProductPayInt002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductPayInt002")
@RequestMapping("/invest/product/payint/FinProductPayInt002")
public class FinProductPayInt002Controller extends BaseController {

    /**
     * FinProductPayInt002Service
     */
    @Autowired
    private FinProductPayInt002Service finProductPayInt002Service;

    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.销售部门, DictCode.收益类型,
            DictCode.付息周期, DictCode.币种);

        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_TG_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        model.addAttribute("PS_ZJFX", DictCodeList.交易方向_中间付息.getValue());
        model.addAttribute("LAY_TYPE_JEFC", DictCodeList.分层类型_金额分层.getValue());
        model.addAttribute("LAY_TYPE_QXFC", DictCodeList.分层类型_期限分层.getValue());
        model.addAttribute("LAY_TYPE_KHFC", DictCodeList.分层类型_客户类型.getValue());

        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

        FinProductPayInt002Dto dataDto = new FinProductPayInt002Dto();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)) {
            dataDto = finProductPayInt002Service.getBussinessEntityByWorkflowId(
                workflowId);
            dataDto.setWorkflowId(workflowId);
        }
        else {
            FinProductPayInt001Dto paramDto = new FinProductPayInt001Dto();
            paramDto.setPortfolioId(getParam("portfolioId"));
            paramDto.setFinprodId(getParam("finprodId"));
            paramDto.setProdId(getParam("prodId"));
            paramDto.setPayintStartDate(DateUtil.parse(getParam("payintStartDate")));
            paramDto.setPayintEndDate(DateUtil.parse(getParam("payintEndDate")));
            paramDto.setCcy(getParam("ccy"));
            paramDto.setIsLay(getParam("isLay"));
            if (StringUtil.isNotBlank(getParam("prinAmt"))) {
                paramDto.setPrinAmt(new BigDecimal(getParam("prinAmt")));
            }
            paramDto.setBussId(getParam("bussId"));

            dataDto = finProductPayInt002Service.selectProdBaseInfo(paramDto);
        }

        // 划款信息
        if (dataDto != null && !StringUtil.isEmptyList(dataDto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_FinProductPayInt002",
                JsonUtils.object2Json(dataDto.getBussTrdTransferOrder().get(0)));
        }

        model.addAttribute("entity", dataDto);

        return super.init(model);
    }

    /**
     * 查询利息明细信息
     *
     * @return 利息明细信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findIntDetailInfo")
    @ResponseBody
    public Page<ReduceInfoDto> findIntDetailInfo() throws Exception {
        FinProductPayInt002Dto inputDto = BeanUtil.mapToObject(RequestUtil.getPageParam(), FinProductPayInt002Dto.class);
        return finProductPayInt002Service.findIntDetailInfo(inputDto);
    }

    /**
     * 画面联动
     * 2.应付客户收益 = sum（应付利息）
     * 3.实付客户收益 = sum（实付利息）
     *
     * @param inputDto 参数
     * @return 画面联动结果
     * @throws Exception Exception
     */
    @RequestMapping("calcCusProfit")
    @ResponseBody
    public FinProductPayInt002Dto calcCusProfit(@RequestBody FinProductPayInt002Dto inputDto)
            throws Exception {
        return finProductPayInt002Service.calcCusProfit(inputDto);
    }
    
    /**
     * 画面联动
     * 反算实付收益率
     *
     * @param inputDto 参数
     * @return 画面联动结果
     * @throws Exception Exception
     */
    @RequestMapping("calcActYield")
    @ResponseBody
    public FinProductPayInt002IntDetailDto calcActYield(@RequestBody FinProductPayInt002IntDetailDto inputDto)
            throws Exception {
        return finProductPayInt002Service.calcActYield(inputDto);
    }

    /**
     * 保存提交前数据检查
     *
     * @param dto 画面参数
     * @param result valid结果
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody FinProductPayInt002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存提交
     *
     * @param dto 画面参数
     * @return 保存提交结果
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody FinProductPayInt002Dto dto) throws Exception {
        if (StringUtil.isBlank(dto.getBussId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);
                dto.setBussId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setBussId(IDGenerator.generate());
            }
        }

        ProcessParameter<FinProductPayInt002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductPayInt002Dto> workFlowParameter = new WorkFlowParameter<>();
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
        workFlowParameter.setBusinessCode((entity) -> entity.getBussId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("FinProductPayInt002.prodAbbr") + GlobalConstant.OPEN_PARENTHESIS + entity.getProdAbbr() + GlobalConstant.CLOSE_PARENTHESIS,
            getMessage("FinProductPayInt002.payDate") + GlobalConstant.COLON + DateUtil.format(entity.getPayDate()),
            getMessage("FinProductPayInt002.actCusProfit") + GlobalConstant.COLON + String.valueOf(entity.getActCusProfit())));
        String msg = finProductPayInt002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
