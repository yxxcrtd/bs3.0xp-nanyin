package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepLiabilitiesOther;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLiabilitiesOtherMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepLiabilitiesOther record);

    int insertSelective(RepLiabilitiesOther record);

    int insertBasicByBasicInfo(RepLiabilitiesOther record);

    RepLiabilitiesOther selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepLiabilitiesOther record);

    int updateByPrimaryKey(RepLiabilitiesOther record);
}