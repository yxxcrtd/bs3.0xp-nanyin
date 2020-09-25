package com.joyintech.tams.poc.directtransaction.uptrinqsec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.uptrinqsec.dto.UpTrInqSec001Dto;

import org.springframework.stereotype.Service;

import com.joyintech.framework.select.SelectOptionDto;

@Service
public class UpTrInqSec002Service extends CommonExportService<UpTrInqSec001Dto> {


	
	/**
     * 组合代码的联想下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
                        + "/UpTrInqSec/getPortfolioId", map,SelectOptionDto.class);
    }

    /**
     * 资产代码
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
                        + "/UpTrInqSec/getFinprodMarketId", map,SelectOptionDto.class);
    }
    /**
     * 交易对手
     */
    public List<SelectOptionDto> getCounterId(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqSec/getCounterId",
            map, SelectOptionDto.class);
    }

    public List<SelectOptionDto> getAcctNo(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqSec/getAcctNo", param,
            SelectOptionDto.class);
    }

    public List<SelectOptionDto> getTrdAcctId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqSec/getTrdAcctId", param,
            SelectOptionDto.class);
    }
    /**
     * 获取新建界面的资产代码联想下拉框框
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
                                       + "/UpTrInqSec/getfinprodIdForAdd"
                                       ,param,
            SelectOptionDto.class);

    }
    
    /**
     * 查询dto
     */
    public UpTrInqSec001Dto selectByPrimryKey (String entrustId){
    	return restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqSec/selectByPrimryKey?entrustId="+
    			entrustId,UpTrInqSec001Dto.class);
    }
    
    /**
     * 资产名称联动
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public String getFinprodName(String finprodId) {
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqSec/getFinprodName",map,
            String.class);
    }
}
