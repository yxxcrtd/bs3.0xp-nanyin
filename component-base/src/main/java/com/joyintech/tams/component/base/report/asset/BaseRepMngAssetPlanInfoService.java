/*
 * 文件名：RepAssetMngProInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.component.base.report.dto.BaseRepMngAssetPlanInfoDto;

/**
 * 资产管理产品中债报送信息组件
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年8月28日
 * @see RepAssetMngProInfo
 * @since 1.0
 */
public interface BaseRepMngAssetPlanInfoService {
    
    /**
     * 
     * 修改资产负债报送公共字段信息
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public boolean update(BaseRepMngAssetPlanInfoDto dto);
    /**
     * 
     * 将信息插入资产管理计划报送信息（REP_ASSET_MNG_PLAN）表
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public boolean insert(BaseRepMngAssetPlanInfoDto dto);
    /**
     * 
     * 根据金融产品代码，删除资产管理计划报送信息（REP_ASSET_MNG_PLAN）表数据
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public boolean delete(BaseRepMngAssetPlanInfoDto dto);
    /**
     * 
     * 根据金融产品代码，查询资产管理计划报送信息（REP_ASSET_MNG_PLAN）表数据
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<RepAssetMngPlan> search(BaseRepMngAssetPlanInfoDto dto);
}
