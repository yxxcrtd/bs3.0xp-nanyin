package com.joyintech.tams.valuation.batch.accountmaintfeebook.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.accountmaintfeebook.dto.AccountMaintFeeBookDto;

import feign.Param;

/**
 * 账户维护费每日计提 Dao
 * @author hpp
 *
 */
@Repository
public interface AccountMaintFeeBookMapper {
	/**
	 * 查询指定帐套下的需要计提的核算主体和金融产品信息及对应的费用，过滤已发生支付交易
	 * @param params
	 * @return
	 */
	//List<AccountMaintFeeBookDto> getAccMaintFeeInfo(@Param("bookDate") Date bookDate);
	
	List<AccountMaintFeeBookDto> getAccMaintFeeInfo(Map<String, Object> param);
}
