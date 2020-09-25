package com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto.FinProductFeaAna001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 可行性分析
 * @author guyunfei
 */
@Service
public class FinProductFeaAna001Service extends CommonExportService<FinProductFeaAna001Dto> {

    /**
     * 画面查询
     * @param pageParam
     * @throws Exception Exception
     * @return
     */
    public Page<FinProductFeaAna001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeaAna/selectForPage",
                pageParam,
                FinProductFeaAna001Dto.class
        );
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/product/rddesign/feasibilityanalysis/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductFeaAna001Dto> selectPageData(Map<String, Object> pageParam){
        return selectForPage(pageParam);
    }


}
