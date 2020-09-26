/*
 * 文件名：PrdIndexCalculateDeleteReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateReaderDelResult;

/**
 * 信息披露-产品指标计算(数据删除)-reader
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdIndexCalculateDelReader extends BaseReader<PrdIndexCalculateReaderDelResult> {

	@Override
	public List<PrdIndexCalculateReaderDelResult> createList() {
		// 获取当前任务代码
		String taskId = this.getBatchExecuteInfo().getTaskId();
		// 设置返回值
		List<PrdIndexCalculateReaderDelResult> listDto = new ArrayList<PrdIndexCalculateReaderDelResult>();
		listDto.add(new PrdIndexCalculateReaderDelResult(taskId));
		return listDto;
	}

}
