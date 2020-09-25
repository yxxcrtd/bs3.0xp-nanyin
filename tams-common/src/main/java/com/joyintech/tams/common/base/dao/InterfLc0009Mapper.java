package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfLc0009;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfLc0009Mapper {
    int insert(InterfLc0009 record);

    int insertSelective(InterfLc0009 record);

    int insertBasicByBasicInfo(InterfLc0009 record);
}