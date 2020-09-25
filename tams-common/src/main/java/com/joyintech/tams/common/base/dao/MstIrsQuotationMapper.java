package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstIrsQuotation;
import com.joyintech.tams.common.base.entity.MstIrsQuotationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstIrsQuotationMapper {
    int deleteByPrimaryKey(MstIrsQuotationKey key);

    int insert(MstIrsQuotation record);

    int insertSelective(MstIrsQuotation record);

    int insertBasicByBasicInfo(MstIrsQuotation record);

    MstIrsQuotation selectByPrimaryKey(MstIrsQuotationKey key);

    int updateByPrimaryKeySelective(MstIrsQuotation record);

    int updateByPrimaryKey(MstIrsQuotation record);
}