package com.joyintech.tams.invest.product.productelement.reportregister.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.reportregister.dto.ProductReportRegister001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class ProductReportRegister001Service extends CommonExportService<ProductReportRegister001Dto> implements BaseProcessService<ProductReportRegister001Dto> {

	@Override
	public void doComplete(ProductReportRegister001Dto entity, OperationType type) {
		if(type == OperationType.DELETE) {
			restTemplate.getForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME
							+ "/productReportRegister/deleteByPrimaryKey?repFinprodId=" + entity.getRepFinprodId(),
					Integer.class);
		}
		
	}
	
	public Page<ProductReportRegister001Dto> selectForPage(Map<String, Object> map){
		if (StringUtil.isNotEmpty(map.get("repFinprodName"))) {
            map.put("repFinprodName", StringUtil.splitToList(null, map.get("repFinprodName").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("repFinprodInsId"))) {
            map.put("repFinprodInsId", StringUtil.splitToList(null, map.get("repFinprodInsId").toString()));
        }
		Page<ProductReportRegister001Dto> pageDto = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/productReportRegister/selectForPage", map,
				ProductReportRegister001Dto.class);
		return pageDto;
	}
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/productelement/reportregister/conf/export_conf.xml")
		.dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<ProductReportRegister001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}
	
	/**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/productReportRegister/queryProdIdInfo", map,
            SelectOptionDto.class);
    }
    
    /**
     * 行内标识码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryFinprodInsId(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/productReportRegister/queryFinprodInsId", map,
            SelectOptionDto.class);
    }
    
    /**
	 * 根据产品ID查询是否关联产品
	 * @param repFinprodId
	 * @return
	 */
	public int selsectByFinprod(String repFinprodId) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/productReportRegister/selsectByFinprod?repFinprodId=" + repFinprodId,
		Integer.class);
	}

}
