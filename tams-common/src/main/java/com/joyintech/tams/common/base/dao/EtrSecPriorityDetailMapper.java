package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrSecPriorityDetailMapper {
    int insert(EtrSecPriorityDetail record);

    int insertSelective(EtrSecPriorityDetail record);

    int insertBasicByBasicInfo(EtrSecPriorityDetail record);
}