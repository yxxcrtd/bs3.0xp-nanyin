package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBonusDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdBonusDealMapper {
    int insert(ChbPrdBonusDeal record);

    int insertSelective(ChbPrdBonusDeal record);

    int insertBasicByBasicInfo(ChbPrdBonusDeal record);
}