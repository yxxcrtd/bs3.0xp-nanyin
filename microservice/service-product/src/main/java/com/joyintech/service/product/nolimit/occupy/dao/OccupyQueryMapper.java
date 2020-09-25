package com.joyintech.service.product.nolimit.occupy.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.occupy.dto.ProdSeriesCurEndAmtDto;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
@Repository
public interface OccupyQueryMapper {
	
	/**
	 * 计划资产总量
	 * @param queryDay
	 * @return
	 */
	BigDecimal getTargetScale(Map<String, Object> param);
	
	/**
	 * 产品系列资产还本到期金额
	 * @param param
	 * @return
	 */
	List<ProdSeriesCurEndAmtDto> getProdSeriesCurEndAmt(Map<String, Object> param);
	
	/**
	 * 产品设计系列
	 * @param param
	 * @return
	 */
	List<SelectOptionDto> queryPrdDesign(Map<String, Object> param);
}
