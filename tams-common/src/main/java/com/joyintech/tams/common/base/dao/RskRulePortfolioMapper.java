package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskRulePortfolio;
import com.joyintech.tams.common.base.entity.RskRulePortfolioKey;

@Repository
public interface RskRulePortfolioMapper {

    int deleteByPrimaryKey(RskRulePortfolioKey key);

    int insert(RskRulePortfolio record);

    int insertSelective(RskRulePortfolio record);

    int insertBasicByBasicInfo(RskRulePortfolio record);

    RskRulePortfolio selectByPrimaryKey(RskRulePortfolioKey key);

    int updateByPrimaryKeySelective(RskRulePortfolio record);

    int updateByPrimaryKey(RskRulePortfolio record);
}