package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdTempStasecFlowDetail;
import com.joyintech.tams.common.base.entity.TrdTempStasecFlowDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdTempStasecFlowDetailMapper {
    int deleteByPrimaryKey(TrdTempStasecFlowDetailKey key);

    int insert(TrdTempStasecFlowDetail record);

    int insertSelective(TrdTempStasecFlowDetail record);

    int insertBasicByBasicInfo(TrdTempStasecFlowDetail record);

    TrdTempStasecFlowDetail selectByPrimaryKey(TrdTempStasecFlowDetailKey key);

    int updateByPrimaryKeySelective(TrdTempStasecFlowDetail record);

    int updateByPrimaryKey(TrdTempStasecFlowDetail record);
}