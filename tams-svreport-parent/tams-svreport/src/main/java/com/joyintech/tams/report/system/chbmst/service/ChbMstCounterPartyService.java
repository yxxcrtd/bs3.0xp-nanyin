package com.joyintech.tams.report.system.chbmst.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChbMstCounterPartyService extends BaseService<ChbMstCounterParty> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ChbMstCounterParty> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/findPage", paramMap,
                ChbMstCounterParty.class);
    }
    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    public List<ChbMstCounterParty> getCounterId(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/getCounterId",param,
                ChbMstCounterParty.class);
    }
    public void updateChbMstCounterParty(List<ChbMstCounterParty> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/updateChbMstCounterParty",dtoList,
                ChbMstCounterParty.class);
        return;
    }

    public void deleteChbMstCounterPartyErrorData(List<ChbMstCounterParty> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/deleteChbMstCounterPartyErrorData",dtoList,
                ChbMstCounterParty.class);
        return;
    }
    public List<ChbMstCounterParty> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/queryReportList",pageParam,
                ChbMstCounterParty.class);
    }
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/checkConfirmMsgForImportData",
                param,
                SvrAjaxResult.class);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("tempList", ExcelUtil.getTempList1(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/importData",
                param,
                ExcelValidDto.class);
    }
    public List<ChbMstCounterParty> selectOneDate(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/selectOneDate",map,
                ChbMstCounterParty.class);
    }
    public List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(String datauuid) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/selectChbMstCounterPartyErrordata",datauuid,
                ChbMstCounterPartyErrodataWithBLOBs.class);
    }
    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/selectChbMstCounterPartyData",dtoList,
                Integer.class);
    }
    public void insertChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/insertChbMstCounterPartyData",dtoList,
                ChbMstCounterParty.class);
        return;
    }
    public void updateOne(ChbMstCounterParty chb) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbMstCounterParty/updateOne",chb,
                ChbMstCounterParty.class);
        return;
    }



    public List<SysDataDict> getSysDataDict(Map<String , Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/getSysDataDict",map,
                SysDataDict.class);
    }



}
