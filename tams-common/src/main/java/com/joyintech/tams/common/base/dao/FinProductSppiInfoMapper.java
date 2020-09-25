package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductSppiInfo;
import com.joyintech.tams.common.base.entity.FinProductSppiInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductSppiInfoMapper {
    int deleteByPrimaryKey(FinProductSppiInfoKey key);

    int insert(FinProductSppiInfo record);

    int insertSelective(FinProductSppiInfo record);

    int insertBasicByBasicInfo(FinProductSppiInfo record);

    FinProductSppiInfo selectByPrimaryKey(FinProductSppiInfoKey key);

    int updateByPrimaryKeySelective(FinProductSppiInfo record);

    int updateByPrimaryKey(FinProductSppiInfo record);
}