package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxSchaBokConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxSchaBokConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxSchaBokConfig record);

    int insertSelective(TaxSchaBokConfig record);

    int insertBasicByBasicInfo(TaxSchaBokConfig record);

    TaxSchaBokConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxSchaBokConfig record);

    int updateByPrimaryKey(TaxSchaBokConfig record);
}