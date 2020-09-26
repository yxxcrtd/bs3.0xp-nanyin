/*
 * 文件名：ValIntRateQuote001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.dto.ResultDto;
import com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.dto.ValFinProductValuation001SearchDto;

/**
 * 利率行情
 * 
 * @author HuangJian
 * @version 1.0 2018年7月18日
 * @see ValFinProductValuation001Mapper
 * @since 1.0
 */
@Repository
public interface ValFinProductValuation001Mapper {

    /**
     * 加载表格
     * 
     * @param map 参数
     * @return List<ResultDto>
     * @see
     * @since 1.0
     */
    List<ResultDto> tableLoad(ValFinProductValuation001SearchDto map);
}
