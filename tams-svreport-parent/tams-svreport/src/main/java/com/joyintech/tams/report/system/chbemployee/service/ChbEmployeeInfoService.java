package com.joyintech.tams.report.system.chbemployee.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChbEmployeeInfoService extends BaseService<ChbEmployeeInfo> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ChbEmployeeInfo> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/findPage", paramMap,
                ChbEmployeeInfo.class);
    }

    public void updateChbEmployeeInfo(List<ChbEmployeeInfo> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/updateChbEmployeeInfo",dtoList,
                ChbEmployeeInfo.class);
        return;
    }


    public List<ChbEmployeeInfo> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/queryReportList",pageParam,
                ChbEmployeeInfo.class);
    }



    public List<ChbEmployeeInfo> selectOneDate(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/selectOneDate",map,
                ChbEmployeeInfo.class);
    }

    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/selectChbMstCounterPartyData",dtoList,
                Integer.class);
    }
    public void insertChbEmployeeInfoData(List<ChbEmployeeInfo> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/insertChbEmployeeInfoData",dtoList,
                ChbEmployeeInfo.class);
        return;
    }
    public void updateOne(Map<String, Object> param) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbEmployeeInfo/updateOne",param,
                MstBondCreditRating.class);
        return;
    }




}
