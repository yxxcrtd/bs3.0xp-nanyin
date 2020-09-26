/*
 * 文件名：ValAssetBook001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetbook001Dto;

/**
 * 〈一句话功能简述〉 套帐管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook001Mapper
 * @since 1.0
 */
@Repository
public interface ValAssetBook001Mapper {
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId();
    /**
     * 检索账套代码
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetIdByBooksetType();
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId2(@Param("booksetType") String booksetType, @Param("param") String param);

    /**
     * 检索账套代码
     * @param map
     * @return
     */
    List<SelectOptionDto> findBooksetId(Map<String, Object> map);
    
    /**
     * 查询数据 <一句话功能简述> <功能详细描述>
     * 
     * @param booksetType 账套类别
     * @param booksetName 账套名称
     * @return List<ValAssetbook001SearchDto>
     * @see
     * @since 1.0
     */
    List<ValAssetbook001Dto> getBookSet02(@Param("booksetType") String booksetType,
                                          @Param("booksetName") String booksetName);

    /**
     * 获取账套名称
     * 
     * @return List<ValAssetbook001Dto>
     * @see
     * @since 1.0
     */
    List<ValAssetbook001Dto> getBookSetName();
    
    /**
     * 根据账套id，删除科目余额表数据
     * @param booksetId
     * @return
     */
    int deleteByBooksetId(@Param("booksetId")String booksetId);



    /**
     * 产品组
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> selectBatchGroup(Map<String, Object> pageParam);

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getBooksetIdForSelect(Map<String, Object> pageParam);

    /**
     * 获取产品组下的账套
     * @param params
     * @return
     */
    List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params);
}
