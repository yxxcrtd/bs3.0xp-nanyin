package com.joyintech.tams.report.system.bonddiscountrate.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChbSecDiscountRateService extends BaseService<ChbSecDiscountRateDto> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ChbSecDiscountRateDto> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbSecDiscountRate/findPage", paramMap,
                ChbSecDiscountRateDto.class);
    }

    public ChbSecDiscountRateDto selectOneDate(Map<String, Object> map) {
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbSecDiscountRate/selectOneDate",map,
                ChbSecDiscountRateDto.class);
    }

    public void save(ChbSecDiscountRateDto dto) {
         restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbSecDiscountRate/save",dto,
                 ChbSecDiscountRateDto.class);
    }

}
