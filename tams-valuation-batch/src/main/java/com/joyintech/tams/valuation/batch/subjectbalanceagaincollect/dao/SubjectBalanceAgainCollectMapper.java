/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BokBalanceAgainCollectLogDto;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BooksetTodoDto;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.SubjectBalanceBatch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;

/**
 * 科目余额重汇Mapper
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectBalanceAgainCollectMapper
 * @since 1.0
 */
@Repository
public interface SubjectBalanceAgainCollectMapper {

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
     * @param booksetId 账套代码
     * @return 科目余额结果集
     * @see
     * @since 1.0
     */
    List<SubjectBalance> queryYesterdayBalance(@Param("queryDate") Date queryDate,
                                               @Param("booksetId") String booksetId);

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
	List<SubjectBalance> queryVoucherToday(@Param("bookDate") Date bookDate, @Param("booksetId") String booksetId,
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
    /**
     * 批量插入
     * @param list 数据
     * @return 插入条数
     * 
     */
    int insertBatch(List<SubjectBalanceBatch> list);
    /**
     * 插入科目余额重汇信息记录
     * @param dto
     * @return
     */
    int insertLog(BokBalanceAgainCollectLogDto dto);

    int updateLog(BokBalanceAgainCollectLogDto dto);

    int deleteLog(Map<String, Object> map);
}
