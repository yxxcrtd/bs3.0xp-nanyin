package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModAssetGradeConfigMapper {
    int insert(ModAssetGradeConfig record);

    int insertSelective(ModAssetGradeConfig record);

    int insertBasicByBasicInfo(ModAssetGradeConfig record);
}