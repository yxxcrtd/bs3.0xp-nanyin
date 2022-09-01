package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepRelDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface RepRelDealMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RepRelDeal record);

    int insertSelective(RepRelDeal record);

    int insertBasicByBasicInfo(RepRelDeal record);

    RepRelDeal selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RepRelDeal record);

    int updateByPrimaryKey(RepRelDeal record);
}