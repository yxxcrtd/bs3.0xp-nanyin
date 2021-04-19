
package com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ValBondMarketInfo001Mapper
 * @since 1.0
 */
@Repository(value="serviceValBondMarketInfo001Mapper")
public interface ValBondMarketInfo001Mapper {
    
    /**
     * 
     * 债券每日行情查询
     * @param ValBondMarketInfo001SearchDto
     * @return ValBondMarketInfo001Dto
     * @see    
     * @since 1.0
     */
    List<ValBondMarketInfo001Dto> queryPageList(Map<String, Object> params);
    
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
    
    /**
     * 查询新增数据产品代码是否存在
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprodIdAndFinprodName( Map<String,Object> param) ;

}
