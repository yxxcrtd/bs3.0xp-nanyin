package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.Payhsrcsecoption;
import org.springframework.stereotype.Repository;

@Repository
public interface PayhsrcsecoptionMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(Payhsrcsecoption record);

    int insertSelective(Payhsrcsecoption record);

    int insertBasicByBasicInfo(Payhsrcsecoption record);

    Payhsrcsecoption selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(Payhsrcsecoption record);

    int updateByPrimaryKey(Payhsrcsecoption record);
}