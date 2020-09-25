package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.tams.common.base.entity.RskIsupTrialClsKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupTrialClsMapper {
    int deleteByPrimaryKey(RskIsupTrialClsKey key);

    int insert(RskIsupTrialCls record);

    int insertSelective(RskIsupTrialCls record);

    int insertBasicByBasicInfo(RskIsupTrialCls record);

    RskIsupTrialCls selectByPrimaryKey(RskIsupTrialClsKey key);

    int updateByPrimaryKeySelective(RskIsupTrialCls record);

    int updateByPrimaryKey(RskIsupTrialCls record);
}