package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValTempColCheckInfo;
import com.joyintech.tams.common.base.entity.ValTempColCheckInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValTempColCheckInfoMapper {
    int deleteByPrimaryKey(ValTempColCheckInfoKey key);

    int insert(ValTempColCheckInfo record);

    int insertSelective(ValTempColCheckInfo record);

    int insertBasicByBasicInfo(ValTempColCheckInfo record);

    ValTempColCheckInfo selectByPrimaryKey(ValTempColCheckInfoKey key);

    int updateByPrimaryKeySelective(ValTempColCheckInfo record);

    int updateByPrimaryKey(ValTempColCheckInfo record);
}