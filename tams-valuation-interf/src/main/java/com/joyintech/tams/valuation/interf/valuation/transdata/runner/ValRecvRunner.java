/*
 * 文件名：ValRecvRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.transdata.runner;

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
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.core.exception.InterfException;
import com.joyintech.tams.valuation.interf.valuation.transdata.processor.ValRecvProcessor;
import com.joyintech.tams.valuation.interf.valuation.transdata.reader.ValRecvReader;
import com.joyintech.tams.valuation.interf.valuation.transdata.writer.ValRecvWriter;

/**
 * 数据文件读取到本地操作 
 * 
 * @author 王波
 * @version 1.0 2018年6月22日
 * @see ValRecvRunner
 * @since 1.0
 */
@Service("valRecvRunner")
@Scope("prototype")
public class ValRecvRunner extends InterfBaseService {

    // Step0-数据文件读取到本地操作
    @Autowired
    private ValRecvReader step0Reader;

    @Autowired
    private ValRecvProcessor step0Processor;

    @Autowired
    private ValRecvWriter step0Writer;

    /**
     * interfBuilder
     */
    @Autowired
    private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void execute() throws InterfException {
        InterfExecuteInfo execInfo = getInterfExecuteInfo();
        logger.info(execInfo.getTaskName() + "开始！");
        this.getParams().put("execId", execInfo.getActionLog().getSeqno());
      //参数列表增加"executeInfo"参数
        this.getParams().put("executeInfo", this.getInterfExecuteInfo());
        interfBuilder.init(this).batch("step0", (InterfBaseReader)step0Reader,
                (InterfBaseProcessor)step0Processor, (InterfBaseWriter)step0Writer).builder(
                    "valRecvRunner").run();
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
    }
}
