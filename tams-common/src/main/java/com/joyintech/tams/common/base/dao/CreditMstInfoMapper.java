package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditMstInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditMstInfoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditMstInfo record);

    int insertSelective(CreditMstInfo record);

    int insertBasicByBasicInfo(CreditMstInfo record);

    CreditMstInfo selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditMstInfo record);

    int updateByPrimaryKey(CreditMstInfo record);
}