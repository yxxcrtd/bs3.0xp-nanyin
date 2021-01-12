package com.joyintech.tams.valuation.batch.bookclawback.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.batch.tafloatfeeaccounts.entity.TaFloadFeeAccountsDto;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokClawbackInfo;
import com.joyintech.tams.common.base.entity.BokClawbackInfoDetail;
import com.joyintech.tams.valuation.batch.bookclawback.dto.BookClawbackDto;


@Repository
public interface BookClawbackMapper {

	List<BookClawbackDto> getDealInfo(Map<String,Object> maps);
	
	void insertBokClawbackInfo(List<BokClawbackInfo> list);
	
	void insertBokClawbackInfoDetail(List<BokClawbackInfoDetail> list);

	/**
	 * 获取存款类资产计提账务待处理交易数据
	 * @param param
	 * @return
	 */
	public List<TaFloadFeeAccountsDto> selectBokBookdetailCurrent(Map<String, Object> param);
}
