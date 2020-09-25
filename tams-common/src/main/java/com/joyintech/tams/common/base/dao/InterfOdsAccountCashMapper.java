package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOdsAccountCash;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOdsAccountCashMapper {
    int insert(InterfOdsAccountCash record);

    int insertSelective(InterfOdsAccountCash record);

    int insertBasicByBasicInfo(InterfOdsAccountCash record);
}