/*
 * 文件名：FinProductModule001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.module.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.tams.invest.product.productelement.module.service.FinProductModule001Service;
import com.joyintech.tams.invest.product.productelement.module.service.FinProductModuleService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 产品要素模板
 * @author 何阳阳
 * @version 1.0 2018年8月28日
 * @see FinProductModule001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductModule001")
@RequestMapping("/invest/product/productelement/module/FinProductModule001")
public class FinProductModule001Controller extends BaseController {
    /**
     * FinProductModuleService
     */
    @Autowired(required = false)
    private FinProductModuleService finProductModuleService;
    /**
     * FinProductModule001Service
     */
    @Autowired(required = false)
    private FinProductModule001Service finProductModule001Service;
    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.处理模式,
        DictCode.收益类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }
    /**
     * 获取模板名称的联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param  param param
     * @return SelectOptionDto
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getTempletNameList")
    public Page<SelectOptionDto> getTempletNameList(){
        return finProductModule001Service.getTempletNameList(RequestUtil.getPageParam());
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductModuleInfoDto> selectAll()
        throws Exception {
        return finProductModule001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid FinProductModuleInfo002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param result result
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Valid FinProductModuleInfo002Dto dto, BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        
        // 业务代码生成参数
        ProcessParameter<FinProductModuleInfo002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductModuleInfo002Dto> params = new WorkFlowParameter<>();
        params.setPageId(dto.getPageId());
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        params.setBusinessCode(item->{
            return item.getProductModuleInfo().getTemplateId();
        });
        params.setBusinessType(DictCodeList.共通业务类型_产品模板管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinProductModule001.templetId"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getProductModuleInfo().getTemplateId(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FinProductModule001.templetName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getProductModuleInfo().getTemplateName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finProductModuleService.start(processParameter,dto);
        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductModule001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
