/*
 * 文件名：MoneyProdValuationDataRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.runner;

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
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.processor.NetProdValuationDataProcessor;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.writer.NetProdValuationDataWriter;
import com.joyintech.tams.valuation.interf.valuation.send.common.reader.InterfSendCommonReader;
import com.joyintech.tams.valuation.interf.valuation.send.common.writer.InterfSendCommonWriter;

/**
 * 净值型产品估值数据Runner
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetValuationDataRunner
 * @since 1.0
 */
@Service("netProdValuationDataRunner")
@Scope("prototype")
public class NetProdValuationDataRunner extends InterfBaseService {

	
	@Autowired
	private InterfSendCommonReader step1Reader;
	
	@Autowired
    private NetProdValuationDataProcessor step1Processor;
	
	@Autowired
	private InterfSendCommonWriter step1Writer;
	
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
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
		// 参数列表增加"executeInfo"参数
		this.getParams().put("executeInfo", this.getInterfExecuteInfo());
		
		try {
            interfBuilder.init(this).batch("step1", (InterfBaseReader)step1Reader,
                (InterfBaseProcessor)step1Processor, (InterfBaseWriter)step1Writer).builder(
                    "netValuationDataRunner").run();
        }
        catch (InterfException e) {
            logger.error("", e);
            logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
            throw e;
        }
        logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
	}

}
