package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProductValuation;

@Repository
public interface ValFinProductValuationBatchMapper {
    int insertBatch(List<FinProductValuation> list);
    
    void deleteData(@Param("list") List<FinProductValuation> list);
}