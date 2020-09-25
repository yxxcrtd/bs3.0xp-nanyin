/*
 * 文件名：AutoPeriodCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.secover.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.autoperiod.common.dao.AutoPeriodCommonMapper;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;

/**
 * 自动存续期共通Mapper
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see AutoPeriodCommonMapper
 * @since 1.0
 */
@Repository
public interface AutoPeriodSecOverCommonMapper {
	/**
	 * 查询还本不带息利息明细
	 * @param paramMap
	 * @return
	 */
    List<TrdSecOverdueDurationDto> selectRepaymentUnpayInterest(Map<String, Object> paramMap);

    /**
     * 查询cashid
     * @param paramMap
     * @return
     */
    List<TrdSecOverdueDurationDto> selectCashId(Map<String, Object> paramMap);

    /**
     * 子组合应收款持仓明细
     * @param paramMap
     * @return
     */
    List<FinAssetIntInfo> selectNewInterestDetails(Map<String, Object> paramMap);

}
