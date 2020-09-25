/*
 * 文件名：FinSubmittedModule001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.controller;

import java.util.List;
import java.util.Map;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule001Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule001Service;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 申报附件方案查询Controller
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule001Controller
 * @since 1.0
 */
@Controller
@PageId("FinSubmittedModule001")
@RequestMapping("/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule001")
public class FinSubmittedModule001Controller extends BaseController{
    
    /**
     * 申报方案Service
     */
    @Autowired
    private FinSubmittedModule001Service finSubmittedModule001Service;
    
    /**
     * 申报附件方案Service
     */
    @Autowired
    private FinSubmittedModule002Service finSubmittedModule002Service;
    
    /**
     * 申报附件方案管理查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }
    /**
     * 方案名称树的查询事件
     * 
     * @return 申报附件方案名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/getSchemeName")
    public String getSchemeName(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("list", finSubmittedModule001Service.getSchemeName(map));
        return "/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule006";
    }
    
    /**
     * 表格查询操作
     *
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinSubmittedModule002Dto> select()
        throws Exception {  
        Map<String,Object> map = RequestUtil.getPageParam();
        return finSubmittedModule002Service.selectForPage(map);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) FinSubmittedModule001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 删除操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody FinSubmittedModule001Dto dto) {
        finSubmittedModule001Service.delete(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0067"));
    }
}
