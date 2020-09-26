/*
 * 文件名：ValBondValuationInfo003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo003Dto;

/**
 * 债券估值行情-新建、删除、修改 mapper
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月6日
 * @see ValBondValuationInfo003Mapper
 * @since 1.0
 */
public interface ValBondValuationInfo003Mapper {
    
    /**
     * 
     * 根据债券代码查询债券估值行情
     * @param secId 债券代码
     * @return ValBondValuationInfo002Dto
     * @see
     * @since 1.0
     */
    List<ValBondValuationInfo002Dto> searchList(@Param("secId") String secId);

    /**
     * 获取标的类金融产品表信息
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondValuationInfo003Dto> selectFinprodIdList(Map<String, Object> map);

    /**
     * 获取标的类金融产品简称
     * 
     * @param secId
     * @return
     * @see
     * @since 1.0
     */
    ValBondValuationInfo003Dto selectFinprodAbbrByFinprodId(String secId);

    /**
     * 判断表中是否已存在相同数据
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    ValBondValuationInfo003Dto checkExistMstBondValuationInfoByKey(ValBondValuationInfo003Dto dto);

    /**
     * 更新
     * 
     * @param entity
     * @see
     * @since 1.0
     */
    void updateByBfUpdPrimaryKeySelective(ValBondValuationInfo003Dto entity);

}
