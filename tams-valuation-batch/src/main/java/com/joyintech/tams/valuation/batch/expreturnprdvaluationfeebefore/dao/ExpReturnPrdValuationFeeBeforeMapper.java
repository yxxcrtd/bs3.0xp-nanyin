/*
 * 文件名：ProBenefitPrdValuationFeeBeforeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.dto.ExpReturnPrdValuationFeeBeforeReaderDto;

/**
 * (预期收益型产品估值计算) - 数据获取
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月28日
 * @see ExpReturnPrdValuationFeeBeforeMapper
 * @since 1.0
 */
@Repository
public interface ExpReturnPrdValuationFeeBeforeMapper {

    /**
     * 查询指定帐套的核算主体和TA产品
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ExpReturnPrdValuationFeeBeforeReaderDto> getAcctSubjectAndTaInfo(Map<String, Object> param);

}
