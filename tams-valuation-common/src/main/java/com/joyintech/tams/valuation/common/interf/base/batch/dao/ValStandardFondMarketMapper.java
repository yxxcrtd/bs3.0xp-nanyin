package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinProductValuationDto;

@Repository
public interface ValStandardFondMarketMapper {
	    int insertBatch(List<FinProductValuationDto> list);
	    void deleteData(@Param("list") List<FinProductValuationDto> list);
}
