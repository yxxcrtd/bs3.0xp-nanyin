package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBankBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBankBalanceMapper {
    int deleteByPrimaryKey(String sqeNo);

    int insert(BokBankBalance record);

    int insertSelective(BokBankBalance record);

    int insertBasicByBasicInfo(BokBankBalance record);

    BokBankBalance selectByPrimaryKey(String sqeNo);

    int updateByPrimaryKeySelective(BokBankBalance record);

    int updateByPrimaryKey(BokBankBalance record);
}