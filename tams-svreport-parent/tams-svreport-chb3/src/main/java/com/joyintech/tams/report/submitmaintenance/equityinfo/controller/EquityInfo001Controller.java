/*
 * 文件名：EquityInfo001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmaintenance.equityinfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfoMService;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 投资者身份信息维护
 *
 * @author Administrator
 * @version 1.0 2017年9月16日
 * @since 1.0
 */
@Controller
@PageId("EquityInfo001")
@RequestMapping("/svreport/submitmaintenance/equityinfo/EquityInfo001")
public class EquityInfo001Controller extends BaseController {

    /**
     * dao
     */
    @Autowired
    private EquityInfo001Service equityInfo001Service;
    @Autowired
    private EquityInfoMService equityInfoMService;
    /**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // dingzhipeng add start
        // 判断数据是否从主页代维护信息加载
        if (RequestUtil.getPageParam().containsKey("status")) {
            model.addAttribute("isFromMain", "01");
        }
        else {
            model.addAttribute("isFromMain", "02");
        }
        if (RequestUtil.getPageParam().containsKey("interfType")) {
            model.addAttribute("status", "A");
            model.addAttribute("isFromMain", "01");
        }
        String myOrgCode = CommonUtil.getOrgCode();
        if (StringUtil.isNotEmpty(myOrgCode)) {
            model.addAttribute("MY_AGENCY_CODE", myOrgCode);
        }
        // dingzhipeng add end
        super.setSelectOptionToModel(model, DictCode.维护数据状态, DictCode.投资者类别);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<EquityInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return equityInfoMService.searchForPage(param);
    }

    /**
     * 导出文件
     *
     * @return 导出的excel
     * @see
     * @since 1.0
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){
        // List<EquityInfo001Dto> list =
        // this.equityInfo001Service.queryExportList(RequestUtil.getPageParam());
        // File outFile = equityInfo001Service.makeFile(
        // "/com/joyintech/tams/report/submitmaintenance/equityinfo/conf/export_conf.xml", list);
        // return ExportUtil.export(
        // this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
        // + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
        // + FileNameUtil.getFileExtName(outFile),
        // outFile, true);

        Map<String, Object> pageParam = RequestUtil.getPageParam();
        pageParam.put("export_method_flg", "export");

        return equityInfo001Service.export(this.getPageId(), pageParam);
    }

    /**
     * 将选择的数据导出成excel
     *
     * @return 返回需要的格式
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/exportSelect")
    public ResponseEntity<byte[]> exportSelect() {
        Map<String, Object> map = RequestUtil.getPageParam();
        String exportKeys = (String)map.get("expKeys");
        List<EquityInfo001Dto> list = this.equityInfoMService.queryExportSelectList(exportKeys);
        // 数据状态为有效的数据检查结果置空
        for (EquityInfo001Dto equityInfo001Dto : list) {
            if (equityInfo001Dto.getStatus().equals("E")) {
                equityInfo001Dto.setInterfCheckResult(" ");
            }
        }
        File outFile = equityInfo001Service.makeFile(
            "/com/joyintech/tams/report/submitmaintenance/equityinfo/conf/export_conf.xml", list);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);

        /*
         * Map<String, Object> pageParam = RequestUtil.getPageParam();
         * pageParam.put("export_method_flg", "exportSelect"); return
         * equityInfo001Service.export(this.getPageId(), pageParam);
         */
    }

    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/checkDelete", method = RequestMethod.POST)
    public AjaxResult checkDelete(@RequestBody EquityInfo001Dto dto){
        AjaxResult wb = new AjaxResult();
        List<EquityInfo001Dto> list = dto.getDeleteList();
        List<ValidMessage> errorList = equityInfoMService.checkDeleteData(list);
        if (CollectionUtils.isNotEmpty(errorList)) {
            wb.setErrorList(errorList);
        }
        return wb;
    }

    /**
     * 删除 <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public AjaxResult doDelete(@RequestBody EquityInfo001Dto dto) {
        // Map<String, Object> param = this.getPageParam();
        AjaxResult wb = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        List<EquityInfo001Dto> list = dto.getDeleteList();
        equityInfoMService.doDelete(list);
        return wb;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.equityInfoMService.importData(fileName);
    }

    /**
     * 导入代销数据
     *
     * @param fileName 文件名
     * @return 校验结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/chbImportFile")
    public List<ExcelValidDto> importChbData(String fileName){
        return this.equityInfoMService.importChbData(fileName);
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
        return equityInfoMService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入代销数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForChbImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForChbImportData(String fileName) {
        return equityInfoMService.checkConfirmMsgForChbImportData(fileName);
    }

    /**
     * 修改的check <功能详细描述>
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit", method = RequestMethod.POST)
    public AjaxResult checkEdit(@RequestBody EquityInfo001Dto dto) {
        return equityInfoMService.checkEdit(dto);
    }
}
