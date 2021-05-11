package com.joyintech.service.svrcom.system.chbemployee.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.chbemployee.service.ChbEmployeeInfo001Service;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.mstbond.service.MstBondCreditRating001Service;
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
@RequestMapping("/ChbEmployeeInfo")
public class ChbEmployeeInfoController {

    @Autowired
    private ChbEmployeeInfo001Service chbEmployeeInfo001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ChbEmployeeInfo> search(@RequestBody Map<String,Object> paramMap) {
        return chbEmployeeInfo001Service.searchForPage(paramMap);
    }

    @PostMapping("updateChbEmployeeInfo")
    public void updateChbEmployeeInfo(@RequestBody List<ChbEmployeeInfo> dtoList) {
        chbEmployeeInfo001Service.updateChbEmployeeInfo(dtoList);
        return ;
    }

    @PostMapping("queryReportList")
    public List<ChbEmployeeInfo> queryReportList(@RequestBody Map<String, Object> pageParam) {
        List<ChbEmployeeInfo> list = chbEmployeeInfo001Service.queryReportList(pageParam);
        return list;
    }


    @PostMapping("selectOneDate")
    public List<ChbEmployeeInfo> selectOneDate(@RequestBody Map<String, Object> param) {
        List<ChbEmployeeInfo> list = chbEmployeeInfo001Service.selectOneDate(param);
        return list;
    }

    @PostMapping("selectChbMstCounterPartyData")
    public List<Integer> selectChbMstCounterPartyData (@RequestBody List<ChbMstCounterParty> dtoList){
        List<Integer> intList =chbEmployeeInfo001Service.selectChbMstCounterPartyData(dtoList);
        return intList;
    }
    @PostMapping("insertChbEmployeeInfoData")
    public void insertChbEmployeeInfoData(@RequestBody List<ChbEmployeeInfo> dtoList) {
        chbEmployeeInfo001Service.insertChbEmployeeInfoData(dtoList);
        return ;
    }
    @PostMapping("updateOne")
    public void updateOne(@RequestBody Map<String, Object> param) {
        chbEmployeeInfo001Service.updateOne(param);
        return ;
    }

}
