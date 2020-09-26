/*
 * 文件名：BookDetailSubjectCurrentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyReadResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 更新账务明细（当前表）科目余额批处理 - 数据库数据获取
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectCurrentMapper
 * @since 1.0
 */
@Repository
public interface BookDetailSubjectCurrentMapper {

    /**
     * 获取账务明细当前表数据 <一句话功能简述> <功能详细描述>
     * 
     * @return List<ValBokBookdetailBatch>
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailBatch> getValBokBookdetailBatch(Map<String, Object> params);

    /**
     * 更新账务明细（当前表）
     * 
     * @param vo ValBokBookdetailBatch
     * @return int
     * @see
     * @since 1.0
     */
    int updateValBokBookDetailCurrent(ValBokBookdetailBatch vo);

    /**
     * 批量更新
     * 
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    int updateValBokBookDetailCurrentBatch(List<ValBokBookdetailBatch> list);

    /**
     * 获取当日存在的现金类账务明细代码
     * 
     * @param vo ValBokBookdetailBatch
     * @return ValBokBookdetailBatch
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailBatch> searchTodayTrade(Map<String, Object> params);

    /**
     * 插入明细当前表
     * 
     * @param vo ValBokBookdetailBatch
     * @return ValBokBookdetailBatch
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailBatch> selectValBokBookdetailCurrent(List<ValBokBookdetailBatch> bookList);

    /**
     * 根据条件删除 账务明细（当前表） 数据
     *
     * @param taskId 任务代码
     * @param bookType 凭证类型
     * @param bookSetId 账套代码
     * @return 删除 账务明细（当前表） 数据
     * @see
     * @since 1.0
     */
    int updateBookDetailCurrent(@Param("booksetIds") List<String> booksetIds,
                                @Param("bookType") String bookType);
}
