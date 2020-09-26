/*
 * 文件名：PrdIndexCalculateIntoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.processor;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateProcessorDelResult;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateReaderDelResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 信息披露-产品指标计算(数据删除)-processor
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdIndexCalculateDelProcessor extends BaseProcessor<NeedOperateEntity, PrdIndexCalculateProcessorDelResult> {

	@Override
	public PrdIndexCalculateProcessorDelResult process(NeedOperateEntity items) throws Exception {
		// 获取reader方法的返回值
		PrdIndexCalculateProcessorDelResult result = ((PrdIndexCalculateReaderDelResult) items).toResult();
		result.setProdId(DateUtil.format((Date) this.getParams().get(BookConstant.BOOK_DATE)));
		return result;
	}

}
