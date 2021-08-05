package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdProfit;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdProfitMapper {
    int insert(PrdProfit record);

    int insertSelective(PrdProfit record);
}