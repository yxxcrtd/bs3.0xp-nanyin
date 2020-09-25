/*
 * 文件名：RskValRelateImport002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.controller;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.invest.common.constant.BussinessDictCode;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImport001Dto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImport002Service;

/**
 * 估值表组合关联导入映射新建
 * @author fanshuanghu
 * @version 1.0 2018年12月18日
 * @see RskValRelateImport002Controller
 * @since 1.0
 */
@Controller
@PageId("RskValRelateImport002")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalrelateimport/RskValRelateImport002")
public class RskValRelateImport002Controller extends BaseController{
    
    /**
     * RskValRelateImport002Service
     */
    @Autowired
    private RskValRelateImport002Service rskValRelateImport002Service;
    
    @Override
    public String init(Model model) {
    	//---add by 裴家俊 20190514----start------------
    	setSelectOptionToModel(model, new BussinessDictCode[] {BussinessDictCode.估值过滤器});
    	//---add by 裴家俊 20190514----end------------
        String ptlId = RequestUtil.getParam("ptlId");
        if (StringUtil.isNotEmpty(ptlId)) {
            RskValRelateImport001Dto entity = rskValRelateImport002Service.selectInfoById(ptlId);
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 指标结构映射联想下拉框
     * @return 下拉框信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryStructMapId")
    public List<SelectOptionDto> queryStructMapId() {
        return rskValRelateImport002Service.queryStructMapId(RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * <一句话功能简述> 汇总科目映射联想下拉框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySumsubMapId")
    public List<SelectOptionDto> querySumsubMapId() {
        return rskValRelateImport002Service.querySumsubMapId(RequestUtil.getPageParam());
    }
    
    /**
     * <一句话功能简述> 资产科目映射联想下拉框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAssetSubMapId")
    public List<SelectOptionDto> queryAssetSubMapId() {
        return rskValRelateImport002Service.queryAssetSubMapId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * <一句话功能简述>
     * 点击保存时的check
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check (@Valid @RequestBody RskValRelateImport001Dto dto, BindingResult result){
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * <一句话功能简述> 插入操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("insert")
    public AjaxResult insert(@RequestBody RskValRelateImport001Dto dto) throws Exception {
        rskValRelateImport002Service.insert(dto);
        return new AjaxResult(this.getMessage("BUSS_COM_E_0033"));
    }
    
    /**
     * <一句话功能简述> 编辑更新操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResult update(@RequestBody RskValRelateImport001Dto dto) throws Exception {
        rskValRelateImport002Service.update(dto);
        return new AjaxResult(this.getMessage("BUSS_COM_E_0033"));
    }
    
    /**
     * <一句话功能简述> 删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody RskValRelateImport001Dto dto) {
        rskValRelateImport002Service.delete(dto);
        return new AjaxResult(this.getMessage("RSK_W_0026"));
    }
}
