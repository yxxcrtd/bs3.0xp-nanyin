package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ProductBaseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBaseInfoMapper {
    int deleteByPrimaryKey(String prdDesignId);

    int insert(ProductBaseInfo record);

    int insertSelective(ProductBaseInfo record);

    int insertBasicByBasicInfo(ProductBaseInfo record);

    ProductBaseInfo selectByPrimaryKey(String prdDesignId);

    int updateByPrimaryKeySelective(ProductBaseInfo record);

    int updateByPrimaryKey(ProductBaseInfo record);
}