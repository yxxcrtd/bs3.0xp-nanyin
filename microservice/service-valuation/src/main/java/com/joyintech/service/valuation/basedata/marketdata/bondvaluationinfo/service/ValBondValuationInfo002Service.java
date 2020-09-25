/*
 * 文件名：ValBondValuationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dao.ValBondValuationInfo002Mapper;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo002Dto;


/**
 * 债券估值行情
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo002Service
 * @since 1.0
 */
@Service(value="serviceValBondValuationInfo002Service")
public class ValBondValuationInfo002Service {

    /**
     * 债券估值行情mapper
     */
    @Autowired
    private ValBondValuationInfo002Mapper valBondValuationInfo002Mapper;

    /**
     * 根据债券代码查询债券估值行情
     * 
     * @param secId 债券代码
     * @return ValBondValuationInfo002Dto
     * @see
     * @since 1.0
     */
    public List<ValBondValuationInfo002Dto> searchList(String secId) {
        return valBondValuationInfo002Mapper.searchList(secId);
    }

}
