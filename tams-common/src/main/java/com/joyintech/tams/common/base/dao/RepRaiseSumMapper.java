package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import org.springframework.stereotype.Repository;

@Repository
public interface RepRaiseSumMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepRaiseSum record);

    int insertSelective(RepRaiseSum record);

    int insertBasicByBasicInfo(RepRaiseSum record);

    RepRaiseSum selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepRaiseSum record);

    int updateByPrimaryKey(RepRaiseSum record);
}