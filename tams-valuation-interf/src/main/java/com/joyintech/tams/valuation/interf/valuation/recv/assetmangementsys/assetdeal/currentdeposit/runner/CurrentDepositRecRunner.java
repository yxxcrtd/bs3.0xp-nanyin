/*
 * 文件名：CurrenDepositRecRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.runner;



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
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.processor.CurrentDepositRecProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.writer.CurrentDepositRecWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 活期存款利息-交易数据接口 runner
 * 
 * @author chendijun
 * @version 1.0 2018年8月21日
 * @see CurrentDepositRecRunner
 * @since 1.0
 */
@Service("currentDepositRecRunner")
@Scope("prototype")
public class CurrentDepositRecRunner extends InterfBaseService {

    @Autowired
    private AssetMangBaseInfoReader step1Reader;

    @Autowired
    private CurrentDepositRecProcessor step1Processor;

    @Autowired
    private CurrentDepositRecWriter step1Writer;

    @Autowired
    private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void execute()
        throws Exception {
        // TODO Auto-generated method stub
        logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
        // setp1的参数设置
        this.getParams().put("executeInfo", this.getInterfExecuteInfo());
        try {
            interfBuilder.init(this).batch("step1", (InterfBaseReader)step1Reader,
                (InterfBaseProcessor)step1Processor, (InterfBaseWriter)step1Writer).builder(
                    "currentDepositRecRunner").run();
        }
        catch (Exception e) {
            // TODO: handle exception
            logger.error("", e);
            logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
            throw e;
        }
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
    }

}
