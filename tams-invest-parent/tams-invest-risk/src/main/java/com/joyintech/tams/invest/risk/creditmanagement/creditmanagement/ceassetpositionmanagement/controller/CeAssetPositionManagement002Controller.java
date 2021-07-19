/*
 * 文件名：CeAssetPositionManagement002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.service.CeAssetPositionManagement002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月15日
 * @see CeAssetPositionManagement002Controller
 * @since 1.0
 */
@Controller
@PageId("CeAssetPositionManagement002")
@RequestMapping("/invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement002")
public class CeAssetPositionManagement002Controller extends BaseController {
    
    /**
     * ProgressBarService
     */
    @Autowired
    private ProgressBarService progressBarService;
    
    /**
     * 
     */
    @Autowired
    private CeAssetPositionManagement002Service ceAssetPositionManagement002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 查询委外组合代码联想下拉框
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryGroupIdInfo")
    public List<SelectOptionDto> queryGroupIdInfo() {
        return ceAssetPositionManagement002Service.queryGroupIdInfo(RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 
     * <一句话功能简述>
     * 去除上传进度条
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile() {
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
    
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
        throws Exception{
        return ceAssetPositionManagement002Service.importData(fileName);
    }
    
    /**
     * <一句话功能简述> 导出模板
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ceAssetPositionManagement002Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
