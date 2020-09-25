package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RskDimEntrustInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskDimEntrustInfo record);

    int insertSelective(RskDimEntrustInfo record);

    int insertBasicByBasicInfo(RskDimEntrustInfo record);

    RskDimEntrustInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskDimEntrustInfo record);

    int updateByPrimaryKey(RskDimEntrustInfo record);
}