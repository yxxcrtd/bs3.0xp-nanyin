/*
 * 文件名：FeeDrawBookDetailMapper.java
 * 修改人：chenlei1
 * 修改时间：2019年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.feedrawbookdetail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokFeeTableSet;

/**
 * 查询费用配置信息
 * 
 * @author chenlei1
 * @version 1.0 2019年11月22日
 * @see FeeDrawBookDetailMapper
 * @since 1.0
 */
@Repository
public interface FeeDrawBookDetailMapper {

    /**
     * 
     * 查询费用配置信息
     * @param ps 交易方向
     * @return List<FeeTableSet>
     * @see
     * @since 1.0
     */
    List<BokFeeTableSet> getFeeTableSetList(String ps);

}
