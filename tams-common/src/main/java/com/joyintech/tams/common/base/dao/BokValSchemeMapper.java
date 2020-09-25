package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValScheme;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValSchemeMapper {
    int deleteByPrimaryKey(String schemeId);

    int insert(BokValScheme record);

    int insertSelective(BokValScheme record);

    int insertBasicByBasicInfo(BokValScheme record);

    BokValScheme selectByPrimaryKey(String schemeId);

    int updateByPrimaryKeySelective(BokValScheme record);

    int updateByPrimaryKey(BokValScheme record);
}