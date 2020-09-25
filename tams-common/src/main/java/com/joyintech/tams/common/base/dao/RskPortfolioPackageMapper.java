package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskPortfolioPackage;

@Repository
public interface RskPortfolioPackageMapper {

    int deleteByPrimaryKey(String ptlBagId);

    int insert(RskPortfolioPackage record);

    int insertSelective(RskPortfolioPackage record);

    int insertBasicByBasicInfo(RskPortfolioPackage record);

    RskPortfolioPackage selectByPrimaryKey(String ptlBagId);

    int updateByPrimaryKeySelective(RskPortfolioPackage record);

    int updateByPrimaryKey(RskPortfolioPackage record);
}