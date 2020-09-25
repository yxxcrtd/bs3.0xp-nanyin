package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxTurnedBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTurnedBalanceMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxTurnedBalance record);

    int insertSelective(TaxTurnedBalance record);

    int insertBasicByBasicInfo(TaxTurnedBalance record);

    TaxTurnedBalance selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxTurnedBalance record);

    int updateByPrimaryKey(TaxTurnedBalance record);
}