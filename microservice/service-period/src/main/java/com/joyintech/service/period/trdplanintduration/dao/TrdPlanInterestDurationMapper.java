/*
 * 文件名：FinProductModule002Mapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：何阳阳 修改时间：2018年8月31日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdplanintduration.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;

/**
 * 债券存续期管理
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see TrdPlanInterestDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdPlanInterestDurationMapper {

    List<TrdPlanInterestDurationDto> findPage(Map<String, Object> param);

    TrdPlanInterestDurationDto findEffectiveData(Map<String, Object> param);

    List<TrdProductDeal> findPrdDeal(String busiCode);

    List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

    /*查询是否是还本不带息资产*/
    int getisPayInt(String findProdId);

    /*查询区间实际未付还本不带息金额*/
    BigDecimal getRepaymentUnpayInterest(Map<String, Object> params);

    String getBranch(Map<String, Object> params);
    
    /**
     * 撤销时根据汇总表的主键查询授信交易编号
     *findFinprodIdByBusiCode
     */
    List<String> findCeTradeIdByTotleTradeId(String totleTradeId);
}
