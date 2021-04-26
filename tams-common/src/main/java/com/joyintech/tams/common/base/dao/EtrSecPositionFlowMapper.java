package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrSecPositionFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EtrSecPositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(EtrSecPositionFlow record);

    int insertSelective(EtrSecPositionFlow record);

    int insertBasicByBasicInfo(EtrSecPositionFlow record);

    EtrSecPositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(EtrSecPositionFlow record);

    int updateByPrimaryKey(EtrSecPositionFlow record);

    List<EtrSecPositionFlow> selectEtrSecFlow(Map<String, Object> params);
}