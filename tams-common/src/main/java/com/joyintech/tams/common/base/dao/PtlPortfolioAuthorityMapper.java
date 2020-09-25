package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioAuthority;
import com.joyintech.tams.common.base.entity.PtlPortfolioAuthorityKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioAuthorityMapper {
    int deleteByPrimaryKey(PtlPortfolioAuthorityKey key);

    int insert(PtlPortfolioAuthority record);

    int insertSelective(PtlPortfolioAuthority record);

    int insertBasicByBasicInfo(PtlPortfolioAuthority record);

    PtlPortfolioAuthority selectByPrimaryKey(PtlPortfolioAuthorityKey key);

    int updateByPrimaryKeySelective(PtlPortfolioAuthority record);

    int updateByPrimaryKey(PtlPortfolioAuthority record);
}