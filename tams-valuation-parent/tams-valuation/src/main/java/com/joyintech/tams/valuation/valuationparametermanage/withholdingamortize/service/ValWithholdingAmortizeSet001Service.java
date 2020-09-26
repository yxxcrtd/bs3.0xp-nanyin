package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 待摊设置
 * 
 * @author tanxuan
 * @version 1.0 2018年11月15日
 * @see ValWithholdingAmortizeSet001Service
 * @since 1.0
 */
@Service
public class ValWithholdingAmortizeSet001Service extends CommonExportService<ValWithholdingAmortizeSet001Dto> {

    /**
     * 账套代码
     * 
     * @param pageParam
     * @return page
     */
    public Page<SelectOptionDto> getBooksetIdNew(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                                        + "/ValWithholdingAmortizeSet001/getBooksetIdNew",
            pageParam, SelectOptionDto.class);
    }

    /**
     * 分页查询
     * 
     * @param params
     * @return page
     */
    public Page<ValWithholdingAmortizeSet001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValWithholdingAmortizeSet001/findPage",
            params, ValWithholdingAmortizeSet001Dto.class);
    }

    /**
     * check
     * 
     * @param params
     * @return page
     */
    public List<SelectOptionDto> check(ValWithholdingAmortizeSet002Dto param) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValWithholdingAmortizeSet001/check",
            param, SelectOptionDto.class);
    }

}
