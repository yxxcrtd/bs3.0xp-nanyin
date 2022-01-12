package com.joyintech.tams.exchange.datacommon.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.db.domain.TradeExample;

@Repository
public interface TradeMapper extends IMybatisMapper<Trade, String, TradeExample> {
    int countByExample(TradeExample example);

    int deleteByExample(TradeExample example);

    int insert(Trade record);

    int insertSelective(Trade record);

    int updateOrInsertSelective(Trade record);

    int updateByPrimaryKey(Trade record);

    List<Trade> selectByExample(TradeExample example);

    int updateByExampleSelective(@Param("record") Trade record, @Param("example") TradeExample example);

    int updateByExample(@Param("record") Trade record, @Param("example") TradeExample example);

    //允许默认值
    int updateByPrimaryKeySelective(Trade record);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);
    
    List<Trade> queryTrade2Touguan(@Param("instructionId")String instructionId);
}