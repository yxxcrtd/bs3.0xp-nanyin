/*
 * 文件名：BussRepFinProductInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.prod;

import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;

/**
 * 产品中债报备基本信息
 * 
 * @author zhangliang
 * @version 1.0 2018年9月22日
 * @see BussRepFinProductInfoService
 * @since 1.0
 */
public interface BussRepFinProductInfoService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepFinProductInfoDto";
    
    /**
     * 根据金融产品代码查询中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return 中债报送信息
     * @see
     * @since 1.0
     */
    BussRepFinProductInfoDto select(String finprodId);
    
    /**
     * 根据金融产品代码删除中债报送信息
     * 
     * @param finprodId 金融产品代码
     * @return DB影响行数
     * @see
     * @since 1.0
     */
    void delete(String finprodId);
    
    /**
     * 插入中债报送信息
     * 
     * @param dto 操作对象
     * @see
     * @since 1.0
     */
    void insert(BussRepFinProductInfoDto dto);
    
    /**
     * 修改中债报送信息
     * 
     * @param dto 操作对象
     * @see
     * @since 1.0
     */
    void update(BussRepFinProductInfoDto dto);
    
    /**
     * 计算[产品期限(天)]以及[期限品种]设定值
     *
     * @param dto 画面上的参数dto
     * @return 结果dto
     * @see
     * @since 1.0
     */
    String calcProductTermInfo(BussRepFinProductInfoDto dto);
    /**
     * 根据行内标识码查询中债报送信息
     * 
     * @param finprodId 行内标识码
     * @return 中债报送信息
     * @see
     * @since 1.0
     */
    BussRepFinProductInfoDto selectRepFinprodId(String repFinprodInsId);
}
