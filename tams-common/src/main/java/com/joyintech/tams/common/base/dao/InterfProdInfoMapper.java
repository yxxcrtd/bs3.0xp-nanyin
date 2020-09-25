package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfProdInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfProdInfoMapper {
    int insert(InterfProdInfo record);

    int insertSelective(InterfProdInfo record);

    int insertBasicByBasicInfo(InterfProdInfo record);
}