package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.bean.EntrustQryBean;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustHistory;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegratedEntrustHistoryMapper extends IMybatisMapper<IntegratedEntrustHistory, String, IntegratedEntrustHistoryExample> {
    int countByExample(IntegratedEntrustHistoryExample example);

    int deleteByExample(IntegratedEntrustHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(IntegratedEntrustHistory record);

    int insertBatch(List<IntegratedEntrustHistory> records);

    int insertSelective(IntegratedEntrustHistory record);

    List<IntegratedEntrustHistory> selectByExample(IntegratedEntrustHistoryExample example);

    List<IntegratedEntrustHistory> selectEntrustsPageInfo(EntrustQryBean entrustQryBean);

    IntegratedEntrustHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IntegratedEntrustHistory record, @Param("example") IntegratedEntrustHistoryExample example);

    int updateByExample(@Param("record") IntegratedEntrustHistory record, @Param("example") IntegratedEntrustHistoryExample example);

    int updateByPrimaryKeySelective(IntegratedEntrustHistory record);

    int updateByPrimaryKey(IntegratedEntrustHistory record);

    String queryLastClearDate();
}