/*
 * 文件名：FundDealInfoRecRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.runner;

import java.util.HashMap;
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
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.processor.FundDealInfoRec1Processor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.processor.FundDealInfoRecProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.writer.FundDealInfoRecWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 资产端交易接口--基金类资产 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see FundDealInfoRecRunner
 * @since 1.0
 */
@Service("fundDealInfoRecRunner")
@Scope("prototype")
public class FundDealInfoRecRunner extends InterfBaseService {

    /**
     * step1Reader
     */
    @Autowired
    private AssetMangBaseInfoReader step1Reader;
    
    /**
     * step1Writer
     */
    @Autowired
    private FundDealInfoRecWriter step1Writer;
    
    /**
     * step1Processor
     */
    @Autowired
    private FundDealInfoRec1Processor step1Processor;
    
    
    @Autowired
    private AssetMangBaseInfoReader step2Reader;
    
    /**
     * step1Processor
     */
    @Autowired
    private FundDealInfoRecProcessor step2Processor;
    
    /**
     * step1Writer
     */
    @Autowired
    private FundDealInfoRecWriter step2Writer;

    /**
     * interfBuilder
     */
    @Autowired
    private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void execute() throws Exception {
        logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
        //参数列表增加"executeInfo"参数
        this.getParams().put("executeInfo", this.getInterfExecuteInfo());
        try {
            interfBuilder.init(this)
                    .batch("step1", (InterfBaseReader)step1Reader,(InterfBaseProcessor)step1Processor, (InterfBaseWriter)step1Writer)
            		.batch("step2", (InterfBaseReader)step2Reader,(InterfBaseProcessor)step2Processor, (InterfBaseWriter)step2Writer)
            .builder(
                    "fundDealInfoRecRunner").run();
            
        }
        catch (InterfException e) {
            logger.error("", e);
            logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
            throw e;
        }
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
    }

}
