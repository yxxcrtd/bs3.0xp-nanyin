package com.joyintech.service.svrcom.system.chbmst.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.chbmst.service.ChbMstCounterParty001Service;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
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
@RequestMapping("/ChbMstCounterParty")
public class ChbMstCounterPartyController {

    @Autowired
    private ChbMstCounterParty001Service chbMstCounterParty001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ChbMstCounterParty> search(@RequestBody Map<String,Object> paramMap) {
        return chbMstCounterParty001Service.searchForPage(paramMap);
    }
    @PostMapping("getCounterId")
    public List<ChbMstCounterParty> getCounterId(@RequestBody Map<String, Object> param) {
        List<ChbMstCounterParty> list = chbMstCounterParty001Service.getCounterId(param);
        return list;
    }
    @PostMapping("updateChbMstCounterParty")
    public void updateChbMstCounterParty(@RequestBody List<ChbMstCounterParty> dtoList) {
        chbMstCounterParty001Service.updateChbMstCounterParty(dtoList);
        return ;
    }
    @PostMapping("deleteChbMstCounterPartyErrorData")
    public void deleteChbMstCounterPartyErrorData(@RequestBody List<ChbMstCounterParty> dtoList) {
        chbMstCounterParty001Service.deleteChbMstCounterPartyErrorData(dtoList);
        return ;
    }
    @PostMapping("queryReportList")
    public List<ChbMstCounterParty> queryReportList(@RequestBody Map<String, Object> param) {
        List<ChbMstCounterParty> list = chbMstCounterParty001Service.queryReportList(param);
        return list;
    }
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return chbMstCounterParty001Service.checkConfirmMsgForImportData(dataList);
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
        return this.chbMstCounterParty001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }



    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = chbMstCounterParty001Service.getSysDataDict(param);
        return list;
    }
    @PostMapping("selectPrdFeeData")
    public List<Integer> selectPrdFeeData (@RequestBody List<DisRepCashYield> dtoList){
        List<Integer> intList =chbMstCounterParty001Service.selectPrdFeeData(dtoList);
        return intList;
    }
    @PostMapping("insertPrdFeeData")
    public void insertPrdFeeData(@RequestBody List<DisRepCashYield> dtoList) {
        chbMstCounterParty001Service.insertPrdFeeData(dtoList);
        return ;
    }

    @PostMapping("selectOneDate")
    public List<ChbMstCounterParty> selectOneDate(@RequestBody Map<String, Object> param) {
        List<ChbMstCounterParty> list = chbMstCounterParty001Service.selectOneDate(param);
        return list;
    }
    @PostMapping("selectdisPrdFeeRateErrordata")
    public List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(String datauuid) {
        List<DisPrdFeeRateInfoErrodataWithBLOBs> list = chbMstCounterParty001Service.selectdisPrdFeeRateErrordata(datauuid);
        return list;
    }

}
