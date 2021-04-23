package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCfetsCustomerTmp;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCfetsCustomerTmpMapper {
    int deleteByPrimaryKey(String partyId);

    int insert(InterfCfetsCustomerTmp record);

    int insertSelective(InterfCfetsCustomerTmp record);

    int insertBasicByBasicInfo(InterfCfetsCustomerTmp record);

    InterfCfetsCustomerTmp selectByPrimaryKey(String partyId);

    int updateByPrimaryKeySelective(InterfCfetsCustomerTmp record);

    int updateByPrimaryKey(InterfCfetsCustomerTmp record);
}