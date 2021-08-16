package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstBankNetvalue;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MstBankNetvalueMapper {
    int deleteByPrimaryKey(BigDecimal seqno);

    int insert(MstBankNetvalue record);

    int insertSelective(MstBankNetvalue record);

    int insertBasicByBasicInfo(MstBankNetvalue record);

    MstBankNetvalue selectByPrimaryKey(BigDecimal seqno);

    int updateByPrimaryKeySelective(MstBankNetvalue record);

    int updateByPrimaryKey(MstBankNetvalue record);
}