package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.db.domain.TradeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}