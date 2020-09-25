package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxDeductedBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDeductedBalanceMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxDeductedBalance record);

    int insertSelective(TaxDeductedBalance record);

    int insertBasicByBasicInfo(TaxDeductedBalance record);

    TaxDeductedBalance selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxDeductedBalance record);

    int updateByPrimaryKey(TaxDeductedBalance record);
}