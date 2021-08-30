package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModConfigUseage;
import org.springframework.stereotype.Repository;

@Repository
public interface ModConfigUseageMapper {
    int insert(ModConfigUseage record);

    int insertSelective(ModConfigUseage record);

    int insertBasicByBasicInfo(ModConfigUseage record);
}