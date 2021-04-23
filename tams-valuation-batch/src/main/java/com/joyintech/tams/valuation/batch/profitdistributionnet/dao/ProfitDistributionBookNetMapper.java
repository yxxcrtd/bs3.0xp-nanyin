/*
 * 文件名：ProfitDistributionBookNetMapper.java
 * 版权：Copyright 2020-2011 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1
 * 修改人：WUJIE
 * 修改时间：2020年4月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1
 */
package com.joyintech.tams.valuation.batch.profitdistributionnet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.profitdistributionnet.dto.ProfitDistributionNetReaderDto;

/**
 * 净值型产品利润分配批处理 - 数据库数据获取
 * 
 * @author WUJIE
 * @version 1.0 2020年4月1日
 * @see ProfitDistributionBookNetMapper
 * @since 1.0
 */
@Repository
public interface ProfitDistributionBookNetMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param taskId
     * @param profitType
     * @return
     * @see
     * @since 1.0
     */
    List<ProfitDistributionNetReaderDto> searchProfitDistribution(Map<String, Object> params);

}
