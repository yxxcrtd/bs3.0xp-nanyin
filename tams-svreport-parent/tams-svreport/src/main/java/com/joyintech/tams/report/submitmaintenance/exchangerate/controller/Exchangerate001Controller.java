/*
 * 文件名：Exchangerate001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年9月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.exchangerate.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate001Dto;
import com.joyintech.tams.report.submitmaintenance.exchangerate.service.Exchangerate001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;

/**
 * 汇率行情维护Controller
 * @author zuoyinyi
 * @version 1.0 2017年9月22日
 * @see Exchangerate001Controller
 * @since 1.0
 */
@Controller
@PageId("Exchangerate001")
@RequestMapping("/submitmaintenance/exchangerate/Exchangerate001")
public class Exchangerate001Controller extends BaseController {
    
    @Autowired
    private Exchangerate001Service exchangerate001Service;

    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model,DictCode.币种_中债);
        return super.init(model);
    }
    
    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<Exchangerate001Dto> search() {
        return exchangerate001Service.searchForPage(RequestUtil.getPageParam());
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
        return this.exchangerate001Service.checkConfirmMsgForImportData(fileName);
    }
    
    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.exchangerate001Service.importData(fileName);
    }
    
    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){
        List<Exchangerate001Dto> dtoList = this.exchangerate001Service.queryReportList(RequestUtil.getPageParam());
        File outFile = exchangerate001Service.makeFile(
            "/com/joyintech/tams/report/submitmaintenance/exchangerate/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
            this.getMessage("汇率行情信息") + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
    
    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     * 
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public AjaxResult doDelete() {
        String deleteKeys = super.getParam("deleteKeys");
        exchangerate001Service.doDelete(deleteKeys);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
    }

    /**
     * 页面check<br>
     *
     * @param dto 页面check用dto
     * @param result check结果
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "checkCurrentRowData")
    public AjaxResult check(@Valid @RequestBody Exchangerate001Dto dto, BindingResult result) {
        if(result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult wr = new AjaxResult();
        return wr;
    }
}
