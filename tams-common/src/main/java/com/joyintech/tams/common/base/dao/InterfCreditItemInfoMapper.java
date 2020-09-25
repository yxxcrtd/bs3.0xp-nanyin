package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCreditItemInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCreditItemInfoMapper {
    int deleteByPrimaryKey(String cashOutNo);

    int insert(InterfCreditItemInfo record);

    int insertSelective(InterfCreditItemInfo record);

    int insertBasicByBasicInfo(InterfCreditItemInfo record);

    InterfCreditItemInfo selectByPrimaryKey(String cashOutNo);

    int updateByPrimaryKeySelective(InterfCreditItemInfo record);

    int updateByPrimaryKey(InterfCreditItemInfo record);
}