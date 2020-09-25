/*
 * 文件名：ValRiftMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.batch.valrift.dto.PtlSecValutionDto;
import com.joyintech.tams.invest.batch.valrift.dto.ValtblAssetSubDto;

/**
 *  估值穿透
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see ValRiftMapper
 * @since 1.0
 */
@Repository
public interface ValRiftMapper {

    List<PtlSecValutionDto>  selectPtlIdForTask(Map<String,Object> paramMap);
    
    List<PtlSecValutionDto>  selectPtlValAsset(Map<String,Object> paramMap);
    
    List<ValtblAssetSubDto> selectValtblAssetSub(Map<String,Object> paramMap);
    
    BigDecimal selectValCapital(Map<String,Object> paramMap);
    
    void deleteValRift(Map<String,Object> paramMap);
    
}
