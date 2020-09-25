package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinTermStructInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FinTermStructInfoMapper {
    int deleteByPrimaryKey(String termStructId);

    int insert(FinTermStructInfo record);

    int insertSelective(FinTermStructInfo record);

    int insertBasicByBasicInfo(FinTermStructInfo record);

    FinTermStructInfo selectByPrimaryKey(String termStructId);

    int updateByPrimaryKeySelective(FinTermStructInfo record);

    int updateByPrimaryKey(FinTermStructInfo record);
}