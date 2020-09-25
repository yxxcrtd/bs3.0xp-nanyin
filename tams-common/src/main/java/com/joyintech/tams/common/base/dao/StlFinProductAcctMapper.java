package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.StlFinProductAcctKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlFinProductAcctMapper {
    int deleteByPrimaryKey(StlFinProductAcctKey key);

    int insert(StlFinProductAcct record);

    int insertSelective(StlFinProductAcct record);

    int insertBasicByBasicInfo(StlFinProductAcct record);

    StlFinProductAcct selectByPrimaryKey(StlFinProductAcctKey key);

    int updateByPrimaryKeySelective(StlFinProductAcct record);

    int updateByPrimaryKey(StlFinProductAcct record);
}