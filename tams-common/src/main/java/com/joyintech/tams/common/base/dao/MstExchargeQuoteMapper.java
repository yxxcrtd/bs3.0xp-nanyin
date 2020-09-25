package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.base.entity.MstExchargeQuoteKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstExchargeQuoteMapper {
    int deleteByPrimaryKey(MstExchargeQuoteKey key);

    int insert(MstExchargeQuote record);

    int insertSelective(MstExchargeQuote record);

    int insertBasicByBasicInfo(MstExchargeQuote record);

    MstExchargeQuote selectByPrimaryKey(MstExchargeQuoteKey key);

    int updateByPrimaryKeySelective(MstExchargeQuote record);

    int updateByPrimaryKey(MstExchargeQuote record);
}