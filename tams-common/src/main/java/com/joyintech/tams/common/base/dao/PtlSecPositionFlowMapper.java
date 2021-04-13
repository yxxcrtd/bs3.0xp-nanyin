package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PtlSecPositionFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(PtlSecPositionFlow record);

    int insertSelective(PtlSecPositionFlow record);

    int insertBasicByBasicInfo(PtlSecPositionFlow record);

    PtlSecPositionFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(PtlSecPositionFlow record);

    int updateByPrimaryKey(PtlSecPositionFlow record);

    List<PtlSecPositionFlow> selectSecFlow(Map<String, Object> params);

    void insertSelect04Sec(PtlSecPositionFlow flow);

    void insertSelect04SecFlowByDate(Map<String, Object> params);
    
    Map<String, Object> selectTradeReleaseStatus(String tradeid);

	int updateReleaseStatus(String tradeid);
}