package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.LqdPipelineMatch;
import org.springframework.stereotype.Repository;

@Repository
public interface LqdPipelineMatchMapper {
    int deleteByPrimaryKey(String matchSeqno);

    int insert(LqdPipelineMatch record);

    int insertSelective(LqdPipelineMatch record);

    int insertBasicByBasicInfo(LqdPipelineMatch record);

    LqdPipelineMatch selectByPrimaryKey(String matchSeqno);

    int updateByPrimaryKeySelective(LqdPipelineMatch record);

    int updateByPrimaryKey(LqdPipelineMatch record);
}