package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCounterAcct;
import com.joyintech.tams.common.base.entity.MstCounterAcctKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCounterAcctMapper {
    int deleteByPrimaryKey(MstCounterAcctKey key);

    int insert(MstCounterAcct record);

    int insertSelective(MstCounterAcct record);

    int insertBasicByBasicInfo(MstCounterAcct record);

    MstCounterAcct selectByPrimaryKey(MstCounterAcctKey key);

    int updateByPrimaryKeySelective(MstCounterAcct record);

    int updateByPrimaryKey(MstCounterAcct record);
}