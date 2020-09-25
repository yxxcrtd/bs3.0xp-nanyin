/*
 * 文件名：FinSubmittedModule002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule001Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 申报附件方案Controller 可以进行申报附件方案的新建和编辑
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule002Controller
 * @since 1.0
 */
@Controller
@PageId("FinSubmittedModule002")
@RequestMapping("/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002")
public class FinSubmittedModule002Controller extends BaseController {

    /**
     * 申报方案Service
     */
    @Autowired
    private FinSubmittedModule001Service finSubmittedModule001Service;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 申报附件方案管理界面的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String schemeId = RequestUtil.getParam("schemeId");
        if (StringUtil.isNotEmpty(schemeId)) {
            FinSubmittedModule001Dto schemeList = finSubmittedModule001Service.selectScheme(
                schemeId);
            model.addAttribute("entity", schemeList);
        }
        return super.init(model);
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinSubmittedModule001Dto dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinSubmittedModule001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 业务代码生成参数
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_申报附件方案管理);
        // 新建，系统生成申报附件方案代码
        dto.setSchemeId( bussGetBussinessIdService.buildBussinessCode(parameter));
        ProcessParameter<FinSubmittedModule001Dto> processParameter = new ProcessParameter<FinSubmittedModule001Dto>();
        processParameter.setOperationType(OperationType.NEW);
        processParameter.setPageId("FinSubmittedModule002");
        String msg = finSubmittedModule001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 编辑操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResult update(@RequestBody FinSubmittedModule001Dto dto) {
        finSubmittedModule001Service.update(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0065"));
    }
}
