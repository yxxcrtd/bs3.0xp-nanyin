package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAstQdiiStruct;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAstQdiiStructMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAstQdiiStruct record);

    int insertSelective(RepAstQdiiStruct record);

    int insertBasicByBasicInfo(RepAstQdiiStruct record);

    RepAstQdiiStruct selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAstQdiiStruct record);

    int updateByPrimaryKey(RepAstQdiiStruct record);
}