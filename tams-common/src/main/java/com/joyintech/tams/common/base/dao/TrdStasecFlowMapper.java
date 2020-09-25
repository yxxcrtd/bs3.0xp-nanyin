package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdStasecFlow;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdStasecFlowMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrdStasecFlow record);

    int insertSelective(TrdStasecFlow record);

    int insertBasicByBasicInfo(TrdStasecFlow record);

    TrdStasecFlow selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrdStasecFlow record);

    int updateByPrimaryKey(TrdStasecFlow record);
}