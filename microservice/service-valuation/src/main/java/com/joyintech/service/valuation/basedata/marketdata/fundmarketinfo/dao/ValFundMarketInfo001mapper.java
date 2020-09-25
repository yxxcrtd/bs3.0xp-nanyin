package com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.dto.ValFundMarketInfo001dto;

/**
 * 
 * 数据库操作接口
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ValFundMarketInfo001mapper
 * @since 1.0
 */
@Repository(value="serviceValFundMarketInfo001mapper")
public interface ValFundMarketInfo001mapper {


	/**
	 * 查询结果，加载表格 
	 * @param dto ValFundMarketInfo001dto
	 * @return ValFundMarketInfo001dto
	 */
    List<ValFundMarketInfo001dto> tableLoad(Map<String, Object> params);
    
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据产品代码是否存在
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprod( Map<String,Object> param) ;
	
}
