package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepTrustInvestMode;
import org.springframework.stereotype.Repository;

@Repository
public interface RepTrustInvestModeMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepTrustInvestMode record);

    int insertSelective(RepTrustInvestMode record);

    int insertBasicByBasicInfo(RepTrustInvestMode record);

    RepTrustInvestMode selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepTrustInvestMode record);

    int updateByPrimaryKey(RepTrustInvestMode record);
}