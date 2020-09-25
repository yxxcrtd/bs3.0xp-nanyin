package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstExponentQuotation;
import com.joyintech.tams.common.base.entity.MstExponentQuotationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstExponentQuotationMapper {
    int deleteByPrimaryKey(MstExponentQuotationKey key);

    int insert(MstExponentQuotation record);

    int insertSelective(MstExponentQuotation record);

    int insertBasicByBasicInfo(MstExponentQuotation record);

    MstExponentQuotation selectByPrimaryKey(MstExponentQuotationKey key);

    int updateByPrimaryKeySelective(MstExponentQuotation record);

    int updateByPrimaryKey(MstExponentQuotation record);
}