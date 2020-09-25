package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TacticConfigOptDetailMapper {
    int deleteByPrimaryKey(TacticConfigOptDetailKey key);

    int insert(TacticConfigOptDetail record);

    int insertSelective(TacticConfigOptDetail record);

    int insertBasicByBasicInfo(TacticConfigOptDetail record);

    TacticConfigOptDetail selectByPrimaryKey(TacticConfigOptDetailKey key);

    int updateByPrimaryKeySelective(TacticConfigOptDetail record);

    int updateByPrimaryKey(TacticConfigOptDetail record);
}