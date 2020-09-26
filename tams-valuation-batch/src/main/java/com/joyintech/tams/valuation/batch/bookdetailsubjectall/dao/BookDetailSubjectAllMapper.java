/*
 * 文件名：BookDetailSubjectAllMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.bookdetailsubjectall.dto.BookDetailSubjectAllReadResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 更新账务明细（历史表）科目余额批处理 - 数据库数据获取
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectAllMapper
 * @since 1.0
 */
@Repository
public interface BookDetailSubjectAllMapper {

    /**
     * 获取账务明细历史表数据
     * 
     * @return List<ValBokBookdetailBatch>
     * @see
     * @since 1.0
     */
    List<BookDetailSubjectAllReadResult> getValBokBookdetailListForSort();

    /**
     * 获取账务明细历史表数据 <一句话功能简述> <功能详细描述>
     * 
     * @return List<ValBokBookdetailBatch>
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailBatch> getValBokBookdetailBatch(Map<String, Object> params);

    /**
     * 更新账务明细（历史表）
     * 
     * @param vo ValBokBookdetailBatch
     * @return int
     * @see
     * @since 1.0
     */
    int updateValBokBookDetailAll(ValBokBookdetailBatch vo);

    /**
     * 批量更新
     * 
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    int updateValBokBookDetailAllBatch(@Param("list") List<ValBokBookdetailBatch> list);

}
