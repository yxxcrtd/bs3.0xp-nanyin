package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Bar;
import com.joyintech.tams.exchange.common.db.domain.BarExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarMapper {
    int countByExample(BarExample example);

    int deleteByExample(BarExample example);

    int insert(Bar record);

    int insertSelective(Bar record);

    List<Bar> selectByExample(BarExample example);

    int updateByExampleSelective(@Param("record") Bar record, @Param("example") BarExample example);

    int updateByExample(@Param("record") Bar record, @Param("example") BarExample example);

    List<Bar> queryLastDaysBar(@Param("instrumentId") String instrumentId, @Param("days") int days, @Param("tradingDay") String tradingDay);
}