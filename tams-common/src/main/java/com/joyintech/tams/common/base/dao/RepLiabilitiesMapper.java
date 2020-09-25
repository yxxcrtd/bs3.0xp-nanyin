package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepLiabilities;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLiabilitiesMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepLiabilities record);

    int insertSelective(RepLiabilities record);

    int insertBasicByBasicInfo(RepLiabilities record);

    RepLiabilities selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepLiabilities record);

    int updateByPrimaryKey(RepLiabilities record);
}