package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecAcct;
import com.joyintech.tams.common.base.entity.PtlSecAcctKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecAcctMapper {
    int deleteByPrimaryKey(PtlSecAcctKey key);

    int insert(PtlSecAcct record);

    int insertSelective(PtlSecAcct record);

    int insertBasicByBasicInfo(PtlSecAcct record);

    PtlSecAcct selectByPrimaryKey(PtlSecAcctKey key);

    int updateByPrimaryKeySelective(PtlSecAcct record);

    int updateByPrimaryKey(PtlSecAcct record);
}