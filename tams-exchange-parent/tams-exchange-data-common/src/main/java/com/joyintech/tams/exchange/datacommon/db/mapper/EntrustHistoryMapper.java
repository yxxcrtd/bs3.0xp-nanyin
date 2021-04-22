package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.EntrustHistory;
import com.joyintech.tams.exchange.common.db.domain.EntrustHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrustHistoryMapper extends IMybatisMapper<EntrustHistory, String, EntrustHistoryExample> {
    int countByExample(EntrustHistoryExample example);

    int deleteByExample(EntrustHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(EntrustHistory record);

    int insertSelective(EntrustHistory record);

    int insertBatch(List<EntrustHistory> records);

    List<EntrustHistory> selectByExample(EntrustHistoryExample example);

    EntrustHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EntrustHistory record, @Param("example") EntrustHistoryExample example);

    int updateByExample(@Param("record") EntrustHistory record, @Param("example") EntrustHistoryExample example);

    int updateByPrimaryKeySelective(EntrustHistory record);

    int updateByPrimaryKey(EntrustHistory record);

    String queryLastClearDate();
}