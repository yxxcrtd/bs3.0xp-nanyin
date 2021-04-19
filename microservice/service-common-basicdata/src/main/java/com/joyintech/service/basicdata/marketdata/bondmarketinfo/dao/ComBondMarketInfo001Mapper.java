
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Mapper
 * @since 1.0
 */
@Repository(value="serviceComBondMarketInfo001Mapper")
public interface ComBondMarketInfo001Mapper {
    
    /**
     * 
     * 债券每日行情查询
     * @param params
     * @return ComBondMarketInfo001Dto
     * @see    
     * @since 1.0
     */
    List<ComBondMarketInfo001Dto> queryPageList(Map<String, Object> params);
    
    /**
     * 查询新增数据主键是否重复
     * @param param 产品list
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
    
    /**
     * 查询新增数据产品代码是否存在
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprodIdAndFinprodName( Map<String,Object> param) ;

}
