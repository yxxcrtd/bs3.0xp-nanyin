package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.BokBooksetRe;


public interface ValBokBooksetReMapper {
	/**
	 * 批量插入母子关系表
	 * */
	int  insertBatch(List<BokBooksetRe> list);
	/**
	 * 批量删除母子关系表
	 * */
	int  deleteBatch(List<BokBooksetRe> list);
}
