package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.IbuPortfolioAcctRela;
import com.joyintech.tams.common.base.entity.IbuPortfolioAcctRelaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuPortfolioAcctRelaMapper {
    int deleteByPrimaryKey(IbuPortfolioAcctRelaKey key);

    int insert(IbuPortfolioAcctRela record);

    int insertSelective(IbuPortfolioAcctRela record);

    int insertBasicByBasicInfo(IbuPortfolioAcctRela record);

    IbuPortfolioAcctRela selectByPrimaryKey(IbuPortfolioAcctRelaKey key);

    int updateByPrimaryKeySelective(IbuPortfolioAcctRela record);

    int updateByPrimaryKey(IbuPortfolioAcctRela record);
}