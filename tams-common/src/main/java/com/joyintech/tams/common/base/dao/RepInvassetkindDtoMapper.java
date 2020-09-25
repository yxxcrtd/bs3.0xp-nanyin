package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepInvassetkindDto;
import com.joyintech.tams.common.base.entity.RepInvassetkindDtoKey;

public interface RepInvassetkindDtoMapper {
    int deleteByPrimaryKey(RepInvassetkindDtoKey key);

    int insert(RepInvassetkindDto record);

    int insertSelective(RepInvassetkindDto record);

    RepInvassetkindDto selectByPrimaryKey(RepInvassetkindDtoKey key);

    int updateByPrimaryKeySelective(RepInvassetkindDto record);

    int updateByPrimaryKey(RepInvassetkindDto record);
}