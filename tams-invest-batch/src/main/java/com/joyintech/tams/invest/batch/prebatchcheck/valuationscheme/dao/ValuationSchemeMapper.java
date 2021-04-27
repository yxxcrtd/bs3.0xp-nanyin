package com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ValuationSchemeMapper {

	List<SysPrebatchResultDetail> selectValuationSchemeList(Map<String,Object> map);
}
