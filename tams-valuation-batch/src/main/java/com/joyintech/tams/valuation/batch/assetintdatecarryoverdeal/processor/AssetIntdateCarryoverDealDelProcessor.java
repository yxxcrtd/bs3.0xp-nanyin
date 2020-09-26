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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverInfo;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产理论结息日结转- processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverDealDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverDealDelProcessor extends BaseProcessor<NeedOperateEntity, AssetIntdateCarryoverResult> {

    /**
     * 传入参数
     */
    private Map<String, Object> param = new HashMap<String, Object>();

    /**
     * 设置 param
     * 
     * @param param param
     */
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    @Override
    public AssetIntdateCarryoverResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        AssetIntdateCarryoverInfo acctEntity = (AssetIntdateCarryoverInfo)items;

        // 将返回值塞入参数中
        param.put(GlobalConstant.READER_RESULT, acctEntity);


        // 调用service方法进行逻辑逻辑
        AssetIntdateCarryoverResult result = new AssetIntdateCarryoverResult();
        
        result.setProdId("资产理论结息日结转交易类STEP1");
        return result;
    }
}
