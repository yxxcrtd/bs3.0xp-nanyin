package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokBussDetsubKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBussDetsubMapper {
    int deleteByPrimaryKey(BokBussDetsubKey key);

    int insert(BokBussDetsub record);

    int insertSelective(BokBussDetsub record);

    int insertBasicByBasicInfo(BokBussDetsub record);

    BokBussDetsub selectByPrimaryKey(BokBussDetsubKey key);

    int updateByPrimaryKeySelective(BokBussDetsub record);

    int updateByPrimaryKey(BokBussDetsub record);
}