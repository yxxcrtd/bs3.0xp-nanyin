/*
 * 文件名：BaseCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokDetailId;

/**
 * 
 * 产品端共通验证
 * @author weipeng
 * @version 1.0 2018年5月10日
 * @see BookAccountCommonMapper
 * @since 1.0
 */
@Repository
public interface BookAccountCommonMapper {

    /**
     * 获取最大凭证序号
     *
     * @param vouchYear 年份
     * @param vouchMonth 月份
     * @return
     * @see
     * @since 1.0
     */
    String getMaxBookAccountNum(@Param("vouchYear") String vouchYear,
                                @Param("vouchMonth") String vouchMonth,
                                @Param("booksetId") String booksetId);
    
    /**
     * 
     * 更新历史业务凭证编号
     * 
     * @param vouchYear
     * @param vouchMonth
     * @see
     * @since 1.0
     */
    void updateHistory(@Param("vouchYear") String vouchYear,
                       @Param("vouchMonth") String vouchMonth);
    /**
     * 查询账务明细代码
     */
	List<BokDetailId> queryBokDetailIdList(Map<String, Object> map);

}
