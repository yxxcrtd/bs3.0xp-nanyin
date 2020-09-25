package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfFxrmbmidrate;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfFxrmbmidrateMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfFxrmbmidrate record);

    int insertSelective(InterfFxrmbmidrate record);

    int insertBasicByBasicInfo(InterfFxrmbmidrate record);

    InterfFxrmbmidrate selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfFxrmbmidrate record);

    int updateByPrimaryKey(InterfFxrmbmidrate record);
}