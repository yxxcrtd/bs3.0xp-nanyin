package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioDis;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioDisMapper {
    int deleteByPrimaryKey(String portfolioId);

    int insert(PtlPortfolioDis record);

    int insertSelective(PtlPortfolioDis record);

    int insertBasicByBasicInfo(PtlPortfolioDis record);

    PtlPortfolioDis selectByPrimaryKey(String portfolioId);

    int updateByPrimaryKeySelective(PtlPortfolioDis record);

    int updateByPrimaryKey(PtlPortfolioDis record);
    //add by libaisong 2019-10-08 删除组合清算账户表
    int deletePCIByPrimaryKey(String portfolioId);
}