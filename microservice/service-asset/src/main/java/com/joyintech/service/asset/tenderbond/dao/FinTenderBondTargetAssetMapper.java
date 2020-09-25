/*
 * 文件名：FinTenderBondTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.tenderbond.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.asset.tenderbond.dto.FinTenderBondTargetAssetDto;

/**
 * 债券标的信息
 * @author wangpeng
 * @version 1.0 2019年7月4日
 * @see FinTenderBondTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinTenderBondTargetAssetMapper {

    List<FinTenderBondTargetAssetDto> queryForPage(Map<String, Object> pageParam);
    
    FinTenderBondTargetAssetDto getBasicInfo(@Param("finprodId") String finprodId);
    
    int selectCountByfinprodMarketId(@Param("finprodMarketId") String finprodMarketId);

    /**
     * 获取主体评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinTenderBondTargetAssetDto> getZTPJ(Map<String, Object> param);

    /**
     * 获取债项评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinTenderBondTargetAssetDto> getZXPJ(Map<String, Object> param);
}
