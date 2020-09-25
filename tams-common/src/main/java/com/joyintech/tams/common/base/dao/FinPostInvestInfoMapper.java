package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPostInvestInfoMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(FinPostInvestInfo record);

    int insertSelective(FinPostInvestInfo record);

    int insertBasicByBasicInfo(FinPostInvestInfo record);

    FinPostInvestInfo selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(FinPostInvestInfo record);

    int updateByPrimaryKey(FinPostInvestInfo record);
}