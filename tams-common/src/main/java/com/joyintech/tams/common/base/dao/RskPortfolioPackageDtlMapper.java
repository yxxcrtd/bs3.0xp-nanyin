package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskPortfolioPackageDtl;
import com.joyintech.tams.common.base.entity.RskPortfolioPackageDtlKey;

@Repository
public interface RskPortfolioPackageDtlMapper {

    int deleteByPrimaryKey(RskPortfolioPackageDtlKey key);

    int insert(RskPortfolioPackageDtl record);

    int insertSelective(RskPortfolioPackageDtl record);

    int insertBasicByBasicInfo(RskPortfolioPackageDtl record);

    RskPortfolioPackageDtl selectByPrimaryKey(RskPortfolioPackageDtlKey key);

    int updateByPrimaryKeySelective(RskPortfolioPackageDtl record);

    int updateByPrimaryKey(RskPortfolioPackageDtl record);
}