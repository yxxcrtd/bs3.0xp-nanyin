package com.joyintech.tams.valuation.batch.bookclawback.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokClawbackInfo;
import com.joyintech.tams.common.base.entity.BokClawbackInfoDetail;
import com.joyintech.tams.valuation.batch.bookclawback.dto.BookClawbackDto;


@Repository
public interface BookClawbackMapper {

	List<BookClawbackDto> getDealInfo(Map<String,Object> maps);
	
	void insertBokClawbackInfo(List<BokClawbackInfo> list);
	
	void insertBokClawbackInfoDetail(List<BokClawbackInfoDetail> list);
}
