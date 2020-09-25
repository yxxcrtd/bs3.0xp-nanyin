package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxTaxableInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTaxableInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxTaxableInfo record);

    int insertSelective(TaxTaxableInfo record);

    int insertBasicByBasicInfo(TaxTaxableInfo record);

    TaxTaxableInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxTaxableInfo record);

    int updateByPrimaryKey(TaxTaxableInfo record);
}