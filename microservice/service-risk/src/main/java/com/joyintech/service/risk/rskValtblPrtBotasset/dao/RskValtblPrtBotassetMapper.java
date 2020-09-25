/*
 * 文件名：RskValtblPrtBotassetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskValtblPrtBotasset.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetMapper
 * @since 1.0
 */
@Repository
public interface RskValtblPrtBotassetMapper {

    List<RskValtblPrtBotassetMapDto> queryForPage(Map<String, Object> pageParam);
    
    List<RskValtblPrtBotassetMapDto>  selectBotAssetMap(@Param("ptlId") String ptlId);
    
    RskValtblPrtBotassetMapDto selectAssetById(@Param("finprodId") String finprodId);
    
    RskValtblPrtBotassetMapDto selectWindBondAssetById(@Param("finprodId") String finprodId);
    
    Integer getCountForValUnmapped(Map<String,Object> paramMap);
    
    Integer getCountForValDiffmapped(Map<String,Object> paramMap);
    
    void applyMapForValUnmapped(Map<String,Object> paramMap);
    
    void applyMapForValDiffmapped(Map<String,Object> paramMap);

    void cancleValmapped(@Param("ptlId") String ptlId,@Param("subCode") String subCode);

    void deletePrtBotassetMap(Map<String,Object> paramMap);
}
