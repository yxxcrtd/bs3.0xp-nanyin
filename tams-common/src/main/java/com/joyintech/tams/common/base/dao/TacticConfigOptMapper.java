package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TacticConfigOpt;
import org.springframework.stereotype.Repository;

@Repository
public interface TacticConfigOptMapper {
    int deleteByPrimaryKey(String tacticConfigCode);

    int insert(TacticConfigOpt record);

    int insertSelective(TacticConfigOpt record);

    int insertBasicByBasicInfo(TacticConfigOpt record);

    TacticConfigOpt selectByPrimaryKey(String tacticConfigCode);

    int updateByPrimaryKeySelective(TacticConfigOpt record);

    int updateByPrimaryKey(TacticConfigOpt record);
}