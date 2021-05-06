package com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ProcessSettleOrderMapper {
	List<SysPrebatchResultDetail> selectProcessSettleOrderList(Map<String,Object> map);
}
