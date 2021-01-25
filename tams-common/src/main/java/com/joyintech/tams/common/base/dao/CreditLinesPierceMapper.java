package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditLinesPierce;
import com.joyintech.tams.common.base.entity.CreditLinesPierceKey;
import com.joyintech.tams.common.base.entity.CreditLinesSecPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLinesPierceMapper {
    int deleteByPrimaryKey(CreditLinesPierceKey key);

    int insert(CreditLinesPierce record);

    int insertSelective(CreditLinesPierce record);

    int insertBasicByBasicInfo(CreditLinesPierce record);

    CreditLinesSecPosition selectByPrimaryKey(CreditLinesPierceKey key);

    int updateByPrimaryKeySelective(CreditLinesPierce record);

    int updateByPrimaryKey(CreditLinesPierce record);
}