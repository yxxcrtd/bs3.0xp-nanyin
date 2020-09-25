package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service;

import java.util.Map;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial001Dto;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 产品模拟试算service
 * 
 * @author jiaobo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrial001Dto
 * @since 1.0
 */
@Service
public class ProductSimulationTrial001Service extends CommonExportService<ProductSimulationTrial001Dto>{

	/**
	 * 产品设计系列编号下拉框
	 *
	 * @param param 过滤参数
	 * @return 下拉框列表
	 * @throws Exception Exception
	 */
	public Page<SelectOptionDto> queryDesignIdInfo(Map<String, Object> param)
			throws Exception {
		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/queryDesignIdInfo", param,
            SelectOptionDto.class);
	}

	/**
	 * 产品需求收集页面查询
	 *
	 * @param map
	 * @return
	 */
	public Page<ProductSimulationTrial001Dto> findPage(Map<String, Object> map) {
		Object prdDesignId = map.get("prdDesignId");
		if(prdDesignId!= null){
			map.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)prdDesignId));
		}
		Page<ProductSimulationTrial001Dto> pageDto = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/findPage", map,
				ProductSimulationTrial001Dto.class);
		return pageDto;
	}


	/**
	 * 导出
	 *
	 * @param exportParam 导出信息
	 * @see
	 * @since 1.0
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/rddesign/productsimulationtrial/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	@Override
	public Page selectPageData(Map<String, Object> selectParam) {
		Page<ProductSimulationTrial001Dto> result=findPage(selectParam);
		return result;
	}

}
