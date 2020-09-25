/*
 * 文件名：FinProductScheduling002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.controller;

import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品排期管理-新建、查看、修改
 * 
 * @author zhangliang
 * @version 1.0 2018年9月6日
 * @see FinProductScheduling002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductScheduling002")
@RequestMapping("/invest/product/productelement/scheduling/FinProductScheduling002")
public class FinProductScheduling002Controller extends BaseController {

    /**
     * {@link FinProductScheduling002Service}
     */
    @Autowired
    private FinProductScheduling002Service finProductScheduling002Service;

    /**
     * {@link ProductCommonService}
     */
    @Autowired
    private ProductCommonService productCommonService;

    /**
     * 业务编号
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Override
    public String init(Model model) {
        String isTodoList = RequestUtil.getParam("isTodoList");
        if (StringUtil.isNotEmpty(isTodoList) && StringUtil.equals(isTodoList, "true")) {
            String workflowId = RequestUtil.getParam("workflowId");
            ProcessMark pro = finProductScheduling002Service.getBusinessInfo(workflowId);
            if (pro != null) {
                PrdIssueScheduleDto entity = JsonUtils.json2Object(pro.getBusinessData(),
                    PrdIssueScheduleDto.class);
                model.addAttribute("entity", entity);
                model.addAttribute("finprodId", entity.getFinprodId());
                model.addAttribute("workflowId", workflowId);
            }
        }
        else {
            String finprodId = RequestUtil.getParam("finprodId");
            if (StringUtil.isNotEmpty(finprodId)) {
                PrdIssueScheduleDto entity = this.finProductScheduling002Service.queryIssueInfoByFinprodId(
                    finprodId);
                model.addAttribute("entity", entity);
            }
            model.addAttribute("finprodId", StringUtil.defaultString(finprodId));
            model.addAttribute("workflowId",
                StringUtil.defaultString(this.getParam("workflowId")));
        }

        // 期限品种相关
        model.addAttribute("REPTERM_02", DictCodeListExt2.产品期限_7天_含_以内.getValue());
        model.addAttribute("REPTERM_03", DictCodeListExt2.产品期限_7天_1个月_含.getValue());
        model.addAttribute("REPTERM_04", DictCodeListExt2.产品期限_1_3个月_含.getValue());
        model.addAttribute("REPTERM_05", DictCodeListExt2.产品期限_3_6个月_含.getValue());
        model.addAttribute("REPTERM_06", DictCodeListExt2.产品期限_6_12个月_含.getValue());
        model.addAttribute("REPTERM_07", DictCodeListExt2.产品期限_1_3年_含.getValue());

        setSelectOptionToModel(model,
            new DictCode[] {DictCode.排期发行方式, DictCode.付息周期,
                DictCode.币种, DictCode.产品期限, DictCode.规律开放周期,
                DictCode.是否, DictCode.开放期业务});
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        return super.init(model);
    }

    /**
     * 查询产品模板下拉框
     * 
     * @return 产品模板
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdTempleteInfo")
    public Page<SelectOptionDto> queryProdTempleteInfo() {
        return finProductScheduling002Service.queryProdTempleteInfo(RequestUtil.getPageParam());
    }

    /**
     * 查询产品申报方案下拉框
     * 
     * @return 产品申报方案
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryDeclareSchemeInfo")
    public List<SelectOptionDto> queryDeclareSchemeInfo() {
        return finProductScheduling002Service.queryDeclareSchemeInfo(RequestUtil.getPageParam());
    }

    /**
     * 根据金融产品代码查询标的信息
     * 
     * @param finprodId 金融产品代码
     * @return 标的信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFinProductByFinprodId")
    public FinProduct queryFinProductByFinprodId(String finprodId) {
        return productCommonService.queryFinProductByFinprodId(finprodId);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated PrdIssueScheduleDto dto,
                            BindingResult result) {
        if (result.hasErrors()) {
            AjaxResult ar = new AjaxResult(result);
            List<ValidMessage> errorList = ar.getErrorList();
            // 流程中数据判断
            for (ValidMessage validMessage : errorList) {
                if (StringUtil.indexOf(validMessage.getMessage(),
                    this.getMessage("COMMON_E_0012").substring(0,
                        this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
                    validMessage.setMessage(this.getMessage(
                        "COMMON_E_0012", dto.getFinprodMarketId() + "," + dto.getFinprodName()
                                         + "," + dto.getFinprodAbbr()));
                }
            }
            return ar;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated PrdIssueScheduleDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        ProcessParameter<PrdIssueScheduleDto> processParameter = new ProcessParameter<PrdIssueScheduleDto>();
        WorkFlowParameter<PrdIssueScheduleDto> params = new WorkFlowParameter<PrdIssueScheduleDto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_产品排期管理.getValue());
        // 设置工作流注解
        params.setComment(entity -> this.getMessage("FinProductScheduling002.prodId") + "("
                                    + entity.getFinprodMarketId() + ")"
                                    + this.getMessage("FinProductScheduling002.prodAbbr") + "("
                                    + entity.getFinprodAbbr() + ")"
                                    + this.getMessage("FinProductScheduling002.vdate") + "("
                                    + DateUtil.format(entity.getVdate()) + ")");
        processParameter.setWorkFlowParameter(params);
        if (StringUtil.isEmpty(dto.getFinprodId())) {
            // 业务代码生成参数
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品排期管理);
            String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                parameter) : IDGenerator.generate();
            dto.setFinprodId(code);
        }
        // 调用工作流事件
        String msg = finProductScheduling002Service.start(params, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
}
