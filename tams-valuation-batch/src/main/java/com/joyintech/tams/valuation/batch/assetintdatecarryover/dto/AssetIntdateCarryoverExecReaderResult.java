/*
 * 文件名：AssetIntdateCarryoverReaderResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * reader返回值
 * @author yangjian
 * @version 1.0 2018年7月17日
 * @see AssetIntdateCarryoverExecReaderResult
 * @since 1.0
 */
public class AssetIntdateCarryoverExecReaderResult extends NeedOperateEntity  {
    
    /**
     * 金融产品代码
     */
    private String finprodId;
   
    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 资产理论结息日结转行权明细信息List
     */
    List<AssetIntdateCarryoverExecReaderDetail> assetIntdateCarryoverExecReaderDetailList = new ArrayList<AssetIntdateCarryoverExecReaderDetail>();

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public List<AssetIntdateCarryoverExecReaderDetail> getAssetIntdateCarryoverExecReaderDetailList() {
        return assetIntdateCarryoverExecReaderDetailList;
    }

    public void setAssetIntdateCarryoverExecReaderDetailList(List<AssetIntdateCarryoverExecReaderDetail> assetIntdateCarryoverExecReaderDetailList) {
        this.assetIntdateCarryoverExecReaderDetailList = assetIntdateCarryoverExecReaderDetailList;
    }
}
