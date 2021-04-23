package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.BatchInfo;
import com.joyintech.tams.exchange.common.db.domain.BatchInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchInfoMapper extends IMybatisMapper<BatchInfo, String, BatchInfoExample> {
    long countByExample(BatchInfoExample example);

    int deleteByExample(BatchInfoExample example);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(BatchInfo record);

    int insertSelective(BatchInfo record);

    int updateOrInsertSelective(BatchInfo record);

    List<BatchInfo> selectByExample(BatchInfoExample example);

    BatchInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BatchInfo record, @Param("example") BatchInfoExample example);

    int updateByExample(@Param("record") BatchInfo record, @Param("example") BatchInfoExample example);

    int updateByPrimaryKeySelective(BatchInfo record);

    int updateByPrimaryKey(BatchInfo record);
}