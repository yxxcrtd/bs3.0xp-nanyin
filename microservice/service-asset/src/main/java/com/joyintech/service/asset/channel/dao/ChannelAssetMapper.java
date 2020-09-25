/*
 * 文件名：ChannelAssetQueryMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.channel.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.channel.dto.ChannelAssetDto;

/**
 * 通道资产查询
 *  
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see ChannelAssetQueryMapper
 * @since 1.0
 */
@Repository
public interface ChannelAssetMapper {

    /**
     * 通道查询page
     * <功能详细描述>
     * @param map 
     * @return 通道查询结果
     * @see
     * @since 1.0
     */
    List<ChannelAssetDto> selectForPage(Map<String, Object> map);

    /**
     * 获取通道代码
     * <功能详细描述>
     * @param param 查询条件
     * @return 市场代码 金融产品代码
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSecAcctId(Map<String, Object> param);
    
    /**
     * 获取通道名称
     * <功能详细描述>
     * @param param 查询条件
     * @return 金融产品名称  金融产品代码
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSecAcctName(Map<String, Object> param);
    
    /**
     * 判断通道是否重复
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int isRepeated(Map<String, Object> param);
    
    /**
     *  修改查看
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    ChannelAssetDto getBasicInfo(Map<String,Object> param);

    /**
     * 通道资产查询page
     * <功能详细描述>
     * @param map 
     * @return 通道资产查询结果
     * @return
     * @see
     * @since 1.0
     */
    List<ChannelAssetDto> selectChannelAssetForPage(Map<String, Object> pageParam);

    /**
     * 判断通道是否存在交易
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int isTransInAsset(Map<String, Object> finprodId);

    void deleteStlFinProdAcctByFinprodId(String finprodId);

    /**
     * 资金账户已经有通道挂的话，不允许再挂
     * @param map
     * @return
     */
    int isReBusiAcctNo(Map<String, Object> map);
}
