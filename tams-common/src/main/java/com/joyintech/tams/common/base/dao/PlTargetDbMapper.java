package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PlTargetDb;
import com.joyintech.tams.common.base.entity.PlTargetDbKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PlTargetDbMapper {
    int deleteByPrimaryKey(PlTargetDbKey key);

    int insert(PlTargetDb record);

    int insertSelective(PlTargetDb record);

    int insertBasicByBasicInfo(PlTargetDb record);

    PlTargetDb selectByPrimaryKey(PlTargetDbKey key);

    int updateByPrimaryKeySelective(PlTargetDb record);

    int updateByPrimaryKey(PlTargetDb record);
}