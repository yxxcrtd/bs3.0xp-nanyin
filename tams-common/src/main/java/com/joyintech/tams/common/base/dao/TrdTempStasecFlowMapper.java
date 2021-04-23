package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdTempStasecFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdTempStasecFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrdTempStasecFlow record);

    int insertSelective(TrdTempStasecFlow record);

    int insertBasicByBasicInfo(TrdTempStasecFlow record);

    TrdTempStasecFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrdTempStasecFlow record);

    int updateByPrimaryKey(TrdTempStasecFlow record);
}