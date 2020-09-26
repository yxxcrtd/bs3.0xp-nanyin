package com.joyintech.tams.valuation.common.interf.val.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 同业借款基本信息初始化
 * 
 * @author yangjian
 * @version 1.0 2019年1月12日
 * @see BorrowBaseInfoInitMapper
 * @since 1.0
 */
@Repository
public interface BorrowBaseInfoInitMapper {

	/**
	 * 根据条件删除【交易类金融产品】数据
	 * @param finprodId
	 * @param finprodType
	 * @param branch
	 * @return
	 */
	int deleteFinTradeProduct(@Param("finprodId") String finprodId, @Param("finprodType") String finprodType, @Param("branch") Long branch);

}
