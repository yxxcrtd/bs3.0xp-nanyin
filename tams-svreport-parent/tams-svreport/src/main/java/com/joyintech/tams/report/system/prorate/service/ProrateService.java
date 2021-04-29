package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProrateService extends BaseService<DisRepCashYield> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<DisRepCashYield> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/findPage", paramMap,
                DisRepCashYield.class);
    }
    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/getProdinfo",param,
                ChbPrdBaseinfo.class);
    }

    public List<SysDataDict> getSysDataDict(Map<String , Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/getSysDataDict",map,
                SysDataDict.class);
    }

    public List<Integer> selectPrdFeeData(List<DisRepCashYield> dtoList) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/selectPrdFeeData",dtoList,
                Integer.class);
    }

    public void insertPrdFeeData(List<DisRepCashYield> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/insertPrdFeeData",dtoList,
                DisRepCashYield.class);
        return;
    }

    public void updateDisRepCashYield(List<DisRepCashYield> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/updateDisRepCashYield",dtoList,
                DisRepCashYield.class);
        return;
    }

    public void deleteDisRepCashYieldErrorData(List<DisRepCashYield> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/deleteDisRepCashYieldErrorData",dtoList,
                DisRepCashYield.class);
        return;
    }

    public List<DisRepCashYield> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/queryReportList",pageParam,
                DisRepCashYield.class);
    }


    public List<DisRepCashYield> selectOneDate(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/selectOneDate",map,
                DisRepCashYield.class);
    }

    public List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(String datauuid) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/selectdisPrdFeeRateErrordata",datauuid,
                DisPrdFeeRateInfoErrodataWithBLOBs.class);
    }

    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/checkConfirmMsgForImportData",
                param,
                AjaxResult.class);
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
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/importData",
                param,
                ExcelValidDto.class);
    }

    public void updateOne(DisRepCashYield d) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/updateOne",d,
                DisRepCashYield.class);
        return;
    }
}
