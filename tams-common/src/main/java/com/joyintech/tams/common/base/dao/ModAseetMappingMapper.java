package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModAseetMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface ModAseetMappingMapper {
    int insert(ModAseetMapping record);

    int insertSelective(ModAseetMapping record);

    int insertBasicByBasicInfo(ModAseetMapping record);
}