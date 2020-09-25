package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.common.base.entity.FinProductCreditKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductCreditMapper {
    int deleteByPrimaryKey(FinProductCreditKey key);

    int insert(FinProductCredit record);

    int insertSelective(FinProductCredit record);

    int insertBasicByBasicInfo(FinProductCredit record);

    FinProductCredit selectByPrimaryKey(FinProductCreditKey key);

    int updateByPrimaryKeySelective(FinProductCredit record);

    int updateByPrimaryKey(FinProductCredit record);
}