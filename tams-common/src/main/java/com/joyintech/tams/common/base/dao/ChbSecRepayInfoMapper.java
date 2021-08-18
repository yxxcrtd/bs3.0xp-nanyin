package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSecRepayInfo;
import com.joyintech.tams.common.base.entity.ChbSecRepayInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSecRepayInfoMapper {
    int deleteByPrimaryKey(ChbSecRepayInfoKey key);

    int insert(ChbSecRepayInfo record);

    int insertSelective(ChbSecRepayInfo record);

    int insertBasicByBasicInfo(ChbSecRepayInfo record);

    ChbSecRepayInfo selectByPrimaryKey(ChbSecRepayInfoKey key);

    int updateByPrimaryKeySelective(ChbSecRepayInfo record);

    int updateByPrimaryKey(ChbSecRepayInfo record);
}