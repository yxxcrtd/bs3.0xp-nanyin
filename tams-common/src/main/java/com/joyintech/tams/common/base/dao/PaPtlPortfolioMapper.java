package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaPtlPortfolio;
import org.springframework.stereotype.Repository;

@Repository
public interface PaPtlPortfolioMapper {
    int deleteByPrimaryKey(String portfolioId);

    int insert(PaPtlPortfolio record);

    int insertSelective(PaPtlPortfolio record);

    int insertBasicByBasicInfo(PaPtlPortfolio record);

    PaPtlPortfolio selectByPrimaryKey(String portfolioId);

    int updateByPrimaryKeySelective(PaPtlPortfolio record);

    int updateByPrimaryKey(PaPtlPortfolio record);
}