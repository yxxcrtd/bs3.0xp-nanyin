package com.joyintech.tams.report.system.disrelorg.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.disrelorg.dto.DisRelOrgInfo001Dto;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisRelOrgService extends BaseService<DisAssociatedInstitutions> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<DisAssociatedInstitutions> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/findPage", paramMap,
                DisAssociatedInstitutions.class);
    }


    public List<SysDataDict> getSysDataDict(Map<String , Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/getSysDataDict",map,
                SysDataDict.class);
    }

    public void updateDisAssociatedInstitutions(List<DisAssociatedInstitutions> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/updateDisAssociatedInstitutions",dtoList,
                DisAssociatedInstitutions.class);
        return;
    }


    public List<DisAssociatedInstitutions> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/queryReportList",pageParam,
                DisAssociatedInstitutions.class);
    }

    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/checkConfirmMsgForImportData",
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
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/importData",
                param,
                ExcelValidDto.class);
    }
    public List<DisAssociatedInstitutions> selectOne(String relorgId) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/selectOne",relorgId,
                DisAssociatedInstitutions.class);
    }

    public void insertOne(DisAssociatedInstitutions dto) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Disrelorg/insertOne",dto,
                DisRepCashYield.class);
        return;
    }
}
