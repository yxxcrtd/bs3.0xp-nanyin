package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaEtrAutoDispenseTime;
import org.springframework.stereotype.Repository;

@Repository
public interface PaEtrAutoDispenseTimeMapper {
    int insert(PaEtrAutoDispenseTime record);

    int insertSelective(PaEtrAutoDispenseTime record);

    int insertBasicByBasicInfo(PaEtrAutoDispenseTime record);
}