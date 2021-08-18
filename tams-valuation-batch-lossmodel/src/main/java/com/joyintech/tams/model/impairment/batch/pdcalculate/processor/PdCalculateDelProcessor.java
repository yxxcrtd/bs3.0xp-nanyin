/*
 * 文件名：AssetIntdateCarryoverProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chenghzong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.impairment.batch.pdcalculate.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetImpairmentPdCalculateDelResultDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * PD计算批处理- processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PdCalculateDelProcessor extends BaseProcessor<NeedOperateEntity, AssetImpairmentPdCalculateDelResultDto> {

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
    public AssetImpairmentPdCalculateDelResultDto process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        AssetImpairmentPdCalculateDelResultDto result = new AssetImpairmentPdCalculateDelResultDto();
        result.setProdId("PD计算批处理STEP1");
        return result;
    }
}
