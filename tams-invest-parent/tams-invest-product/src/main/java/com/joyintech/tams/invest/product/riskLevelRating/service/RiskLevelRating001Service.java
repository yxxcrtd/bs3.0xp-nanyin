package com.joyintech.tams.invest.product.riskLevelRating.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: RiskLevelRating001Service
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02
 **/
@Service
public class RiskLevelRating001Service extends CommonExportService<RiskLevelRating001Dto>  {
    /**
     * 产品风险等级评定查询
     * @param pageParam
     * @return
     */
    public Page<RiskLevelRating001Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/findPage", pageParam,
                RiskLevelRating001Dto.class);
    }
    /**
     * 产品风险等级评定产品代码下拉框查询
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> findId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/findId", pageParam,
                SelectOptionDto.class);
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {

            exportParam.config("/com/joyintech/tams/invest/product/riskLevelRating/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);

    }
    @Override
    public Page selectPageData(Map<String, Object> selectParam) {

            Page<RiskLevelRating001Dto> result = findPage(selectParam);
            return result;


    }
}
