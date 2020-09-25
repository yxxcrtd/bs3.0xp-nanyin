package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfTcms001;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfTcms001Mapper {
    int insert(InterfTcms001 record);

    int insertSelective(InterfTcms001 record);

    int insertBasicByBasicInfo(InterfTcms001 record);
}