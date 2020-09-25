/*
 * 文件名：ProdDevelopManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.proddevelopmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.dto.ProdDevelopManageDto;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.service.ProdDevelopManageService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 产品研发方案管理
 * 
 * @author zhangJin
 * @version 1.0 2019年10月22日
 * @see ProdDevelopManage001Controller
 * @since 1.0
 */
@Controller
@PageId("ProdDevelopManage001")
@RequestMapping("/invest/product/rddesign/proddevelopmanage/ProdDevelopManage001")
public class ProdDevelopManage001Controller extends BaseController {

    /**
     * 产品研发方案管理Service
     */
    @Autowired
    private ProdDevelopManageService prodDevelopManageService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.是否通过, DictCode.可行性状态, DictCode.产品风险等级);
        return super.init(model);
    }

    /**
     * 【产品系列】下拉框初始化
     * 
     * @param search 模糊查询
     * @return 产品系列
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProdDesignSeries")
    public Page<SelectOptionDto> getProdDesignSeries() {
        return prodDevelopManageService.getProdDesignSeries(RequestUtil.getPageParam());
    }

    /**
     * 分页查询
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ProdDevelopManageDto> findPage()
        throws Exception {
        return prodDevelopManageService.selectForPage(RequestUtil.getPageParam());
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
        return prodDevelopManageService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除、修改按钮点击时的check
     *
     * @param dto 参数
     * @param result 校验结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ProdDevelopManageDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupDelete.class}) ProdDevelopManageDto dto, BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        ProcessParameter<ProdDevelopManageDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ProdDevelopManageDto> params = new WorkFlowParameter<>();
        params.setPageId(dto.getPageId());
        params.setElementId("ProdDevelopManage001_btn_delete");
        params.setBusinessCode(item -> item.getSeqNo());
        params.setBusinessType(DictCodeListExt2.共通业务类型_产品研发方案管理.getValue());
        params.setComment(entity -> this.getMessage("ProdDevelopManage001.prdDesignId") + "("
                                    + entity.getPrdDesignId() + ")" + "-"
                                    + this.getMessage("ProdDevelopManage001.prdDesignName") + "("
                                    + entity.getPrdDesignName() + ")" + "-"
                                    + this.getMessage("ProdDevelopManage001.generateTime") + "("
                                    + entity.getGenerateTimeStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = prodDevelopManageService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
