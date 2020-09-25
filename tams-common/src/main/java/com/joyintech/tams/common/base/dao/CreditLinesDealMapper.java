package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.common.base.entity.CreditLinesDealKey;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLinesDealMapper {
    int deleteByPrimaryKey(CreditLinesDealKey key);

    int insert(CreditLinesDeal record);

    int insertSelective(CreditLinesDeal record);

    int insertBasicByBasicInfo(CreditLinesDeal record);

    CreditLinesDeal selectByPrimaryKey(CreditLinesDealKey key);

    int updateByPrimaryKeySelective(CreditLinesDeal record);

    int updateByPrimaryKey(CreditLinesDeal record);
}