/*
 * 文件名：BookGenerateResult.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 记账凭证生成结果
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see AssetIntdateCarryoverResult
 * @since 1.0
 */
public class AssetIntdateCarryoverResult extends NeedToWriteEntity {

    /**
     * 创建交易
     */
    private List<AssetIntdateCarryoverDeal> assetIntdateCarryoverDealList = new ArrayList<AssetIntdateCarryoverDeal>();

    
    /**
     * 获取 assetIntdateCarryoverDealList
     * @return assetIntdateCarryoverDealList.
     */
    public List<AssetIntdateCarryoverDeal> getAssetIntdateCarryoverDealList() {
        return assetIntdateCarryoverDealList;
    }

    
    /**
     * 设置 assetIntdateCarryoverDealList
     * @param assetIntdateCarryoverDealList assetIntdateCarryoverDealList
     */
    public void setAssetIntdateCarryoverDealList(List<AssetIntdateCarryoverDeal> assetIntdateCarryoverDealList) {
        this.assetIntdateCarryoverDealList = assetIntdateCarryoverDealList;
    }
    
    

}
