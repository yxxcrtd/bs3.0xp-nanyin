package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdDeclareScheme;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdDeclareSchemeMapper {
    int deleteByPrimaryKey(String schemeId);

    int insert(PrdDeclareScheme record);

    int insertSelective(PrdDeclareScheme record);

    int insertBasicByBasicInfo(PrdDeclareScheme record);

    PrdDeclareScheme selectByPrimaryKey(String schemeId);

    int updateByPrimaryKeySelective(PrdDeclareScheme record);

    int updateByPrimaryKey(PrdDeclareScheme record);
}