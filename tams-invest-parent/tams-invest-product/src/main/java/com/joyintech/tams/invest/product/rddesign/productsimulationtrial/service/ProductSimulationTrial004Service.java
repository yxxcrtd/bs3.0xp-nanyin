package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial003Dto;

/**
 * 产品模拟交易service
 * 
 * @author jiaobo
 * @version 1.0 2019年11月5日
 * @see ProductSimulationTrial003Dto
 * @since 1.0
 */
@Service
public class ProductSimulationTrial004Service extends CommonExportService<ProductSimulationTrial003Dto>{
	/**
	 * 历史记录页面查询
	 *
	 * @param map
	 * @return
	 */
	public List<ProductSimulationTrial003Dto> findPage(Map<String, Object> map) {
	    List<ProductSimulationTrial003Dto> pageDto = restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductHistorical/findPage", map,
				ProductSimulationTrial003Dto.class);
		return pageDto;
	}
	
	/**
	 * 浮动利率时获取浮动利率下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
    public Page<SelectOptionDto> getIndexIdSource(Map<String, Object> param) {
        Page<SelectOptionDto> page = restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/getIndexIdSource", param,
            SelectOptionDto.class);
        return page;
    }
}
