package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface RepEmployeeMapper {
    int deleteByPrimaryKey(String repEmployeeSystemCode);

    int insert(RepEmployee record);

    int insertSelective(RepEmployee record);

    int insertBasicByBasicInfo(RepEmployee record);

    RepEmployee selectByPrimaryKey(String repEmployeeSystemCode);

    int updateByPrimaryKeySelective(RepEmployee record);

    int updateByPrimaryKey(RepEmployee record);
}