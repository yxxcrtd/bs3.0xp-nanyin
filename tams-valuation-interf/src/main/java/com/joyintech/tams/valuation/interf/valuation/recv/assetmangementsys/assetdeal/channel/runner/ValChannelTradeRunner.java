/*
 * 文件名：ValChannelTradeRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.runner;

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
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.processor.ValChannelTradeProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.writer.ValChannelTradeWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 通道产品-交易数据接口 Runner
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelTradeRunner
 * @since 1.0
 */
@Service("valChannelTradeRunner")
@Scope("prototype")
public class ValChannelTradeRunner extends InterfBaseService {

    @Autowired
    private AssetMangBaseInfoReader step1Reader;
    
    @Autowired
    private ValChannelTradeProcessor step1Processor;
    
    @Autowired 
    private ValChannelTradeWriter step1Writer;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * interfBuilder
     */
    @Autowired
    private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void execute()
        throws Exception {
        logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
        //参数列表增加"executeInfo"参数
        this.getParams().put("executeInfo", this.getInterfExecuteInfo());

        try {
            interfBuilder.init(this).batch("step1", (InterfBaseReader)step1Reader,
                (InterfBaseProcessor)step1Processor, (InterfBaseWriter)step1Writer).builder(
                    "cProdDealInfoRecRunner").run();
        }
        catch (InterfException e) {
            logger.error("", e);
            logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
            throw e;
        }
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
    }
    
}
