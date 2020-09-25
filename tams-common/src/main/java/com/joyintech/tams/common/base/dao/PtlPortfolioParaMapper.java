package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioPara;
import com.joyintech.tams.common.base.entity.PtlPortfolioParaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioParaMapper {
    int deleteByPrimaryKey(PtlPortfolioParaKey key);

    int insert(PtlPortfolioPara record);

    int insertSelective(PtlPortfolioPara record);

    int insertBasicByBasicInfo(PtlPortfolioPara record);

    PtlPortfolioPara selectByPrimaryKey(PtlPortfolioParaKey key);

    int updateByPrimaryKeySelective(PtlPortfolioPara record);

    int updateByPrimaryKey(PtlPortfolioPara record);
}