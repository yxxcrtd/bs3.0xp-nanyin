/*
 * 文件名：CProdDealInfoRecRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.runner;

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
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.processor.CProdDealInfoRecProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.writer.CProdDealInfoRecWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 货币型产品交易接口 Runner
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2018年8月13日
 * @see CProdDealInfoRecRunner
 * @since 1.0
 */
@Service("cProdDealInfoRecRunner")
@Scope("prototype")
public class CProdDealInfoRecRunner extends InterfBaseService {
    
    @Autowired
    private AssetMangBaseInfoReader step1Reader;
    
    @Autowired
    private CProdDealInfoRecProcessor step1Processor;
    
    @Autowired 
    private CProdDealInfoRecWriter step1Writer;

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
