/*
 * 文件名：UpTrInqBuyBack002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.service;

import org.springframework.beans.factory.annotation.Qualifier;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.uptrinqbuyback.dto.UpTrInqBuyBack001Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 回购交易新增报价
 * 
 * @author wanghb
 * @version 1.0 2019年2月15日
 * @see UpTrInqBuyBack002Service
 * @since 1.0
 */
@Service
public class UpTrInqBuyBack002Service extends CommonExportService<UpTrInqBuyBack001Dto> {



    public List<SelectOptionDto> selectCounterId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqBuyBack002/getCounterId", param,
            SelectOptionDto.class);
    }

    public List<SelectOptionDto> getPortfolioId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqBuyBack002/getPortfolioId", param,
            SelectOptionDto.class);
    }

    public List<SelectOptionDto> getAcctNo(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqBuyBack002/getAcctNo", param,
            SelectOptionDto.class);
    }

    public List<SelectOptionDto> getTrdAcctId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqBuyBack002/getTrdAcctId", param,
            SelectOptionDto.class);
    }
    
    /**
     * 查询dto
     */
    public UpTrInqBuyBack001Dto selectByPrimryKey (String entrustId){
    	return restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/UpTrInqBuyBack/selectByPrimryKey?entrustId="+
    			entrustId,UpTrInqBuyBack001Dto.class);
    }
}
