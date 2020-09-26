/*
 * 文件名：FeeInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dto.FeeInfoDto;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 *  产品费用Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月20日
 * @see FeeInfoMapper
 * @since 1.0
 */
@Repository
public interface FeeInfoMapper {
    /**
     * 查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<FeeInfoDto> queryFeeData(SendReaderEntity item);
    /**
     * 查询现金流代码方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    FinCashInfo queryCashId(FeeInfoDto dto);
    /**
     * 查询查询费率,计息基础方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    FeeInfoDto queryFeeRate(Map<String, Object> paramer);
    
    /**
     * 查询计费基数方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    FeeInfoDto queryFeeCalcBase(Map<String, Object> map);
    
}
