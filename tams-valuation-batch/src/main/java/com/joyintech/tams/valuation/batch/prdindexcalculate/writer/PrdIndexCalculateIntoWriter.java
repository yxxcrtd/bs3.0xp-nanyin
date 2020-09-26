/*
 * 文件名：PrdIndexCalculateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.RepProdIndexMapper;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateProcessorIntoResult;

/**
 * 信息披露-产品指标计算(数据插入)-writer
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateIntoWriter
 * @since 1.0
 */
@Service
public class PrdIndexCalculateIntoWriter extends BaseWriter<NeedToWriteEntity> {

	@Autowired
	private RepProdIndexMapper repProdIndexMapper;

	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值
		List<PrdIndexCalculateProcessorIntoResult> resultList = (List<PrdIndexCalculateProcessorIntoResult>) items;

		// 循环返回值list
		for (PrdIndexCalculateProcessorIntoResult result : resultList) {
			// 获取产品指标数据list
			List<RepProdIndex> repProdIndexList = result.getRepProdIndexList();

			// 循环进行数据插入
			if (repProdIndexList != null && !repProdIndexList.isEmpty()) {
				for (RepProdIndex dto : repProdIndexList) {
					repProdIndexMapper.insert(dto);
				}
			}
		}
	}

}
