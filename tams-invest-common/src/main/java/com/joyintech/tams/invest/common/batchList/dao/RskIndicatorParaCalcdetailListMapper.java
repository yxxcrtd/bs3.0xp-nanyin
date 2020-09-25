package com.joyintech.tams.invest.common.batchList.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.invest.common.batchList.dao.BatchListMapper;

@Repository
public interface RskIndicatorParaCalcdetailListMapper extends BatchListMapper<RskIndicatorParaCalcdetail> {

	@Override
	void insertBatchList(List<RskIndicatorParaCalcdetail> infoList);
}
