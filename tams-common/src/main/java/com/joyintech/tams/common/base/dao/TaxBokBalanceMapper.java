package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxBokBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBokBalanceMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxBokBalance record);

    int insertSelective(TaxBokBalance record);

    int insertBasicByBasicInfo(TaxBokBalance record);

    TaxBokBalance selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxBokBalance record);

    int updateByPrimaryKey(TaxBokBalance record);
}