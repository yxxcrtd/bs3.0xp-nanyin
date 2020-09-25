/*
 * 文件名：FinProductScheduling001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.dao.PaPrdIssueScheduleMapper;
import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.FinProductScheduling001Dto;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling001Service;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品排期管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月5日
 * @see FinProductScheduling001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductScheduling001")
@RequestMapping("/invest/product/productelement/scheduling/FinProductScheduling001")
public class FinProductScheduling001Controller extends BaseController {

    /**
     * {@link FinProductScheduling001Service}
     */
    @Autowired
    private FinProductScheduling001Service finProductScheduling001Service;

    /**
     * {@link FinProductScheduling002Service}
     */
    @Autowired
    private FinProductScheduling002Service finProductScheduling002Service;

    @Autowired
    private PaPrdIssueScheduleMapper paPrdIssueScheduleMapper;

    /**
     * {@linkplain}
     */

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 画面查詢方法
     * 
     * @return 所有數據
     * @throws Exception 異常
     */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<PrdIssueScheduleDto> selectForPage()
        throws Exception {
        Page<PrdIssueScheduleDto> page = finProductScheduling001Service.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySchedulingProdInfo")
    public List<SuggestDto> querySchedulingProdInfo(String param) {
        return finProductScheduling001Service.querySchedulingProdInfo(param);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductScheduling001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 修改或行删除时check验证
     * 
     * @param dto 待验证实体
     * @param br 验证信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinProductScheduling001Dto dto, BindingResult br) {
        AjaxResult ar = new AjaxResult();
        if (br.hasErrors()) {
            ar = new AjaxResult(br);
            List<ValidMessage> errorList = ar.getErrorList();
            // 流程中数据判断
            for (ValidMessage validMessage : errorList) {
                if (StringUtil.indexOf(validMessage.getMessage(),
                    this.getMessage("COMMON_E_0012").substring(0,
                        this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
                    validMessage.setMessage(this.getMessage(
                        "COMMON_E_0012",
                        dto.getFinprodMarketId() + ","
                                         + dto.getFinprodName() + ","
                                         + dto.getFinprodAbbr()));
                }
            }
        }
        else {
            // check 数据是否已被删除
            PaPrdIssueSchedule paPrdIssueSchedule = paPrdIssueScheduleMapper.selectByPrimaryKey(dto.getFinprodId());
            if (paPrdIssueSchedule != null) {
                if (finProductScheduling001Service.checkRegistCodeExists(dto.getFinprodId()) > 0) {
                    ar.appendMessage(new ValidMessage(this.getMessage("FIN_PROD_E_0049",
                        StringUtil.equals(dto.getOperateType(), "edit") ? "修改" : "删除",
                        this.getMessage("FinProductScheduling002.prodId"),
                        dto.getFinprodMarketId())));
                }
            }
            else {
                ar.appendMessage(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
            }
        }

        return ar;
    }

    /**
     * 启动工作流执行删除操作
     * 
     * @param dto 待删除数据
     * @return 结果信息
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody PrdIssueScheduleDto dto)
        throws Exception {
        ProcessParameter<PrdIssueScheduleDto> processParameter = new ProcessParameter<PrdIssueScheduleDto>();
        WorkFlowParameter<PrdIssueScheduleDto> params = new WorkFlowParameter<PrdIssueScheduleDto>();
        params.setPageId("FinProductScheduling002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_产品排期管理.getValue());
        processParameter.setOperationType(OperationType.DELETE);
        params.setComment(entity -> this.getMessage("FinProductScheduling002.prodId") + "("
                                    + entity.getFinprodMarketId() + ")"
                                    + this.getMessage("FinProductScheduling002.prodAbbr") + "("
                                    + entity.getFinprodAbbr() + ")"
                                    + this.getMessage("FinProductScheduling002.vdate") + "("
                                    + DateUtil.format(entity.getVdate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductScheduling002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
