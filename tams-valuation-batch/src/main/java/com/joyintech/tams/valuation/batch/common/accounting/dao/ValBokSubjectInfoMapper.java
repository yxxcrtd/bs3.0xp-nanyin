package com.joyintech.tams.valuation.batch.common.accounting.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.batch.common.accounting.entity.ValBokBalanceDto;


/**
 * 基础mapper
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月30日
 * @see ValBokSubjectInfoMapper
 * @since 1.0
 */
@Repository
public interface ValBokSubjectInfoMapper {
	/**
     * 通过账套代码查询科目明细
     * 
     * @param booksetId 账套代码
     * @return ValBokSubject 
     * @see
     * @since 1.0
     */
    List<BokSubject> findValBokSubjectInfoByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 通过日期实现余额查询
     * 
     * @param earlyDate 科目余额日期
     * @param booksetId 
     * @return ValBokBalance
     * @see
     * @since 1.0
     */
    List<ValBokBalanceDto> findEarlyBalace(@Param("earlyDate") Date earlyDate,@Param("booksetId") String booksetId);

    /**
     * 损益期科目余额查询
     * 
     * @param booksetId 账套代码
     * @param earlyDate 入账日
     * @return ValBokBalance
     * @see
     * @since 1.0
     */
    List<BokBalance> findBookDetailAllEarly(@Param("booksetId") String booksetId, @Param("earlyDate") Date earlyDate);

    /**
     * 损益类本期科目余额查询
     * 
     * @param booksetId 账套代码
     * @param earlyDate 入账日
     * @return ValBokBalance
     * @see
     * @since 1.0
     */
    List<BokBalance> findBookDetailAllNow(@Param("booksetId") String booksetId, @Param("earlyDate") Date earlyDate, @Param("endDate") Date endDate);

}