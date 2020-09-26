package com.joyintech.tams.valuation.common.interf.howmany.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.BokSubject;


@Repository
public interface HowManyCommonMapper {
	
	/**
	 * 删除入账信息表
	 * @param tradeId 交易编号
	 * @return
	 */
	int delBokDealAccountingByTradeId(@Param("tradeId") String tradeId);
	
	/**
	 * 删除入账明细信息表
	 * @param tradeId 交易编号
	 * @return
	 */
	int delBokDealAccountingDetailByTradeId(@Param("tradeId") String tradeId);
	
	/**
	 * 账套信息查询
	 * @param busiId 业务明细代码
	 * @return
	 */
	List<BokBookset> queryBokBooksetByBusiId(@Param("busiId") String busiId);
	
	/**
	 * 账套日期表
	 * @param booksetId
	 * @return
	 */
	BokBooksetDate queryBokBooksetDateByBooksetId(@Param("booksetId") String booksetId);
	
	
	/**
	 * 账套科目
	 * @param booksetId
	 * @param subjectNo
	 * @return
	 */
	BokSubject querybokSubjectByKey(@Param("booksetId") String booksetId, @Param("subjectNo") String subjectNo);

}
