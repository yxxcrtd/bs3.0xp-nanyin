package com.joyintech.service.product.productelement.reportregister.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productelement.reportregister.dao.ProductReportRegisterMapper;
import com.joyintech.service.product.productelement.reportregister.dto.ProductReportRegisterDto;

@Service
public class ProductReportRegisterService extends BaseService<ProductReportRegisterDto>{

	@Autowired
	private ProductReportRegisterMapper productReportRegisterMapper;
	
	public Page<ProductReportRegisterDto> selectForPage(Map<String, Object> map,int page,int length){
		return queryForPage(page,length,() -> {
			productReportRegisterMapper.selectForPage(map);
		});
	}
	
	/**
	 * 产品代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param){
    	return productReportRegisterMapper.queryProdIdInfo(param);
    }
    
    /**
	 * 行内标识码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
    public List<SelectOptionDto> queryFinprodInsId(@RequestBody Map<String, Object> param){
    	return productReportRegisterMapper.queryFinprodInsId(param);
    }
    
    /**
     * 根据产品ID查询
     * @param repFinprodId
     * @return
     */
    public ProductReportRegisterDto selectByPrimaryKey(String repFinprodId) {
    	return productReportRegisterMapper.selectByPrimaryKey(repFinprodId);
    }
    
    /**
     * 新建插入
     * @param productReportRegisterDto
     */
    public void insertSelective(ProductReportRegisterDto productReportRegisterDto) {
    	productReportRegisterMapper.insertSelective(productReportRegisterDto);
    	return;
    }
    
    /**
     * 编辑
     * @param productReportRegisterDto
     */
    public void updateByPrimaryKeySelective(ProductReportRegisterDto productReportRegisterDto) {
    	productReportRegisterMapper.updateByPrimaryKeySelective(productReportRegisterDto);
    	return;
    }
    
    /**
     * 根据产品ID删除
     * @param repFinprodId
     */
    public void deleteByPrimaryKey(String repFinprodId) {
    	productReportRegisterMapper.deleteByPrimaryKey(repFinprodId);
    }
    
    /**
	 * 根据产品ID查询是否关联产品
	 * @param repFinprodId
	 * @return
	 */
	public int selsectByFinprod(String repFinprodId) {
		return productReportRegisterMapper.selsectByFinprod(repFinprodId);
	}
	
	/**
	 * 根据行内识别码查询是否有重复数据
	 * @param repFinprodId
	 * @return
	 */
	public int selsectByFinprodInsId(String repFinprodInsId) {
		return productReportRegisterMapper.selsectByFinprodInsId(repFinprodInsId);
	}
}
