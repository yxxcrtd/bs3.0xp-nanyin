package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial003Dto;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 产品模拟试算service
 * 
 * @author jiaobo
 * @version 1.0 2019年11月5日
 * @see
 * @since 1.0
 */
@Service
public class ProductSimulationTrial005Service extends CommonExportService<ProductSimulationTrial003Dto> {

    /**
     * 查询三级分类多选框
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType3(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductHistorical/queryMulType3",
                map, SelectOptionDto.class);
        return list;
    }

    /**
     * 查询四级分类多选框
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType4(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductHistorical/queryMulType4",
                map, SelectOptionDto.class);
        return list;
    }

    /**
     * 查询五级分类多选框
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType5(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductHistorical/queryMulType5",
                map, SelectOptionDto.class);
        return list;
    }

}
