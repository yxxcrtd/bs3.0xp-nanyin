package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdBonusRegister;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdBonusRegisterMapper {
    int insert(RepProdBonusRegister record);

    int insertSelective(RepProdBonusRegister record);

    int insertBasicByBasicInfo(RepProdBonusRegister record);
}