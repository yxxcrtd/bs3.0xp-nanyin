/*
 * 文件名：ExpectIncomeSubProductFixedFeeCalBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.expectincomesubproductfixedfeecalbook.processor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.StayDrawSubprdEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.expectincomesubproductfixedfeecalbook.service.ExpReturnSubProductFixedFeeCalBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 预期收益型子产品 TA 固定费用计提入账 Processor
 * 
 * @author zhangwei
 * @version 1.0 2018年9月29日
 * @see ExpectIncomeSubProductFixedFeeCalBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExpReturnSubProductFixedFeeCalBookProcessor
		extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

	/**
	 * processor方法的 Service
	 */
	@Autowired
	private ExpReturnSubProductFixedFeeCalBookService expectService;

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BookGenerateResult process(NeedOperateEntity item) throws Exception {

		StayDrawSubprdEntity acctEntity = (StayDrawSubprdEntity) item; // 获取 reader 方法的返回值

		Map<String, Object> params = Maps.newHashMap();
		params.putAll(this.getParams());
		params.put(GlobalConstant.READER_RESULT, acctEntity); // 将reader返回值塞入参数中

		BookGenerateResult result = expectService.execute(params); // 业务逻辑处理

		if (result == null) {// 当为null时候，给予初始化
			result = new BookGenerateResult();
		}

		result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBooksetId()); // 标识

		return result;
	}

}
