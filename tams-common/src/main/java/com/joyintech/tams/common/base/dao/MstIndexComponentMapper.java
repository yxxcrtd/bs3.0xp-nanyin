package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.MstIndexComponentKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstIndexComponentMapper {
    int deleteByPrimaryKey(MstIndexComponentKey key);

    int insert(MstIndexComponent record);

    int insertSelective(MstIndexComponent record);

    int insertBasicByBasicInfo(MstIndexComponent record);

    MstIndexComponent selectByPrimaryKey(MstIndexComponentKey key);

    int updateByPrimaryKeySelective(MstIndexComponent record);

    int updateByPrimaryKey(MstIndexComponent record);
}