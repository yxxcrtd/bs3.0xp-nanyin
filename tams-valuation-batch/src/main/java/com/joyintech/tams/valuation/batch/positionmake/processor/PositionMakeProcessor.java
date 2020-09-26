package com.joyintech.tams.valuation.batch.positionmake.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.positionmake.dto.PositionMakeReaderResult;
import com.joyintech.tams.valuation.batch.positionmake.dto.PositionMakeWriterResult;

/**
 * 头寸生成批处理 - processor
 * 
 * @author Administrator
 *
 */
@Service
@Scope("prototype")
public class PositionMakeProcessor extends BaseProcessor<NeedOperateEntity, PositionMakeWriterResult> {

	@Override
	public PositionMakeWriterResult process(NeedOperateEntity items) throws Exception {

		// 获取reader方法的返回值
		PositionMakeReaderResult acctEntity = (PositionMakeReaderResult) items;

		// process返回值
		PositionMakeWriterResult result = new PositionMakeWriterResult();
		result.setPtlSecValutionList(acctEntity.getPtlSecValutionList());// 组合资产估值
		result.setBokPositionPrdfeeList(acctEntity.getBokPositionPrdfeeList());// 产品费用头寸
		result.setBokPositionPrdList(acctEntity.getBokPositionPrdList());// 产品头寸
		result.setPtlFeePositionList(acctEntity.getPtlFeePositionList());// 组合费用头寸
		result.setPtlPortfolioValuationList(acctEntity.getPtlPortfolioValuationList());// 组合估值头寸
		//查询组合资产应收款头寸LIST 【PTL_SEC_RECEIVABLES】 add by yanya on 20191225
		result.setPtlSecReceivablesList(acctEntity.getPtlSecReceivablesList());// 组合资产应收款头寸

		// 需要插入
		result.setProdId("头寸生成批处理processor");

		return result;
	}

}
