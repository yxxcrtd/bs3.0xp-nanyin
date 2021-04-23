package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.db.domain.AlgorithmExample;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlgorithmMapper extends IMybatisMapper<Algorithm, String, AlgorithmExample> {
    int countByExample(AlgorithmExample example);

    int deleteByExample(AlgorithmExample example);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(Algorithm record);

    int insertSelective(Algorithm record);

    int updateOrInsertSelective(Algorithm record);

    List<Algorithm> selectByExample(AlgorithmExample example);

    Algorithm selectByPrimaryKey(String id);

    int updateByExampleSelective(Algorithm record, AlgorithmExample example);

    int updateByExample(Algorithm record, AlgorithmExample example);

    int updateByPrimaryKeySelective(Algorithm record);

    int updateByPrimaryKey(Algorithm record);
}