/*
 * 文件名：VatDealInfoRecRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.runner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.InterfBaseReader;
import com.joyintech.tams.valuation.interf.core.InterfBaseService;
import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;
import com.joyintech.tams.valuation.interf.core.InterfBuilder;
import com.joyintech.tams.valuation.interf.core.exception.InterfException;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.processor.VatDealInfoRecProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.writer.VatDealInfoRecWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 增值税交易信息接口Runner
 * 
 * @author wangqidong
 * @version 1.0 2018年8月16日
 * @see VatDealInfoRecRunner
 * @since 1.0
 */
@Service("vatDealInfoRecRunner")
@Scope("prototype")
public class VatDealInfoRecRunner extends InterfBaseService {
    /**
     * step1Reader
     */
    @Autowired
    private AssetMangBaseInfoReader step1Reader;
    /**
     * step1Processor
     */
    @Autowired
    private VatDealInfoRecProcessor step1Processor;
    /**
     * step1Writer
     */
    @Autowired
    private VatDealInfoRecWriter step1Writer;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * interfBuilder
     */
    @Autowired
    private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, 
            InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void execute()
        throws Exception {
        logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
        // setp1的参数设置-接收定时任务
        Map<String, Object> params1 = getParams();
        params1.put("executeInfo", this.getInterfExecuteInfo());
        step1Reader.setParams(params1);
        step1Processor.setParam(params1);
        step1Writer.setParam(params1);

        try {
            interfBuilder.init(this).batch("step1", (InterfBaseReader)step1Reader,
                (InterfBaseProcessor)step1Processor, (InterfBaseWriter)step1Writer).builder(
                    "vatDealInfoRecRunner").run();
        }
        catch (InterfException e) {
            logger.error("", e);
            logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
            throw e;
        }
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
    }
    
}
