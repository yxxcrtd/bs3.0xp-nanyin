package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxCfBokConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxCfBokConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxCfBokConfig record);

    int insertSelective(TaxCfBokConfig record);

    int insertBasicByBasicInfo(TaxCfBokConfig record);

    TaxCfBokConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxCfBokConfig record);

    int updateByPrimaryKey(TaxCfBokConfig record);
}