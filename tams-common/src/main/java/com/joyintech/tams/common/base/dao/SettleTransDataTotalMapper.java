package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataTotalMapper {
    int deleteByPrimaryKey(String seqId);

    int insert(SettleTransDataTotal record);

    int insertSelective(SettleTransDataTotal record);

    int insertBasicByBasicInfo(SettleTransDataTotal record);

    SettleTransDataTotal selectByPrimaryKey(String seqId);

    int updateByPrimaryKeySelective(SettleTransDataTotal record);

    int updateByPrimaryKey(SettleTransDataTotal record);
}