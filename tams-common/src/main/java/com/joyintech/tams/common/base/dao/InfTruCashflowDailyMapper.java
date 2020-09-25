package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfTruCashflowDaily;
import org.springframework.stereotype.Repository;

@Repository
public interface InfTruCashflowDailyMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(InfTruCashflowDaily record);

    int insertSelective(InfTruCashflowDaily record);

    int insertBasicByBasicInfo(InfTruCashflowDaily record);

    InfTruCashflowDaily selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(InfTruCashflowDaily record);

    int updateByPrimaryKey(InfTruCashflowDaily record);
}