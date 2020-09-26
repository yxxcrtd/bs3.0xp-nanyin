
package com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.dao;

import java.util.List;

import com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001SearchDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ValBondMarketInfo001Mapper
 * @since 1.0
 */
public interface ValBondMarketInfo001Mapper {
    
    /**
     * 
     * 债券每日行情查询
     * @param ValBondMarketInfo001SearchDto
     * @return ValBondMarketInfo001Dto
     * @see    
     * @since 1.0
     */
    List<ValBondMarketInfo001Dto> queryPageList(ValBondMarketInfo001SearchDto dto);

}
