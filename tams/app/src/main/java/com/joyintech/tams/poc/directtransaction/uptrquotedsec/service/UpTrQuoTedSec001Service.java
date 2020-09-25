package com.joyintech.tams.poc.directtransaction.uptrquotedsec.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.uptrquotedsec.dto.UpTrQuoTedSec001Dto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UpTrQuoTedSec001Service extends CommonExportService<UpTrQuoTedSec001Dto> {


	
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
                        + "/UpTrQuoTedSec/getPortfolioId", map,SelectOptionDto.class);
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
                        + "/UpTrQuoTedSec/getFinprodMarketId", map,SelectOptionDto.class);
    }
    /**
     * 交易对手
     */
    public List<SelectOptionDto> getCounterId(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME + "/UpTrQuoTedSec/getCounterId",
            map, SelectOptionDto.class);
    }
}
