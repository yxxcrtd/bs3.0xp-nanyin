/*
 * 文件名：BookDetailCompareIntoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.ValBokBookdetailAllExt;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.ValBokBookdetailCurrentExt;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 账务明细比较合并批处理 - 数据库数据获取
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BookDetailCompareIntoMapper
 * @since 1.0
 */
@Repository
public interface BookDetailCompareIntoMapper {
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param bookDateNow
     * @return 
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailCurrentExt> queryValBokBookdetailCurrentDetail(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param date
     * @param bookDateNow
     * @return
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailAllExt> queryValBokBookdetailAllDetail(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param date
     * @param bookDateNow
     * @see
     * @since 1.0
     */
    int updateDate(@Param("list")List<String> seqno);

    /**
     *
     * @param params
     * @return
     */
    List<String> getUpdateSeqNo(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param model
     * @see
     * @since 1.0
     */
    void insertComparebookDetail(ValBokBookdetailBatch model);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param date
     * @param bookDateNow
     * @see
     * @since 1.0
     */
    void updateSeqno(List<ValBokBookdetailBatch> list);
    
    /**
     * 需要冲销的账务明细(历史表）的反向流水号
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<ValBokBookdetailBatch> selectSeqnoToUpdate(Map<String, Object> params);

}
