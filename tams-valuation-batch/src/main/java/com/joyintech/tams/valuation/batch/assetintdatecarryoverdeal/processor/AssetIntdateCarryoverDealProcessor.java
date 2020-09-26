/*
 * 文件名：AssetIntdateCarryoverProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chenghzong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryoverdeal.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderResult;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverResult;
import com.joyintech.tams.valuation.batch.assetintdatecarryoverdeal.service.AssetIntdateCarryoverDealProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产理论结息日结转- processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverDealProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDealProcessor extends BaseProcessor<NeedOperateEntity, AssetIntdateCarryoverResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private AssetIntdateCarryoverDealProcessorService assetIntdateCarryoverDealProcessorService;

    @Override
    public AssetIntdateCarryoverResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        AssetIntdateCarryoverReaderResult acctEntity = (AssetIntdateCarryoverReaderResult)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);


        // 调用service方法进行逻辑逻辑
        AssetIntdateCarryoverResult result = assetIntdateCarryoverDealProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new AssetIntdateCarryoverResult();
        }
        
        result.setProdId(acctEntity.getAssetId() + acctEntity.getFinprodId());
        
        return result;
    }
}
