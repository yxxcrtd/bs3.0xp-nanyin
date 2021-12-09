package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataTotalStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataTotalStatusMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(SettleTransDataTotalStatus record);

    int insertSelective(SettleTransDataTotalStatus record);

    int insertBasicByBasicInfo(SettleTransDataTotalStatus record);

    SettleTransDataTotalStatus selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SettleTransDataTotalStatus record);

    int updateByPrimaryKey(SettleTransDataTotalStatus record);
}