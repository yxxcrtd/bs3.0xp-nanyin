/*
 * 文件名：BookClawbackWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookclawback.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.bookclawback.dao.BookClawbackMapper;
import com.joyintech.tams.valuation.batch.bookclawback.dto.BookClawbackResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.framework.util.StringUtil;

/**
 * 
 * 回补Writer
 * 
 * @author dongjun
 * @version 1.0 2019年2月21日
 * @see BookClawbackWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookClawbackWriter extends BaseWriter<NeedToWriteEntity> {

	/**
	 * 记账凭证生成类
	 */
	@Autowired
	private BookAccountGenLogic bookAccountGenLogic;

	/***
	 * 回补Mapper
	 */
	@Autowired
	private BookClawbackMapper bookClawbackMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings({"unchecked"})
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		
		List<BookClawbackResult> resultList = (List<BookClawbackResult>) items;

		if(StringUtil.isEmptyList(resultList)){
		    return;
		}
		for (BookClawbackResult bookGenerateResult : resultList) {
			// 插入记账凭证主表信息 和 插入记账凭证明细表信息
			bookAccountGenLogic.insertKeepAccount(bookGenerateResult);

			if (!StringUtil.isEmptyList(bookGenerateResult.getBokClawbackInfoList())) {
				bookClawbackMapper.insertBokClawbackInfo(bookGenerateResult.getBokClawbackInfoList());
			}

			if (!StringUtil.isEmptyList(bookGenerateResult.getBokClawbackInfoDetailList())) {
				bookClawbackMapper.insertBokClawbackInfoDetail(bookGenerateResult.getBokClawbackInfoDetailList());
			}

		}

	}

}
