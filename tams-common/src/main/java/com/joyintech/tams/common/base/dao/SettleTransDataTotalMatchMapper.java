package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataTotalMatch;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalMatchKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataTotalMatchMapper {
    int deleteByPrimaryKey(SettleTransDataTotalMatchKey key);

    int insert(SettleTransDataTotalMatch record);

    int insertSelective(SettleTransDataTotalMatch record);

    int insertBasicByBasicInfo(SettleTransDataTotalMatch record);

    SettleTransDataTotalMatch selectByPrimaryKey(SettleTransDataTotalMatchKey key);

    int updateByPrimaryKeySelective(SettleTransDataTotalMatch record);

    int updateByPrimaryKey(SettleTransDataTotalMatch record);
}