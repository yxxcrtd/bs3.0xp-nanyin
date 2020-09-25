package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdStasecFlowDetail;
import com.joyintech.tams.common.base.entity.TrdStasecFlowDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdStasecFlowDetailMapper {
    int deleteByPrimaryKey(TrdStasecFlowDetailKey key);

    int insert(TrdStasecFlowDetail record);

    int insertSelective(TrdStasecFlowDetail record);

    int insertBasicByBasicInfo(TrdStasecFlowDetail record);

    TrdStasecFlowDetail selectByPrimaryKey(TrdStasecFlowDetailKey key);

    int updateByPrimaryKeySelective(TrdStasecFlowDetail record);

    int updateByPrimaryKey(TrdStasecFlowDetail record);
}