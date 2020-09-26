/*
 * 文件名：MoneyTaAutoCalProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.investratechange.processor;

import java.util.HashMap;
import java.util.Map;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.investratechange.dto.InvestRateChangeReaderDto;
import com.joyintech.tams.valuation.batch.investratechange.dto.InvestRateChangeResult;
import com.joyintech.tams.valuation.batch.investratechange.service.InvestRateChangeProcessorService;

/**
 * 利率互换
 * @author taoran
 * @version 20200114
 * @see InvestRateChangeProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvestRateChangeProcessor extends BaseProcessor<NeedOperateEntity, InvestRateChangeResult> {

	@Autowired
	private InvestRateChangeProcessorService investRateChangeProcessorService;

	@Override
	public InvestRateChangeResult process(NeedOperateEntity items) throws Exception {
		// 获取reader方法的返回值
		InvestRateChangeReaderDto acctEntity = (InvestRateChangeReaderDto) items;

		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());
		// 将返回值塞入参数中
		thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

		InvestRateChangeResult investRateChangeResult = investRateChangeProcessorService.execute(thisParams);

		// 当为null时候，给予初始化
		if (investRateChangeResult == null) {
			investRateChangeResult = new InvestRateChangeResult();
		}

		//investRateChangeResult.setProdId("利率互换估值模型处理STEP2: " + acctEntity.getBooksetId());

		return investRateChangeResult;
	}

}
