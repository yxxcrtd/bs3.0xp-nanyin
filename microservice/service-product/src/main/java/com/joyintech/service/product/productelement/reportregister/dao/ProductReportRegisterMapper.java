package com.joyintech.service.product.productelement.reportregister.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productelement.reportregister.dto.ProductReportRegisterDto;

@Repository
public interface ProductReportRegisterMapper {
	
	int deleteByPrimaryKey(String repFinprodId);

    int insert(ProductReportRegisterDto record);

    int insertSelective(ProductReportRegisterDto record);

    int insertBasicByBasicInfo(ProductReportRegisterDto record);

    ProductReportRegisterDto selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(ProductReportRegisterDto record);

    int updateByPrimaryKey(ProductReportRegisterDto record);
	/**
	 * 页面查询
	 * @param map
	 * @return
	 */
	List<ProductReportRegisterDto> selectForPage(Map<String , Object> map);
	
	/**
	 * 产品代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);
	
	/**
	 * 行内标识码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryFinprodInsId(Map<String, Object> map);
	
	/**
	 * 根据产品ID查询是否关联产品
	 * @param repFinprodId
	 * @return
	 */
	int selsectByFinprod(String repFinprodId);
	
	/**
	 * 根据行内识别码查询是否有重复数据
	 * @param repFinprodId
	 * @return
	 */
	public int selsectByFinprodInsId(String repFinprodInsId);
}
