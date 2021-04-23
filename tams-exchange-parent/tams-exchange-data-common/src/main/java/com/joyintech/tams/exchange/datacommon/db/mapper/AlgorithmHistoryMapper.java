package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.AlgorithmHistory;
import com.joyintech.tams.exchange.common.db.domain.AlgorithmHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgorithmHistoryMapper {
    int countByExample(AlgorithmHistoryExample example);

    int deleteByExample(AlgorithmHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(AlgorithmHistory record);

    int insertSelective(AlgorithmHistory record);

    List<AlgorithmHistory> selectByExample(AlgorithmHistoryExample example);

    AlgorithmHistory selectByPrimaryKey(String id);

    int insertBatch(List<AlgorithmHistory> historyList);

    int updateByExampleSelective(@Param("record") AlgorithmHistory record, @Param("example") AlgorithmHistoryExample example);

    int updateByExample(@Param("record") AlgorithmHistory record, @Param("example") AlgorithmHistoryExample example);

    int updateByPrimaryKeySelective(AlgorithmHistory record);

    int updateByPrimaryKey(AlgorithmHistory record);

    String queryLastClearDate();
}