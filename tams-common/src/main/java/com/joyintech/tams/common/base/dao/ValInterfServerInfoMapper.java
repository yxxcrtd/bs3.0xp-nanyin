package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfServerInfo;
import com.joyintech.tams.common.base.entity.ValInterfServerInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfServerInfoMapper {
    int deleteByPrimaryKey(ValInterfServerInfoKey key);

    int insert(ValInterfServerInfo record);

    int insertSelective(ValInterfServerInfo record);

    int insertBasicByBasicInfo(ValInterfServerInfo record);

    ValInterfServerInfo selectByPrimaryKey(ValInterfServerInfoKey key);

    int updateByPrimaryKeySelective(ValInterfServerInfo record);

    int updateByPrimaryKey(ValInterfServerInfo record);
}