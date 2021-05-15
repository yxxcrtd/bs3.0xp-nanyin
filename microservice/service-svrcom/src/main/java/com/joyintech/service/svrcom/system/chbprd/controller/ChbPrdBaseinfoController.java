package com.joyintech.service.svrcom.system.chbprd.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.chbprd.service.ChbPrdBaseinfo001Service;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.service.ProRateInfo001Service;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ChbPrdBaseinfo")
public class ChbPrdBaseinfoController {

    @Autowired
    private ChbPrdBaseinfo001Service chbPrdBaseinfo001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ChbPrdBaseinfo> search(@RequestBody Map<String,Object> paramMap) {
        return chbPrdBaseinfo001Service.searchForPage(paramMap);
    }
    @PostMapping("getProdinfo")
    public List<ChbPrdBaseinfo> getProdinfo(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = chbPrdBaseinfo001Service.getProdinfo(param);
        return list;
    }
    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = chbPrdBaseinfo001Service.getSysDataDict(param);
        return list;
    }
    @PostMapping("selectPrdFeeData")
    public List<Integer> selectPrdFeeData (@RequestBody List<DisRepCashYield> dtoList){
        List<Integer> intList =chbPrdBaseinfo001Service.selectPrdFeeData(dtoList);
        return intList;
    }
    @PostMapping("insertPrdFeeData")
    public void insertPrdFeeData(@RequestBody List<ChbPrdBaseinfo> dtoList) {
        chbPrdBaseinfo001Service.insertPrdFeeData(dtoList);
        return ;
    }
    @PostMapping("updateChbPrdBaseinfo")
    public void updateChbPrdBaseinfo(@RequestBody List<ChbPrdBaseinfo> dtoList) {
        chbPrdBaseinfo001Service.updateChbPrdBaseinfo(dtoList);
        return ;
    }
    @PostMapping("deleteChbPrdBaseinfoErrorData")
    public void deleteChbPrdBaseinfoErrorData(@RequestBody List<ChbPrdBaseinfo> dtoList) {
        chbPrdBaseinfo001Service.deleteChbPrdBaseinfoErrorData(dtoList);
        return ;
    }
    @PostMapping("queryReportList")
    public List<ChbPrdBaseinfo> queryReportList(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = chbPrdBaseinfo001Service.queryReportList(param);
        return list;
    }
    @PostMapping("selectOneDate")
    public List<ChbPrdBaseinfo> selectOneDate(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = chbPrdBaseinfo001Service.selectOneDate(param);
        return list;
    }
    @PostMapping("selectchbPrdBaseinfoErrordata")
    public List<ChbPrdBaseinfoErrodataWithBLOBs> selectchbPrdBaseinfoErrordata(String datauuid) {
        List<ChbPrdBaseinfoErrodataWithBLOBs> list = chbPrdBaseinfo001Service.selectchbPrdBaseinfoErrordata(datauuid);
        return list;
    }
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return chbPrdBaseinfo001Service.checkConfirmMsgForImportData(dataList);
    }
    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.chbPrdBaseinfo001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    @PostMapping("updateOne")
    public void updateOne(@RequestBody Map<String, Object> mapOld) {
        chbPrdBaseinfo001Service.updateOne(mapOld);
        return ;
    }
}
