package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustRe;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustReMapper {
    int insert(EtrEntrustRe record);

    int insertSelective(EtrEntrustRe record);

    int insertBasicByBasicInfo(EtrEntrustRe record);
}