/*
 * 文件名：RskValTableImport001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.valtblimport.controller;

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
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImport001Dto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImport002Service;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValTableImportParamDto;
import com.joyintech.tams.invest.risk.valtblimport.service.RskValTableImportService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月27日
 * @see RskValTableImport001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValTableImport001")
@RequestMapping("/invest/risk/valtblimport/RskValTableImport001")
public class RskValTableImport001Controller extends BaseController{

    @Autowired
    private RskValRelateImport002Service rskValRelateImport002Service;
    
    @Autowired
    private RskValTableImportService rskValTableImportService;
    @Autowired
    private ProgressBarService progressBarService;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 获取组合关联映射信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectRskValRelateImportById")
    public RskValRelateImport001Dto selectRskValRelateImportById(@RequestBody RskValRelateImport001Dto dto){
        if(StringUtil.isNotEmpty(dto.getPtlId())){
            dto =  rskValRelateImport002Service.selectInfoById(dto.getPtlId());
        }
        return dto;
    }
    
    /**
     * 点击预览时check
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskValTableImportParamDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile() {
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
    
    /**
     * 组合代码下拉框(分页)
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getGroupIdInfo")
    @ResponseBody
    public Page<SelectOptionDto> getGroupIdInfo()
            throws Exception {
        return rskValTableImportService.getGroupIdInfo(RequestUtil.getPageParam());
    }
}
