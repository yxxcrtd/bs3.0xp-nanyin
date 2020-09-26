package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import com.joyintech.tams.common.base.entity.FinTradeProductAdd;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FinTradeProductAddBatchMapper {
    
    int insertBatch(List<FinTradeProductAdd> list);
    
    int deleteBatch(List<FinTradeProductAdd> list);
    
}