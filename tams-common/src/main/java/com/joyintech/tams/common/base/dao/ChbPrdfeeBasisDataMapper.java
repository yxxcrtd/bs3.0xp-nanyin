package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdfeeBasisData;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdfeeBasisDataMapper {
    int insert(ChbPrdfeeBasisData record);

    int insertSelective(ChbPrdfeeBasisData record);

    int insertBasicByBasicInfo(ChbPrdfeeBasisData record);
}