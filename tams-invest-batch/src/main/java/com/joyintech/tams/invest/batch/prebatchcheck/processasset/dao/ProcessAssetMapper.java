package com.joyintech.tams.invest.batch.prebatchcheck.processasset.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ProcessAssetMapper {
	
	List<SysPrebatchResultDetail> selectProcessAssetList(Map<String,Object> map);
}
