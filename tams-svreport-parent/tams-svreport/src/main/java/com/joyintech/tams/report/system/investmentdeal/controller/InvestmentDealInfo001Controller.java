package com.joyintech.tams.report.system.investmentdeal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import com.joyintech.tams.report.system.investmentdeal.service.InvestmentDealInfo001Service;
import com.joyintech.tams.report.system.investmentdeal.service.InvestmentDealInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

@Controller
@PageId("InvestmentDealInfo001")
@RequestMapping("/svreport/system/investmentdeal/InvestmentDealInfo001")
public class InvestmentDealInfo001Controller extends BaseController {

    @Autowired
    private InvestmentDealInfoService investmentDealInfoService;

    @Autowired
    private InvestmentDealInfo001Service investmentDealInfo001Service;

    @Override
    @RequestMapping(PAGE_INIT)
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
    public Page<InvestmentDealInfoDto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return investmentDealInfoService.searchForPage(param);
    }

    /**
     * 流程delete
     */
    @ResponseBody
    @PostMapping("deleteData")
    public AjaxResult deleteData(@RequestBody @Validated({
            ValidateGroupEdit.class}) InvestmentDealInfoDto dto, BindingResult result) {
        return investmentDealInfoService.delData(dto);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<InvestmentDealInfoDto> dtoList = this.investmentDealInfoService.queryReportList(RequestUtil.getPageParam());
        File outFile = investmentDealInfo001Service.makeFile(
                "/com/joyintech/tams/report/system/investmentdeal/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                        + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return investmentDealInfoService.checkConfirmMsgForImportData(fileName);
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
        return this.investmentDealInfoService.importData(fileName);
    }

    @ResponseBody
    @RequestMapping("/getProdId")
    public List<SelectOptionDto> getProdId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<SelectOptionDto> list = investmentDealInfoService.getProdId(param);
        return list;
    }
}
