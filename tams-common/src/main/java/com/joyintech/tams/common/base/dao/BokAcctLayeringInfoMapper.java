package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctLayeringInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAcctLayeringInfoMapper {
    int deleteByPrimaryKey(BokAcctLayeringInfoKey key);

    int insert(BokAcctLayeringInfo record);

    int insertSelective(BokAcctLayeringInfo record);

    int insertBasicByBasicInfo(BokAcctLayeringInfo record);

    BokAcctLayeringInfo selectByPrimaryKey(BokAcctLayeringInfoKey key);

    int updateByPrimaryKeySelective(BokAcctLayeringInfo record);

    int updateByPrimaryKey(BokAcctLayeringInfo record);
}