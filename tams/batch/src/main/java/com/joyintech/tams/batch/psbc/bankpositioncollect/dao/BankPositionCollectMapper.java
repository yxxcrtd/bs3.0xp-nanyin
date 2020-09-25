/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.bankpositioncollect.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.batch.psbc.bankcalbook.dto.BokBankPositionDto;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.BooksetTodoDto;

/**
 * 科目余额汇总Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see SubjectBalanceCollectMapper
 * @since 1.0
 */
@Repository
public interface BankPositionCollectMapper {

    /**
     * 根据任务代码和入账日期删除对应的科目余额信息
     * 
     * @param bookDate
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    int deleteBalanceByKey(Map<String, Object> param);

    /**
     * 查询所有待处理的账套代码
     * 
     * @param params taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
    List<BooksetTodoDto> selectBooksetTodo(Map<String, Object> param);

    /**
     * 昨日科目余额查询
     *  
     * @param queryDate 查询日期
     * @return 科目余额结果集
     * @see
     * @since 1.0
     */
    List<BokBankPositionDto> queryYesterdayBankPosition(@Param("queryDate") Date queryDate);

    /**
     * 今日凭证查询
     * 
     * @param bookDate 入账日期
     * @param bottomLevel 底层科目级别
     * @param booksetId 账套代码
     * @return 科目余额结果集
     * @see
     * @since 1.0
     */
	List<BokBankPositionDto> queryVoucherToday(@Param("bookDate") Date bookDate, @Param("booksetId") String booksetId,
			@Param("bottomLevel") Long bottomLevel);

    /**
     * 账套科目查询
     * 
     * @param booksetId 账套代码
     * @return 账套科目结果集
     * @see
     * @since 1.0
     */
    List<BokSubject> querySubject(@Param("booksetId") String booksetId);

}
