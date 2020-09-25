package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlIncomeIndex;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlIncomeIndexMapper {
    int deleteByPrimaryKey(String indexId);

    int insert(PtlIncomeIndex record);

    int insertSelective(PtlIncomeIndex record);

    int insertBasicByBasicInfo(PtlIncomeIndex record);

    PtlIncomeIndex selectByPrimaryKey(String indexId);

    int updateByPrimaryKeySelective(PtlIncomeIndex record);

    int updateByPrimaryKey(PtlIncomeIndex record);
}