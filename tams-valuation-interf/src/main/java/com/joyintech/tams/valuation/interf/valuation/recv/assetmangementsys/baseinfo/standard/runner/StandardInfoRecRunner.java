package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.runner;

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
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.processor.StandardInfoRecProcessor;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.writer.StandardInfoRecWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.common.reader.AssetMangBaseInfoReader;

/**
 * 标的信息 数据接口
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月18日
 * @see StandardInfoRecRunner
 * @since 1.0
 */
@Service("standardInfoRecRunner")
@Scope("prototype")
public class StandardInfoRecRunner extends InterfBaseService {

	@Autowired
	private AssetMangBaseInfoReader step1Reader;

	@Autowired
	private StandardInfoRecProcessor step1Processor;

	@Autowired
	private StandardInfoRecWriter step1Writer;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * interfBuilder
	 */
	@Autowired
	private InterfBuilder<InterfBaseReader<InterfNeedOperateEntity>, InterfBaseProcessor<InterfNeedOperateEntity, BaseEntity>, InterfBaseWriter<BaseEntity>> interfBuilder;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute() throws Exception {
		logger.info(getInterfExecuteInfo().getTaskName() + "开始！");
		// 参数列表增加"executeInfo"参数
		this.getParams().put("executeInfo", this.getInterfExecuteInfo());

		try {
			interfBuilder.init(this).batch("step1", (InterfBaseReader) step1Reader,
					(InterfBaseProcessor) step1Processor, (InterfBaseWriter) step1Writer)
					.builder("StandardInfoRecRunner").run();
		} catch (InterfException e) {
			logger.error("", e);
			logger.error(getInterfExecuteInfo().getTaskName() + "异常结束！");
			throw e;
		}
		logger.info(getInterfExecuteInfo().getTaskName() + "结束！");
	}
}
