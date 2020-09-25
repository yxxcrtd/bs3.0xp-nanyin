package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAcctStandard;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAcctStandardMapper {
    int deleteByPrimaryKey(String acctStandardId);

    int insert(BokAcctStandard record);

    int insertSelective(BokAcctStandard record);

    int insertBasicByBasicInfo(BokAcctStandard record);

    BokAcctStandard selectByPrimaryKey(String acctStandardId);

    int updateByPrimaryKeySelective(BokAcctStandard record);

    int updateByPrimaryKey(BokAcctStandard record);
}