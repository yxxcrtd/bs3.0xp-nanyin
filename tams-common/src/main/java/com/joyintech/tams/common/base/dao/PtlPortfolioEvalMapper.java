package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioEval;
import com.joyintech.tams.common.base.entity.PtlPortfolioEvalKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlPortfolioEvalMapper {
    int deleteByPrimaryKey(PtlPortfolioEvalKey key);

    int insert(PtlPortfolioEval record);

    int insertSelective(PtlPortfolioEval record);

    int insertBasicByBasicInfo(PtlPortfolioEval record);

    PtlPortfolioEval selectByPrimaryKey(PtlPortfolioEvalKey key);

    int updateByPrimaryKeySelective(PtlPortfolioEval record);

    int updateByPrimaryKey(PtlPortfolioEval record);
}