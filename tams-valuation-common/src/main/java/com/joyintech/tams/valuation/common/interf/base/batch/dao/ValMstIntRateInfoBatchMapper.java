package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;

@Repository
public interface ValMstIntRateInfoBatchMapper {
	int insertBatch(List<MstIntRateInfo> list);
    int deleteData(@Param("list") List<MstIntRateInfo> list);
}