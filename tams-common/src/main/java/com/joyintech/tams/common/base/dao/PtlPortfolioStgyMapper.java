package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioStgy;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioStgyMapper {
    int deleteByPrimaryKey(String ptlStgyId);

    int insert(PtlPortfolioStgy record);

    int insertSelective(PtlPortfolioStgy record);

    int insertBasicByBasicInfo(PtlPortfolioStgy record);

    PtlPortfolioStgy selectByPrimaryKey(String ptlStgyId);

    int updateByPrimaryKeySelective(PtlPortfolioStgy record);

    int updateByPrimaryKey(PtlPortfolioStgy record);
}