package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAstQdiiShare;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAstQdiiShareMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAstQdiiShare record);

    int insertSelective(RepAstQdiiShare record);

    int insertBasicByBasicInfo(RepAstQdiiShare record);

    RepAstQdiiShare selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAstQdiiShare record);

    int updateByPrimaryKey(RepAstQdiiShare record);
}