/*
 * 文件名：ProfitDistributionBookMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;
import com.joyintech.tams.common.base.entity.BokSubject;

/**
 * 利润分配批处理 - 数据库数据获取
 * 
 * @author chengzhong
 * @version 1.0 2018年5月24日
 * @see ValBokBookDetailDelMapper
 * @since 1.0
 */
@Repository
public interface ValBokBookDetailDelMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param taskId
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    List<String> searchBooksets(Map<String, Object> params);


}
