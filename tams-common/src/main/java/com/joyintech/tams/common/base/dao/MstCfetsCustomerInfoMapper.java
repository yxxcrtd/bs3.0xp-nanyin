package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCfetsCustomerInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCfetsCustomerInfoMapper {
    int deleteByPrimaryKey(String partyId);

    int insert(MstCfetsCustomerInfo record);

    int insertSelective(MstCfetsCustomerInfo record);

    int insertBasicByBasicInfo(MstCfetsCustomerInfo record);

    MstCfetsCustomerInfo selectByPrimaryKey(String partyId);

    int updateByPrimaryKeySelective(MstCfetsCustomerInfo record);

    int updateByPrimaryKey(MstCfetsCustomerInfo record);
}