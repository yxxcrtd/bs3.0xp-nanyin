package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloatKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlIncomeIndexFloatMapper {
    int deleteByPrimaryKey(PtlIncomeIndexFloatKey key);

    int insert(PtlIncomeIndexFloat record);

    int insertSelective(PtlIncomeIndexFloat record);

    int insertBasicByBasicInfo(PtlIncomeIndexFloat record);

    PtlIncomeIndexFloat selectByPrimaryKey(PtlIncomeIndexFloatKey key);

    int updateByPrimaryKeySelective(PtlIncomeIndexFloat record);

    int updateByPrimaryKey(PtlIncomeIndexFloat record);
}