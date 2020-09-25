package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAcctStandardNature;
import com.joyintech.tams.common.base.entity.BokAcctStandardNatureKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAcctStandardNatureMapper {
    int deleteByPrimaryKey(BokAcctStandardNatureKey key);

    int insert(BokAcctStandardNature record);

    int insertSelective(BokAcctStandardNature record);

    int insertBasicByBasicInfo(BokAcctStandardNature record);

    BokAcctStandardNature selectByPrimaryKey(BokAcctStandardNatureKey key);

    int updateByPrimaryKeySelective(BokAcctStandardNature record);

    int updateByPrimaryKey(BokAcctStandardNature record);
}