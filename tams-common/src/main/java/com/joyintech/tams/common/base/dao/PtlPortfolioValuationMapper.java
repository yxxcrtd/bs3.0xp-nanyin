package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;
import com.joyintech.tams.common.base.entity.PtlPortfolioValuationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioValuationMapper {
    int deleteByPrimaryKey(PtlPortfolioValuationKey key);

    int insert(PtlPortfolioValuation record);

    int insertSelective(PtlPortfolioValuation record);

    int insertBasicByBasicInfo(PtlPortfolioValuation record);

    PtlPortfolioValuation selectByPrimaryKey(PtlPortfolioValuationKey key);

    int updateByPrimaryKeySelective(PtlPortfolioValuation record);

    int updateByPrimaryKey(PtlPortfolioValuation record);
}