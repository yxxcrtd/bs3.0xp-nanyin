/*
 * 文件名：MoneyTaAutoCalDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 27, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.processor;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.MoneyTaAutoCalResult;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.TradeInofoGenerateResult;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 27, 2019
 * @see MoneyTaAutoCalDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalDelProcessor extends BaseProcessor<NeedOperateEntity, TradeInofoGenerateResult> {

    @Override
    public TradeInofoGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        MoneyTaAutoCalResult acctEntity = (MoneyTaAutoCalResult)items;

        Map<String, Object> param = new HashMap<String, Object>();

        // 将返回值塞入参数中
        param.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        TradeInofoGenerateResult result = new TradeInofoGenerateResult();

        result.setProdId("货币型产品自动结转处理STEP1");
        return result;
    }

}
