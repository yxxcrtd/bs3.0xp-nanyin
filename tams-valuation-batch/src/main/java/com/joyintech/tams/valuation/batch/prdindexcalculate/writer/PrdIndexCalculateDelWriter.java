/*
 * 文件名：PrdIndexCalculateDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.writer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dao.PrdIndexCalculateMapper;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 信息披露-产品指标计算(数据删除)-writer
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateDelWriter
 * @since 1.0
 */
@Service
public class PrdIndexCalculateDelWriter extends BaseWriter<NeedToWriteEntity> {

	@Autowired
	private PrdIndexCalculateMapper prdIndexCalculateMapper;

	@Override
	public void write(List<? extends NeedToWriteEntity> arg0) throws Exception {
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
		prdIndexCalculateMapper.deleteTableDate(sqlmap);

	}

}
