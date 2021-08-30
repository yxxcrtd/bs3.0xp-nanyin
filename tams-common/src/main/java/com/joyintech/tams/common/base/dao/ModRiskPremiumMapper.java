package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModRiskPremium;
import org.springframework.stereotype.Repository;

@Repository
public interface ModRiskPremiumMapper {
    int insert(ModRiskPremium record);

    int insertSelective(ModRiskPremium record);

    int insertBasicByBasicInfo(ModRiskPremium record);
}