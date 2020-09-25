package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskPortfolioIndicatorKey;
import com.joyintech.tams.common.base.entity.RskPortfolioIndicator;

@Repository
public interface RskPortfolioIndicatorMapper {

    int deleteByPrimaryKey(RskPortfolioIndicatorKey key);

    int insert(RskPortfolioIndicator record);

    int insertSelective(RskPortfolioIndicator record);

    int insertBasicByBasicInfo(RskPortfolioIndicator record);

    RskPortfolioIndicator selectByPrimaryKey(RskPortfolioIndicatorKey key);

    int updateByPrimaryKeySelective(RskPortfolioIndicator record);

    int updateByPrimaryKey(RskPortfolioIndicator record);
}