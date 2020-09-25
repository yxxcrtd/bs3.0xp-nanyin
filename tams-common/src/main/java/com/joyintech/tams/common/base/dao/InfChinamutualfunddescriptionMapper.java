package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfChinamutualfunddescription;
import com.joyintech.tams.common.base.entity.InfChinamutualfunddescriptionWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface InfChinamutualfunddescriptionMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InfChinamutualfunddescriptionWithBLOBs record);

    int insertSelective(InfChinamutualfunddescriptionWithBLOBs record);

    int insertBasicByBasicInfo(InfChinamutualfunddescriptionWithBLOBs record);

    InfChinamutualfunddescriptionWithBLOBs selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InfChinamutualfunddescriptionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InfChinamutualfunddescriptionWithBLOBs record);

    int updateByPrimaryKey(InfChinamutualfunddescription record);
}