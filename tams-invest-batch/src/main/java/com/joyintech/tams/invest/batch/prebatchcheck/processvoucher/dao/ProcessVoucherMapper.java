package com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ProcessVoucherMapper {
	
	List<SysPrebatchResultDetail> selectProcessVoucherList(Map<String,Object> map);
}
