/*
 * 文件名：AutoPeriodCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.common.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import org.springframework.stereotype.Repository;

/**
 * 自动存续期共通Mapper
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see AutoPeriodCommonMapper
 * @since 1.0
 */
@Repository
public interface AutoPeriodCommonMapper {
    int deleteWflTrdFinProductDealTotalData(Map<String, Object> paramMap);

    int deleteWflStlPayOrdersData(Map<String, Object> paramMap);

    int deleteWflPaTrdFinDealTotalData(Map<String, Object> paramMap);

    int deleteWflTrdProductDeal(Map<String, Object> paramMap);

    List<PtlSecPosition> findAllProvided(Map<String, Object> params);
}
