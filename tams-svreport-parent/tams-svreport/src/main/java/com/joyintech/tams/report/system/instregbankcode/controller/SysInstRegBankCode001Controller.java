/*
 * 文件名：SysInstRegBankCode001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.report.system.instregbankcode.service.SysInstRegBankCode001Service;
import com.joyintech.tams.report.system.instregbankcode.service.SysInstRegBankCodeMService;

/**
 * 机构登记银行代码管理画面
 *
 * @author 刘二通
 * @version 1.0 2018年5月17日
 * @see SysInstRegBankCode001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/system/instregbankcode/SysInstRegBankCode001")
@PageId("SysInstRegBankCode001")
public class SysInstRegBankCode001Controller extends BaseController {

    /**
     * SysInstRegBankCode001Service
     */
    @Autowired
    private SysInstRegBankCodeMService sysInstRegBankCodeMService;
    @Autowired
    private SysInstRegBankCode001Service sysInstRegBankCode001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<SysInstRegBankCode001Dto> searchPage() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return sysInstRegBankCodeMService.searchForPage(pageParam);
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
        return this.sysInstRegBankCodeMService.checkConfirmMsgForImportData(fileName);
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
        return this.sysInstRegBankCodeMService.importData(fileName);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<SysInstRegBankCode001Dto> dtoList = this.sysInstRegBankCodeMService.queryExportList(RequestUtil.getPageParam());
        File outFile = sysInstRegBankCode001Service.makeFile(
            "/com/joyintech/tams/report/system/instregbankcode/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
            this.getMessage("SYSTEM_001_999") + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(DateUtil.platDateFromCache()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
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
        sysInstRegBankCodeMService.doDelete(deleteKeys);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
    }
}
