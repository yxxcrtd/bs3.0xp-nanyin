package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdInvestAdjust;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdInvestAdjustMapper {
    int insert(RepProdInvestAdjust record);

    int insertSelective(RepProdInvestAdjust record);

    int insertBasicByBasicInfo(RepProdInvestAdjust record);
}