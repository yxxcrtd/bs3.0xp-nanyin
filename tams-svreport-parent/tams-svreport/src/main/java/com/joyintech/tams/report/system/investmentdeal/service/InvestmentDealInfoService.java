package com.joyintech.tams.report.system.investmentdeal.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentDealInfoService extends BaseService<InvestmentDealInfoDto> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<InvestmentDealInfoDto> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/findPage", paramMap,
                InvestmentDealInfoDto.class);
    }
    public List<InvestmentDealInfoDto> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/queryReportList",pageParam,
                InvestmentDealInfoDto.class);
    }
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/checkConfirmMsgForImportData",
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
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/importData",
                param,
                ExcelValidDto.class);
    }
    public InvestmentDealInfoDto selectOneDate(Map<String, Object> map) {
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/selectOneDate",map,
                InvestmentDealInfoDto.class);
    }
    public ChbInvestTradeErrordataWithBLOBs selectInvestmentDealInfoErrordata(String datauuid) {
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/selectInvestmentDealInfoErrordata",datauuid,
                ChbInvestTradeErrordataWithBLOBs.class);
    }

    public List<SelectOptionDto> getProdId(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/getProdId",param,
                SelectOptionDto.class);
    }

    public void save(InvestmentDealInfoDto dto) {
         restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/save",dto,
                 InvestmentDealInfoDto.class);
    }

    public List<SelectOptionDto> getTradeCounter(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/getTradeCounter",param,
                SelectOptionDto.class);
    }

    public void delData(InvestmentDealInfoDto dto) {
         restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/InvestmentDealInfo/delData",dto,
                 InvestmentDealInfoDto.class);
    }


}
