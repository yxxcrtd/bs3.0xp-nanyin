package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepRelorg;
import org.springframework.stereotype.Repository;

@Repository
public interface RepRelorgMapper {
    int insert(RepRelorg record);

    int insertSelective(RepRelorg record);

    int insertBasicByBasicInfo(RepRelorg record);
}