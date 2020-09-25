/*
 * 文件名：ValBondValuationInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo002Dto;


/**
 * ValBondValuationInfo002Mapper
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo002Mapper
 * @since 1.0
 */
@Repository(value="serviceValBondValuationInfo002Mapper")
public interface ValBondValuationInfo002Mapper {
    
    /**
     * 
     * 根据债券代码查询债券估值行情
     * @param secId 债券代码
     * @return ValBondValuationInfo002Dto
     * @see
     * @since 1.0
     */
    List<ValBondValuationInfo002Dto> searchList(@Param("secId") String secId);

}
