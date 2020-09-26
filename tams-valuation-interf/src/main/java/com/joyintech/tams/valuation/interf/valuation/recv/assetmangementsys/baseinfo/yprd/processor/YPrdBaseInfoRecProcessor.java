/*
 * 文件名：YPrdBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.dto.YPrdBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.service.YPrdBaseInfoRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.yprd.service.YPrdBaseInfoRecjxService;

/**
 * 预期型产品接口处理Processor
 * 
 * @author dongjun
 * @version 1.0 2018年10月8日
 * @see YPrdBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, YPrdBaseInfoProcessorResult> {

    @Autowired
    private YPrdBaseInfoRecService yPrdBaseInfoRecService;
    @Autowired
    private YPrdBaseInfoRecjxService yPrdBaseInfoRecjxService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public YPrdBaseInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult) item;

        // 调用预期型产品接口处理Service
        YPrdBaseInfoProcessorResult result = yPrdBaseInfoRecService.execute(acctEntity);
        //几许传数
        yPrdBaseInfoRecjxService.prodInfoJx(result);
        return result;
    }

}
