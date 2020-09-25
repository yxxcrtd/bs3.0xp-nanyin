package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductConvertible;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductConvertibleMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(FinProductConvertible record);

    int insertSelective(FinProductConvertible record);

    int insertBasicByBasicInfo(FinProductConvertible record);

    FinProductConvertible selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(FinProductConvertible record);

    int updateByPrimaryKey(FinProductConvertible record);
}