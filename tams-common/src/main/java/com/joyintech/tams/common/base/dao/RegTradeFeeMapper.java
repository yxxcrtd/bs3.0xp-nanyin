package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegTradeFee;
import com.joyintech.tams.common.base.entity.RegTradeFeeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RegTradeFeeMapper {
    int deleteByPrimaryKey(RegTradeFeeKey key);

    int insert(RegTradeFee record);

    int insertSelective(RegTradeFee record);

    int insertBasicByBasicInfo(RegTradeFee record);

    RegTradeFee selectByPrimaryKey(RegTradeFeeKey key);

    int updateByPrimaryKeySelective(RegTradeFee record);

    int updateByPrimaryKey(RegTradeFee record);
}