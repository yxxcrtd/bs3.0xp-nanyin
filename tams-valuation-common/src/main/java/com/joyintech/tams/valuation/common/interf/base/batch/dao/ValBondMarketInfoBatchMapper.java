package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstBondMarketInfo;

@Repository
public interface ValBondMarketInfoBatchMapper {
    int insertBatch(List<MstBondMarketInfo> list);
    void deleteData(@Param("list") List<MstBondMarketInfo> list);
}