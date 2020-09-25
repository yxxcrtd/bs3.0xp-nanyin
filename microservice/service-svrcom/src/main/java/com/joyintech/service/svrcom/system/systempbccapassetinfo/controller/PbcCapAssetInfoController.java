package com.joyintech.service.svrcom.system.systempbccapassetinfo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.systempbccapassetinfo.dto.PbcCapAssetInfo001Dto;
import com.joyintech.tams.report.system.systempbccapassetinfo.service.PbcCapAssetInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 人行资金信托代码管理Controller
 *
 * @author kx
 *
 * @create 2019/12/24
 */
@RestController
@RequestMapping("/PbcCapAssetInfo")
public class PbcCapAssetInfoController {

    @Autowired
    private PbcCapAssetInfo001Service pbcCapAssetInfo001Service;


    /**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PbcCapAssetInfo001Dto> search(@RequestBody Map<String, Object> paramMap) {
        return pbcCapAssetInfo001Service.searchForPage(paramMap);
    }


    /**
     * 资产代码下拉框取得值
     *
     * @param param
     * @return
     */
    @PostMapping("getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList(@RequestBody Map<String, Object> param) {
        return pbcCapAssetInfo001Service.getAssetCodeList(param);
    }

    /**
     * 人行资金信托代码下拉框取得值
     *
     * @param param
     * @return
     */
    @PostMapping("setPobCasSeTidList")
    public List<SelectOptionDto> setPobCasSeTidList(@RequestBody Map<String, Object> param) {
        return pbcCapAssetInfo001Service.setPobCasSeTidList(param);
    }

    /**
     * 删除
     *
     * @param map
     */
    @PostMapping("deletePbcCapAssetInfoData")
    public void deleteRegCode(@RequestBody Map<String, Object> map) {
        pbcCapAssetInfo001Service.deteteRegCode(map);
    }


    /**
     * 导出excel查询
     *
     * @return 结果数据
     */
    @PostMapping("querySearchPageList")
    public List<PbcCapAssetInfo001Dto> querySearchPageList(@RequestBody Map<String, Object> paramMap) {
        return pbcCapAssetInfo001Service.querySearchPageList(paramMap);

    }

    /**
     * 导入数据文件
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String orgCode = param.get("orgCode") == null ? null : param.get("orgCode").toString();
        String deptCode = param.get("deptCode") == null ? null : param.get("deptCode").toString();
        return this.pbcCapAssetInfo001Service.importData(dataList, tempList, orgCode, deptCode);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode = param.get("orgCode") == null ? null : param.get("orgCode").toString();
        String deptCode = param.get("deptCode") == null ? null : param.get("deptCode").toString();
        return pbcCapAssetInfo001Service.checkConfirmMsgForImportData(dataList, orgCode, deptCode);
    }

    /**
     * 导入数据文件之前检查资金信托代码有没有对应多个资产代码(Excel)
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportDataByExcel")
    public AjaxResult checkConfirmMsgForImportDataByExcel(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode = param.get("orgCode") == null ? null : param.get("orgCode").toString();
        String deptCode = param.get("deptCode") == null ? null : param.get("deptCode").toString();
        return pbcCapAssetInfo001Service.checkConfirmMsgForImportDataByExcel(dataList, orgCode, deptCode);
    }

    /**
     * 导入数据文件之前检查资金信托代码有没有对应多个资产代码(数据库)
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportDataByDB")
    public AjaxResult checkConfirmMsgForImportDataByDB(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode = param.get("orgCode") == null ? null : param.get("orgCode").toString();
        String deptCode = param.get("deptCode") == null ? null : param.get("deptCode").toString();
        return pbcCapAssetInfo001Service.checkConfirmMsgForImportDataByDB(dataList, orgCode, deptCode);
    }

}
