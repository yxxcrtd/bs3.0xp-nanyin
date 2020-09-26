/*
 * 文件名：ValBondValuationInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dao;

import java.util.List;

import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo001SearchDto;

/**
 * ValBondValuationInfo001Mapper
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Mapper
 * @since 1.0
 */
public interface ValBondValuationInfo001Mapper {
    
    /**
     * 
     * 债券估值行情查询
     * @param dto ValBondValuationInfo001SearchDto
     * @return ValBondValuationInfo001Dto
     * @see
     * @since 1.0
     */
    List<ValBondValuationInfo001Dto> queryPageList(ValBondValuationInfo001SearchDto dto);

    /**
     * 获取购买了指定债券的账套的账套日期
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    ValBondValuationInfo001Dto queryBooksetDate(ValBondValuationInfo001Dto dto);

}
