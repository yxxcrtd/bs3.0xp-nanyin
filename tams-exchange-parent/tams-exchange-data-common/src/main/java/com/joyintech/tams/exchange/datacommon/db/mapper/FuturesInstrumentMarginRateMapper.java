package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBean;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBeanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuturesInstrumentMarginRateMapper extends IMybatisMapper<FuturesInstrumentMarginRateBean, String, FuturesInstrumentMarginRateBeanExample> {
    int countByExample(FuturesInstrumentMarginRateBeanExample example);

    int deleteByExample(FuturesInstrumentMarginRateBeanExample example);

    int deleteByPrimaryKey(String id);

    int insert(FuturesInstrumentMarginRateBean record);

    int insertSelective(FuturesInstrumentMarginRateBean record);

    List<FuturesInstrumentMarginRateBean> selectByExample(FuturesInstrumentMarginRateBeanExample example);

    FuturesInstrumentMarginRateBean selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FuturesInstrumentMarginRateBean record, @Param("example") FuturesInstrumentMarginRateBeanExample example);

    int updateByExample(@Param("record") FuturesInstrumentMarginRateBean record, @Param("example") FuturesInstrumentMarginRateBeanExample example);

    int updateByPrimaryKeySelective(FuturesInstrumentMarginRateBean record);

    int updateByPrimaryKey(FuturesInstrumentMarginRateBean record);

    int updateOrInsertSelective(FuturesInstrumentMarginRateBean record);

    int deleteBatchByPrimaryKey(List<String> ids);
}