/*
 * 文件名：FinBondTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.bond.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.bond.dto.FinBondTargetAssetDto;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinBondTargetAssetMapper {

    List<FinBondTargetAssetDto> queryForPage(Map<String, Object> pageParam);
    
    FinBondTargetAssetDto getBasicInfo(@Param("finprodId") String finprodId
                                       // 债券新建新增快捷录入功能 add by renping 20191010 start
                                       , @Param("finprodType2") String finprodType2);
                                       // 债券新建新增快捷录入功能 add by renping 20191010 end
    
    int selectCountByfinprodMarketId(@Param("finprodMarketId") String finprodMarketId);

    /**
     * 获取主体评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinBondTargetAssetDto> getZTPJ(Map<String, Object> param);

    /**
     * 获取债项评级机构数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinBondTargetAssetDto> getZXPJ(Map<String, Object> param);
    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     * 
     * @author wangguiqi
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);

	int isHqTrade(@Param("finprodId") String finprodId);
    int selectCountByfinprodId(@Param("finprodId") String finprodId);

    int selectCountByOFinprodId(@Param("oFinprodId") String oFinprodId);

}
