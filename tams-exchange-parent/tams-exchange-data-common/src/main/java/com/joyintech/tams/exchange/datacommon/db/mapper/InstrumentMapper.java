package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.InstrumentExample;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InstrumentMapper extends IMybatisMapper<Instrument, String, InstrumentExample> {
    int countByExample(InstrumentExample example);

    int deleteByExample(InstrumentExample example);

    int insert(Instrument record);

    int insertBatch(List<Instrument> records);

    int insertSelective(Instrument record);

    int deleteBatchByPrimaryKey(List<String> ids);

    List<Instrument> selectByExample(InstrumentExample example);

    int updateByExampleSelective(Instrument record, InstrumentExample example);

    int updateByExample(Instrument record, InstrumentExample example);

    int updateOrInsertSelective(Instrument record);
}