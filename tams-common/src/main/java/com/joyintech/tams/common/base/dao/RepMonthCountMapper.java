package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepMonthCount;
import com.joyintech.tams.common.base.entity.RepMonthCountKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepMonthCountMapper {
    int deleteByPrimaryKey(RepMonthCountKey key);

    int insert(RepMonthCount record);

    int insertSelective(RepMonthCount record);

    int insertBasicByBasicInfo(RepMonthCount record);

    RepMonthCount selectByPrimaryKey(RepMonthCountKey key);

    int updateByPrimaryKeySelective(RepMonthCount record);

    int updateByPrimaryKey(RepMonthCount record);
}