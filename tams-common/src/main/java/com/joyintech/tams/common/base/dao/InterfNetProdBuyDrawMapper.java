package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfNetProdBuyDraw;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfNetProdBuyDrawMapper {
    int insert(InterfNetProdBuyDraw record);

    int insertSelective(InterfNetProdBuyDraw record);

    int insertBasicByBasicInfo(InterfNetProdBuyDraw record);
}