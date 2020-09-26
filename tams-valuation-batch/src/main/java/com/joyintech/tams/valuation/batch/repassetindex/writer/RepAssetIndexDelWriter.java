/*
 * 文件名：SubjectBalanceCollectWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.repassetindex.writer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.repassetindex.dao.RepAssetIndexTaskMapper;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产指标计算step1Writer
 * 
 * @author dongxiangyang
 * @version 1.0 2018年12月21日
 * @see RepAssetIndexDelWriter
 * @since 1.0
 */
@Service
public class RepAssetIndexDelWriter extends BaseWriter<NeedToWriteEntity> {

	/**
	 * 资产指标mapper
	 */
	@Autowired
	private RepAssetIndexTaskMapper rpAssetIndexTaskMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {

		// 获取task传递过来的参数
		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());

		@SuppressWarnings("unchecked")
		List<String> booksetIdList = (List<String>) thisParams.get(GlobalConstant.BOOKSETS);// 账套信息
		Date bookSetDate = (Date) thisParams.get(BookConstant.BOOKSET_DATE);// 所选账套日

		// 根据参数删除数据
		Map<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("bookSetList", booksetIdList);// 所选账套list
		sqlmap.put("booksetdate", bookSetDate);// 账套日
		
		// 删除之前的记录
		rpAssetIndexTaskMapper.deleteRepAssetIndex(sqlmap);
	}

}
