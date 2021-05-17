package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdIndexDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIndexDetailMapper {
    int insert(RepProdIndexDetail record);

    int insertSelective(RepProdIndexDetail record);

    int insertBasicByBasicInfo(RepProdIndexDetail record);
}