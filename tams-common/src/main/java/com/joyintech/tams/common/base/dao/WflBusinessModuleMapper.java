package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflBusinessModule;
import com.joyintech.tams.common.base.entity.WflBusinessModuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface WflBusinessModuleMapper {
    int deleteByPrimaryKey(WflBusinessModuleKey key);

    int insert(WflBusinessModule record);

    int insertSelective(WflBusinessModule record);

    int insertBasicByBasicInfo(WflBusinessModule record);

    WflBusinessModule selectByPrimaryKey(WflBusinessModuleKey key);

    int updateByPrimaryKeySelective(WflBusinessModule record);

    int updateByPrimaryKey(WflBusinessModule record);
}