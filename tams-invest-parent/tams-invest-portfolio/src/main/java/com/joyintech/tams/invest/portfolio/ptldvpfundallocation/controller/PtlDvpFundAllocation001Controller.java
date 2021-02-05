/*
 * 文件名：PtlDvpFundAllocation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptldvpfundallocation.controller;

import javax.validation.Valid;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationRevokeDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.service.PtlDvpFundAllocation001Service;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.service.PtlDvpFundAllocation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.ArrayList;

/**
 * DVP资金调拨管理Controller
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlDvpFundAllocation001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlDvpFundAllocation001")
@RequestMapping("/invest/portfolio/ptldvpfundallocation/PtlDvpFundAllocation001")
public class PtlDvpFundAllocation001Controller extends BaseController {
    
    /**
     * PtlDvpFundAllocation001Service
     */
    @Autowired
    private PtlDvpFundAllocation001Service ptlDvpFundAllocation001Service;
    
    /**
     * PtlDvpFundAllocation002Service
     */
    @Autowired
    private PtlDvpFundAllocation002Service ptlDvpFundAllocation002Service;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;
    /**
     * 重写init方法
     * @param model 模型
     * @return 返回pageId
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.画面数据状态);
        model.addAttribute("pageStatus", BussinessDictCodeList.画面数据状态_有效.getValue());
        model.addAttribute("pageStatusText", BussinessDictCodeList.画面数据状态_有效.getCodeName());

        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return Page<>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlDvpFundAllocationDto> findPage()
        throws Exception {
        return ptlDvpFundAllocation001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ptlDvpFundAllocation001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 撤销按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid PtlDvpFundAllocationRevokeDto dto, BindingResult result) {
        // etliu 20190719 指令已经发送成功，原逻辑不允许撤销，改成：确认消息，允许继续撤销。
        if (!result.hasErrors()) {
            // 指令是否已发送检查
            if (ptlDvpFundAllocation001Service.isOrderSended(dto.getOrderId())) {
                AjaxResult wr = new AjaxResult();
                wr.setData(getMessage("PTL_COM_C_0001"));
                return wr;
            }
        }
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(dto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 撤销操作
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody PtlDvpFundAllocationDto dto)
        throws Exception {
        ProcessParameter<PtlDvpFundAllocationDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PtlDvpFundAllocationDto> params = new WorkFlowParameter<>();
        params.setPageId("PtlDvpFundAllocation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_DVP资金调拨管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
            entity -> this.getMessage("PtlDvpFundAllocation001.portfolioId") + "("
                      + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("PtlDvpFundAllocation001.portfolioName") + "("
                      + entity.getPortfolioName() + ")" + "-"
                      + this.getMessage("PtlDvpFundAllocation001.orderId") + "("
                      + entity.getOrderId() + ")" );
        processParameter.setWorkFlowParameter(params);
        if (StringUtil.isNotEmpty(dto.getActPortfolioId())) {
            dto.setPortfolioId(dto.getActPortfolioId());
        }
        String msg = ptlDvpFundAllocation002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
