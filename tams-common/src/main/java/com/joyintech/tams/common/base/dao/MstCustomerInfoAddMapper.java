package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCustomerInfoAddMapper {
    int insert(MstCustomerInfoAddWithBLOBs record);

    int insertSelective(MstCustomerInfoAddWithBLOBs record);

    int insertBasicByBasicInfo(MstCustomerInfoAddWithBLOBs record);
}