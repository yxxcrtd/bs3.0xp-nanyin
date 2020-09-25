package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeInfoMapper {
    int deleteByPrimaryKey(String schId);

    int insert(BokSchemeInfo record);

    int insertSelective(BokSchemeInfo record);

    int insertBasicByBasicInfo(BokSchemeInfo record);

    BokSchemeInfo selectByPrimaryKey(String schId);

    int updateByPrimaryKeySelective(BokSchemeInfo record);

    int updateByPrimaryKey(BokSchemeInfo record);
}