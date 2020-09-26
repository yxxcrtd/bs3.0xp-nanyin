package com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;


import com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.dto.ValFundMarketInfo001dto;

/**
 * 
 * 数据库操作接口
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ValFundMarketInfo001mapper
 * @since 1.0
 */
@Repository
public interface ValFundMarketInfo001mapper {


	/**
	 * 查询结果，加载表格 
	 * @param dto ValFundMarketInfo001dto
	 * @return ValFundMarketInfo001dto
	 */
    List<ValFundMarketInfo001dto> tableLoad(ValFundMarketInfo001dto dto);
	
}
