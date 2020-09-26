/*
 * 文件名：SubjectBalanceCollectReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.repassetindex.reader;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.repassetindex.dao.RepAssetIndexTaskMapper;
import com.joyintech.tams.valuation.batch.repassetindex.dto.BokBookset;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产指标计算 reader
 * 
 * @author dongxiangyang
 * @version 1.0 2018年5月28日
 * @see RepAssetIndexReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepAssetIndexReader extends BaseReader<BokBookset> {

	/**
	 * mapper
	 */
	@Autowired
	private RepAssetIndexTaskMapper mapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BokBookset> createList() {
		// 获取task传递过来的参数
		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());

		@SuppressWarnings("unchecked")
		List<String> booksetIdList = (List<String>) thisParams.get(GlobalConstant.BOOKSETS);// 账套信息
		Date bookSetDate = (Date) thisParams.get(BookConstant.BOOKSET_DATE);// 所选账套日

		Map<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("bookSetList", booksetIdList);// 所选账套list
		sqlmap.put("bookSetDate", bookSetDate);
		List<BokBookset> resultList = mapper.selectBokBookset(sqlmap);

		// 循环塞入[入账日] = 参数.账套日
		if (resultList != null && !resultList.isEmpty()) {
			for (BokBookset dto : resultList) {
				dto.setBookDate(bookSetDate);
			}
		}

		return resultList;
	}
	
}
