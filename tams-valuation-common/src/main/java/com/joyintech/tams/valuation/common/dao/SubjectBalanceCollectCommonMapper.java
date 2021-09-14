/*
 * 文件名：SubjectBalanceCollectCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：gushi
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SubjectBalance;

/**
 * 科目余额汇总Mapper 共通
 * @author zhangwei
 * @version 1.0 2018年11月10日
 * @see SubjectBalanceCollectCommonMapper
 * @since 1.0
 */
@Repository
public interface SubjectBalanceCollectCommonMapper {

    /**
     * 账套科目查询
     * 
     * @param booksetId 账套代码
     * @return 账套科目结果集
     * @see
     * @since 1.0
     */
    List<BokSubject> querySubject(@Param("booksetId") String booksetId);
    
    /**
     * 昨日科目余额查询
     * 
     * @param queryDate 查询日期
     * @param booksetId 账套代码
     * @return 科目余额结果集
     * @see
     * @since 1.0
     */
    List<SubjectBalance> queryYesterdayBalance(@Param("queryDate") Date queryDate,
                                               @Param("booksetId") String booksetId);
    
    /**
     * 昨日科目余额删除
     * 
     * @param queryDate 查询日期
     * @param booksetId 账套代码
     * @return 科目余额结果集
     * @see
     * @since 1.0
     */
    int delYesterdayBalance(@Param("queryDate") Date queryDate,
                                               @Param("booksetId") String booksetId);
    
    
    /**
     * 查询今日已入库的科目余额
     * 
     * @param queryDate 查询日期
     * @param booksetId 账套代码
     * @return 科目余额结果集
     * @author WUJIE
     * @see
     * @since 1.0
     */
    List<SubjectBalance> queryTdayBalanceAready(@Param("queryDate") Date queryDate,
                                               @Param("booksetId") String booksetId);
}
