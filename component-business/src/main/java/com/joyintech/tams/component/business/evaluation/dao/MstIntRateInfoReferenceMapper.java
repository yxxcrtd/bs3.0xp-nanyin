/*
 * 文件名：EvalIndexAlgorithmMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.component.business.evaluation.dto.MstIntRateInfoDto;

/**
 * 2019年10月9日
 * yangxiaofeng
 * 基准日收益率列表
 */
@Repository
public interface MstIntRateInfoReferenceMapper {

    /**
     * 基准日收益率列表
     */
    List<MstIntRateInfoDto> getMstIntRateInfoDtoList(Map<String,Object> parMap);

    /**
     * 查询【最近一天非空利率】
     */
    BigDecimal nearistNotEmptyRateDayRate(Map<String,String> map);
}
