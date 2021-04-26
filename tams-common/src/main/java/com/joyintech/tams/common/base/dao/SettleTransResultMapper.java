package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransResult;
import com.joyintech.tams.common.base.entity.SettleTransResultWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransResultMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SettleTransResultWithBLOBs record);

    int insertSelective(SettleTransResultWithBLOBs record);

    int insertBasicByBasicInfo(SettleTransResultWithBLOBs record);

    SettleTransResultWithBLOBs selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SettleTransResultWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SettleTransResultWithBLOBs record);

    int updateByPrimaryKey(SettleTransResult record);
}