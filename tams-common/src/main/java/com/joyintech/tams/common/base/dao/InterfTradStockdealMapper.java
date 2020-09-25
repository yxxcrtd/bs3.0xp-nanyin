package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfTradStockdeal;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfTradStockdealMapper {
    int deleteByPrimaryKey(String tradeid);

    int insert(InterfTradStockdeal record);

    int insertSelective(InterfTradStockdeal record);

    int insertBasicByBasicInfo(InterfTradStockdeal record);

    InterfTradStockdeal selectByPrimaryKey(String tradeid);

    int updateByPrimaryKeySelective(InterfTradStockdeal record);

    int updateByPrimaryKey(InterfTradStockdeal record);
}