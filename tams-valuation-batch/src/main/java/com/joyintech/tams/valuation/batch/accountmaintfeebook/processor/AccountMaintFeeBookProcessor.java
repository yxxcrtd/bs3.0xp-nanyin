package com.joyintech.tams.valuation.batch.accountmaintfeebook.processor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.dto.AccountMaintFeeBookResult;
import com.joyintech.tams.valuation.batch.accountmaintfeebook.service.AccountMaintFeeBookProcessorService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 账户维护费每日计提 processor
 * @author hpp
 *
 */
@Service
@Scope("prototype")
public class AccountMaintFeeBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>{

	@Autowired
	private AccountMaintFeeBookProcessorService accountMaintFeeBookProcessorService;
	
	@Override
	public BookGenerateResult process(NeedOperateEntity item) throws Exception {

        AccountMaintFeeBookResult acctEntity = (AccountMaintFeeBookResult)item; // 获取 reader 方法的返回值

        Map<String, Object> params = Maps.newHashMap();
        params.putAll(this.getParams());
        params.put(GlobalConstant.READER_RESULT, acctEntity); // 将reader返回值塞入参数中

        BookGenerateResult result = accountMaintFeeBookProcessorService.execute(params); // 业务逻辑处理

        if (result == null) {// 当为null时候，给予初始化
            result = new BookGenerateResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBooksetId()); // 标识

        return result;
	}

}
