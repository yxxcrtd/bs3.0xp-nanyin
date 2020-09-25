package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAuthorizeMng;
import org.springframework.stereotype.Repository;

@Repository
public interface RskAuthorizeMngMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(RskAuthorizeMng record);

    int insertSelective(RskAuthorizeMng record);

    int insertBasicByBasicInfo(RskAuthorizeMng record);

    RskAuthorizeMng selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(RskAuthorizeMng record);

    int updateByPrimaryKey(RskAuthorizeMng record);
}