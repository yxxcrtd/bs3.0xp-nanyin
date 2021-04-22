/*
 * 文件名：AssetIntdateCarryoverMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetimpairmentprovision.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.dto.AssetImpairmentProvisionReaderResult;
/**
 * 资产减值计提批处理 - 数据库数据获取
 * 
 * @author chufengjie
 * @version 1.0 2020年10月13日
 * @see AssetImpairmentProvisionMapper
 * @since 1.0
 */
@Repository
public interface AssetImpairmentProvisionMapper {
/**
     * 减值资产明细查询 <功能详细描述>
     * 
     * @param branch
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    List<AssetImpairmentProvisionReaderResult> queryTrdAssetLossReader(Map<String, Object> param);
    /**
     * 减值资产明细查询 <功能详细描述>
     * 
     * @param branch
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    int updateBokStatDetPosiiton(BokStatLossPosiiton bokStatLossPosiiton);
    
}
