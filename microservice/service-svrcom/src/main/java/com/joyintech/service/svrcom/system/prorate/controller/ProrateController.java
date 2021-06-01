package com.joyintech.service.svrcom.system.prorate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
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
@RequestMapping("/Prorate")
public class ProrateController {

    @Autowired
    private ProRateInfo001Service proRateInfo001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<DisRepCashYield> search(@RequestBody Map<String,Object> paramMap) {
        return proRateInfo001Service.searchForPage(paramMap);
    }
    @PostMapping("getProdinfo")
    public List<ChbPrdBaseinfo> getProdinfo(@RequestBody Map<String, Object> param) {
        List<ChbPrdBaseinfo> list = proRateInfo001Service.getProdinfo(param);
        return list;
    }
    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = proRateInfo001Service.getSysDataDict(param);
        return list;
    }
    @PostMapping("selectPrdFeeData")
    public List<Integer> selectPrdFeeData (@RequestBody List<DisRepCashYield> dtoList){
        List<Integer> intList =proRateInfo001Service.selectPrdFeeData(dtoList);
        return intList;
    }
    @PostMapping("insertPrdFeeData")
    public void insertPrdFeeData(@RequestBody List<DisRepCashYield> dtoList) {
        proRateInfo001Service.insertPrdFeeData(dtoList);
        return ;
    }
    @PostMapping("updateDisRepCashYield")
    public void updateDisRepCashYield(@RequestBody List<DisRepCashYield> dtoList) {
        proRateInfo001Service.updateDisRepCashYield(dtoList);
        return ;
    }
    @PostMapping("deleteDisRepCashYieldErrorData")
    public void deleteDisRepCashYieldErrorData(@RequestBody List<DisRepCashYield> dtoList) {
        proRateInfo001Service.deleteDisRepCashYieldErrorData(dtoList);
        return ;
    }
    @PostMapping("queryReportList")
    public List<DisRepCashYield> queryReportList(@RequestBody Map<String, Object> param) {
        List<DisRepCashYield> list = proRateInfo001Service.queryReportList(param);
        return list;
    }
    @PostMapping("selectOneDate")
    public List<DisRepCashYield> selectOneDate(@RequestBody Map<String, Object> param) {
        List<DisRepCashYield> list = proRateInfo001Service.selectOneDate(param);
        return list;
    }
    @PostMapping("selectdisPrdFeeRateErrordata")
    public List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(@RequestBody Map<String, Object> param) {
        List<DisPrdFeeRateInfoErrodataWithBLOBs> list = proRateInfo001Service.selectdisPrdFeeRateErrordata(param);
        return list;
    }
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return proRateInfo001Service.checkConfirmMsgForImportData(dataList);
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
        return this.proRateInfo001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    @PostMapping("updateOne")
    public void updateOne(@RequestBody DisRepCashYield dto) {
        proRateInfo001Service.updateOne(dto);
        return ;
    }

    /**
     * 删除错误表数据
     * @param dto
     */
    @PostMapping("deleteErrorDataOne")
    public void deleteErrorDataOne(@RequestBody DisRepCashYield dto) {
        if(StringUtil.isNotEmpty(dto.getBusiId()) && StringUtil.isNotEmpty(dto.getOldStartDateStr()) && StringUtil.isNotEmpty(dto.getOldCashType2())) {
            proRateInfo001Service.deleteErrorDataOne(dto.getBusiId(), dto.getOldStartDateStr(), dto.getOldCashType2());
        }
    }

    /**
     * 根据uuid删除错误表数据
     * @param uuid
     */
    @PostMapping("deleteErrorDataOneByUuid")
    public void deleteErrorDataOneByUuid(@RequestBody Map<String, Object> param) {
        String uuid=param.get("uuid")==null?null:param.get("uuid").toString();
        if(StringUtil.isNotEmpty(uuid)) {
            proRateInfo001Service.deleteErrorDataOneByUuid(uuid);
        }
    }
}
