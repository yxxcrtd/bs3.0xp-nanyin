package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAstMetal;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAstMetalMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAstMetal record);

    int insertSelective(RepAstMetal record);

    int insertBasicByBasicInfo(RepAstMetal record);

    RepAstMetal selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAstMetal record);

    int updateByPrimaryKey(RepAstMetal record);
}