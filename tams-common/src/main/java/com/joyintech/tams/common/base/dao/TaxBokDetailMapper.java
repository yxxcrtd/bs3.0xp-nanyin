package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxBokDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBokDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxBokDetail record);

    int insertSelective(TaxBokDetail record);

    int insertBasicByBasicInfo(TaxBokDetail record);

    TaxBokDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxBokDetail record);

    int updateByPrimaryKey(TaxBokDetail record);
}