package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxBokConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBokConfigMapper {
    int deleteByPrimaryKey(String configId);

    int insert(TaxBokConfig record);

    int insertSelective(TaxBokConfig record);

    int insertBasicByBasicInfo(TaxBokConfig record);

    TaxBokConfig selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(TaxBokConfig record);

    int updateByPrimaryKey(TaxBokConfig record);
}