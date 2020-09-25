package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokFeeTableSet;
import com.joyintech.tams.common.base.entity.BokFeeTableSetKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokFeeTableSetMapper {
    int deleteByPrimaryKey(BokFeeTableSetKey key);

    int insert(BokFeeTableSet record);

    int insertSelective(BokFeeTableSet record);

    int insertBasicByBasicInfo(BokFeeTableSet record);

    BokFeeTableSet selectByPrimaryKey(BokFeeTableSetKey key);

    int updateByPrimaryKeySelective(BokFeeTableSet record);

    int updateByPrimaryKey(BokFeeTableSet record);
}