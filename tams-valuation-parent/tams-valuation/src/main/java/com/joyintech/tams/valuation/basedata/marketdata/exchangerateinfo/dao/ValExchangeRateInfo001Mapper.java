/*
 * 文件名：ValExchangeRateInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.dto.ValExchangeRateInfo001Dto;

/**
 * 数据库操作接口
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年8月8日
 * @see ValExchangeRateInfo001Mapper
 * @since 1.0
 */
@Repository
public interface ValExchangeRateInfo001Mapper {

    /**
     * 
     * 查询结果，加载表格 
     * 
     * @param valExchangeRateInfo001Dto ValExchangeRateInfo001Dto
     * @return ValExchangeRateInfo001Dto
     */
    List<ValExchangeRateInfo001Dto> tableLoad(Map<String, Object> map);
}
