/*
 * 文件名：ProfitDistributionBookMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;

/**
 * 利润分配批处理 - 数据库数据获取
 * 
 * @author chengzhong
 * @version 1.0 2018年5月24日
 * @see ProfitDistributionBookMapper
 * @since 1.0
 */
@Repository
public interface ProfitDistributionBookMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param taskId
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    List<ProfitDistributionReaderDto> searchProfitDistribution(Map<String, Object> params);

    /**
     * 利润分配-应付利润、应付利润科目查询
     * 
     * @param booksetId
     * @param baseSubjectNatureList
     * @return
     * @see
     * @since 1.0
     */
    List<BokSubject> searchProfitSubject(@Param("booksetId") String booksetId,
                                         @Param("baseSubjectNatureList") List<String> baseSubjectNatureList);

}
