package com.joyintech.tams.report.system.systempbccapassetinfo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.systempbccapassetinfo.dto.PbcCapAssetInfo001Dto;
import com.joyintech.tams.report.system.systempbccapassetinfo.service.PbcCapAssetInfo001MService;
import com.joyintech.tams.report.system.systempbccapassetinfo.service.PbcCapAssetInfo001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 人行资金信托代码管理Controller
 *
 * @author kx
 *
 * @create 2019/12/24
 */
@Controller
@PageId("PbcCapAssetInfo001")
@RequestMapping("/svreport/system/systempbccapassetinfo/PbcCapAssetInfo001")
public class PbcCapAssetInfo001Controller extends BaseController {

    @Autowired
    private PbcCapAssetInfo001MService pbcCapAssetInfo001MService;

    @Autowired
    private PbcCapAssetInfo001Service pbcCapAssetInfo001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 判断数据是否从主页代维护信息加载
        if (RequestUtil.getPageParam().containsKey("status")) {
            model.addAttribute("isFromMain", "01");
        } else {
            model.addAttribute("isFromMain", "02");
        }
        String myOrgCode = CommonUtil.getOrgCode();
        if (StringUtil.isNotEmpty(myOrgCode)) {
            model.addAttribute("MY_AGENCY_CODE", myOrgCode);
        }
        // dingzhipeng add end
        super.setSelectOptionToModel(model, DictCode.目标客户类型, DictCode.发送状态);

        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }


    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<PbcCapAssetInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return pbcCapAssetInfo001MService.searchForPage(param);
    }


    /**
     * 资产代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetCode")
    public List<SelectOptionDto> queryInsideProdIdList(@RequestParam Map<String, Object> param) throws Exception {
        return pbcCapAssetInfo001MService.queryInsideProdIdList(param);
    }


    /**
     * 人行资金信托代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPobCasSeTidList")
    public List<SelectOptionDto> queryPobCasSeTidList(@RequestParam Map<String, Object> param) throws Exception {
        return pbcCapAssetInfo001MService.queryPobCasSeTidList(param);
    }

    /**
     * 删除人行资金信托代码
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "deletePbcCapAssetInfo", method = RequestMethod.POST)
    public AjaxResult deletePbcCapAssetInfo(@RequestBody Map<String, Object> map) {
        this.pbcCapAssetInfo001MService.deletePbcCapAssetInfo(map);
        AjaxResult wb = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        return wb;
    }


    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        List<PbcCapAssetInfo001Dto> dtoList = this.pbcCapAssetInfo001MService.queryReportList(RequestUtil.getPageParam());
        File outFile = pbcCapAssetInfo001Service.makeFile(
                "/com/joyintech/tams/report/system/systempbccapassetinfo/conf/export_conf.xml", dtoList);
        return ExportUtil.export("人行资金信托代码管理" + GlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                + "xlsx", outFile, true);
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
        return pbcCapAssetInfo001MService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件之前检查资金信托代码有没有对应多个资产代码(Excel)
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportDataByExcel", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportDataByExcel(String fileName)  {
        return pbcCapAssetInfo001MService.checkConfirmMsgForImportDataByExcel(fileName);
    }

    /**
     * 导入数据文件之前检查资金信托代码有没有对应多个资产代码(数据库)
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportDataByDB", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportDataByDB(String fileName)  {
        return pbcCapAssetInfo001MService.checkConfirmMsgForImportDataByDB(fileName);
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
        return this.pbcCapAssetInfo001MService.importData(fileName);
    }


}
