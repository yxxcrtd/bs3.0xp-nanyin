/*
 * 文件名：EquityBaseInfo001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月13日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.maintenance.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.maintenance.dto.EquiteBaseInfo001Dto;
import com.joyintech.tams.report.maintenance.service.EquityBaseInfo001Service;
import com.joyintech.tams.report.maintenance.service.EquityBaseInfoMService;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 权益基期信息维护画面控制器
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月13日
 * @see EquityBaseInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("EquityBaseInfo001")
@RequestMapping("/maintenance/EquityBaseInfo001")
public class EquityBaseInfo001Controller extends BaseController {

    /**
     * EquityBaseInfo001Service
     */
    @Autowired
    private EquityBaseInfoMService equityBaseInfoMService;

    /**
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String myOrgCode = CommonUtil.getOrgCode();
        if (StringUtil.isNotEmpty(myOrgCode)) {
            model.addAttribute("MY_AGENCY_CODE", myOrgCode);
        }
        super.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 产品名称下拉框
     * 
     * @return 产品名称列表
     */
    @RequestMapping("getPrdName")
    @ResponseBody
    public List<SuggestDto> getPrdName(String param) {
        return equityBaseInfoMService.queryPrdNameList(param);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<EquiteBaseInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return equityBaseInfoMService.searchForPage(param);
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
        return this.equityBaseInfoMService.importData(fileName);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){
        List<EquiteBaseInfo001Dto> dtoList = this.equityBaseInfoMService.queryReportList(
            RequestUtil.getPageParam());
        File outFile = equityBaseInfoMService.makeFile(
            "/com/joyintech/tams/report/maintenance/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
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
        equityBaseInfoMService.doDelete(deleteKeys);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
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
        return equityBaseInfoMService.checkConfirmMsgForImportData(fileName);
    }
}
