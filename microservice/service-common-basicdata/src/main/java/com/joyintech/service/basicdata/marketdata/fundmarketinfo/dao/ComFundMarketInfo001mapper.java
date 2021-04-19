package com.joyintech.service.basicdata.marketdata.fundmarketinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import org.springframework.stereotype.Repository;

/**
 * 
 * 数据库操作接口
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ComFundMarketInfo001mapper
 * @since 1.0
 */
@Repository(value="serviceComFundMarketInfo001mapper")
public interface ComFundMarketInfo001mapper {


	/**
	 * 查询结果，加载表格 
	 * @param params
	 * @return ValFundMarketInfo001dto
	 */
    List<ComFundMarketInfo001dto> tableLoad(Map<String, Object> params);
    
    /**
     * 查询新增数据主键是否重复
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据产品代码是否存在
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprod( Map<String,Object> param) ;
	
}
