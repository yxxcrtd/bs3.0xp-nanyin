package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdIndex;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIndexMapper {
    int insert(RepProdIndex record);

    int insertSelective(RepProdIndex record);

    int insertBasicByBasicInfo(RepProdIndex record);
}