package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SppiTestQa;
import com.joyintech.tams.common.base.entity.SppiTestQaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SppiTestQaMapper {
    int deleteByPrimaryKey(SppiTestQaKey key);

    int insert(SppiTestQa record);

    int insertSelective(SppiTestQa record);

    int insertBasicByBasicInfo(SppiTestQa record);

    SppiTestQa selectByPrimaryKey(SppiTestQaKey key);

    int updateByPrimaryKeySelective(SppiTestQa record);

    int updateByPrimaryKey(SppiTestQa record);
}