package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAstOffbeat;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAstOffbeatMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAstOffbeat record);

    int insertSelective(RepAstOffbeat record);

    int insertBasicByBasicInfo(RepAstOffbeat record);

    RepAstOffbeat selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAstOffbeat record);

    int updateByPrimaryKey(RepAstOffbeat record);
}