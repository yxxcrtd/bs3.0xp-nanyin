package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdBankManualSetDetail;
import org.springframework.stereotype.Repository;
@Repository
public interface TrdBankManualSetDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdBankManualSetDetail record);

    int insertSelective(TrdBankManualSetDetail record);

    TrdBankManualSetDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdBankManualSetDetail record);

    int updateByPrimaryKey(TrdBankManualSetDetail record);
}