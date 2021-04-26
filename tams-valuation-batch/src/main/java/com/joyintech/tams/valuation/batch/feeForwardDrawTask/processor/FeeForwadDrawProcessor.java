package com.joyintech.tams.valuation.batch.feeForwardDrawTask.processor;

import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto.FeeForwadDrawReaderResult;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.service.FeeForwadDrawProcessorService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 账户维护费每日计提 processor
 * @author hpp
 *
 */
@Service
@Scope("prototype")
public class FeeForwadDrawProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>{

	@Autowired
	private FeeForwadDrawProcessorService feeForwadDrawProcessorService;

	@Override
	public BookGenerateResult process(NeedOperateEntity item) throws Exception {

        FeeForwadDrawReaderResult acctEntity = (FeeForwadDrawReaderResult)item; // 获取 reader 方法的返回值

        Map<String, Object> params = Maps.newHashMap();
        params.putAll(this.getParams());
        params.put("BATCHINFO", this.getBatchExecuteInfo());
        params.put("BATCHTASK", this.getBatchTask());
        params.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        params.put("taskId", this.getBatchExecuteInfo().getTaskId());
        params.put(GlobalConstant.READER_RESULT, acctEntity); // 将reader返回值塞入参数中

        BookGenerateResult result = feeForwadDrawProcessorService.execute(params); // 业务逻辑处理

        if (result == null) {// 当为null时候，给予初始化
            result = new BookGenerateResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBooksetId()); // 标识

        return result;
	}

}
