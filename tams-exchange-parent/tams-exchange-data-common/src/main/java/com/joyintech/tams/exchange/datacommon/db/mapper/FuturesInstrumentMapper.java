package com.joyintech.tams.exchange.datacommon.db.mapper;


import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuturesInstrumentMapper extends IMybatisMapper<FuturesInstrument, String, FuturesInstrumentExample> {
    int countByExample(FuturesInstrumentExample example);

    int deleteByExample(FuturesInstrumentExample example);

    int deleteByPrimaryKey(String innerCode);

    int insert(FuturesInstrument record);

    int insertSelective(FuturesInstrument record);

    List<FuturesInstrument> selectByExample(FuturesInstrumentExample example);

    FuturesInstrument selectByPrimaryKey(String innerCode);

    int updateByExampleSelective(@Param("record") FuturesInstrument record, @Param("example") FuturesInstrumentExample example);

    int updateByExample(@Param("record") FuturesInstrument record, @Param("example") FuturesInstrumentExample example);

    int updateByPrimaryKeySelective(FuturesInstrument record);

    int updateByPrimaryKey(FuturesInstrument record);

    int updateOrInsertSelective(FuturesInstrument record);
}