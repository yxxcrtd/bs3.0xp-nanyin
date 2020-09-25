/*
 * 文件名：PtlChlFundAllocation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.controller;

import javax.validation.Valid;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationRevokeDto;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service.PtlChlFundAllocation001Service;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service.PtlChlFundAllocation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 通道资金划拨管理Controller
 *
 * @author etliu
 * @version 1.0 2019年4月4日
 * @see PtlChlFundAllocation001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlChlFundAllocation001")
@RequestMapping("/invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation001")
public class PtlChlFundAllocation001Controller extends BaseController {
    
    /**
     * PtlChlFundAllocation001Service
     */
    @Autowired
    private PtlChlFundAllocation001Service ptlChlFundAllocation001Service;
    
    /**
     * PtlChlFundAllocation002Service
     */
    @Autowired
    private PtlChlFundAllocation002Service ptlChlFundAllocation002Service;

    /**
     * 重写init方法
     * @param model 模型
     * @return 返回pageId
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.交易方向, DictCode.画面数据状态);
        model.addAttribute("pageStatus", DictCodeListExt1.画面数据状态_有效.getValue());
        model.addAttribute("pageStatusText", DictCodeListExt1.画面数据状态_有效.getCodeName());

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
    public Page<PtlChlFundAllocationDto> findPage()
        throws Exception {
        return ptlChlFundAllocation001Service.findPage(RequestUtil.getPageParam());
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
        return ptlChlFundAllocation001Service.export(this.getPageId(),
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
    public AjaxResult check(@RequestBody @Valid PtlChlFundAllocationRevokeDto dto, BindingResult result) {
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
    public AjaxResult revoke(@RequestBody PtlChlFundAllocationDto dto)
        throws Exception {
        ProcessParameter<PtlChlFundAllocationDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PtlChlFundAllocationDto> params = new WorkFlowParameter<>();
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
                      + entity.getOrderId() + ")" );
        processParameter.setWorkFlowParameter(params);
        if (StringUtil.isNotEmpty(dto.getActPortfolioId())) {
            dto.setPortfolioId(dto.getActPortfolioId());
        }
        String msg = ptlChlFundAllocation002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
