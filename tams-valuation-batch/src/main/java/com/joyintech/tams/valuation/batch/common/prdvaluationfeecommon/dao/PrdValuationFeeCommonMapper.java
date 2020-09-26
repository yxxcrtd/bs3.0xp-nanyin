/*
 * 文件名：prdValuationFeeBeforeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto.BokstatbokpositionReaderDto;

/**
 * (产品估值计算) - 数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年8月13日
 * @see PrdValuationFeeCommonMapper
 * @since 1.0
 */
@Repository
public interface PrdValuationFeeCommonMapper {

    /**
     * 查询指定帐套的核算主体和TA产品
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<BokstatbokpositionReaderDto> getAcctAndTaInfo(Map<String, Object> param);
}
