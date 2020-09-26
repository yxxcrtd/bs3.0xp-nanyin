/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryoverdeal.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverInfo;

/**
 * 资产理论结息日结转 - 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see AssetIntdateCarryoverDealDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDealDelReader extends BaseReader<AssetIntdateCarryoverInfo> {

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<AssetIntdateCarryoverInfo> createList() {

        // 获取reader返回值
        AssetIntdateCarryoverInfo assetIntdateCarryoverInfo = new AssetIntdateCarryoverInfo();
        List<AssetIntdateCarryoverInfo> resultList = new ArrayList<AssetIntdateCarryoverInfo>();
        resultList.add(assetIntdateCarryoverInfo);
        return resultList;
    }

}
