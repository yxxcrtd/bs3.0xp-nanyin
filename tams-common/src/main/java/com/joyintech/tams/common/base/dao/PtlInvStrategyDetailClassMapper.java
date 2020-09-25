package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClass;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClassKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlInvStrategyDetailClassMapper {
    int deleteByPrimaryKey(PtlInvStrategyDetailClassKey key);

    int insert(PtlInvStrategyDetailClass record);

    int insertSelective(PtlInvStrategyDetailClass record);

    int insertBasicByBasicInfo(PtlInvStrategyDetailClass record);

    PtlInvStrategyDetailClass selectByPrimaryKey(PtlInvStrategyDetailClassKey key);

    int updateByPrimaryKeySelective(PtlInvStrategyDetailClass record);

    int updateByPrimaryKey(PtlInvStrategyDetailClass record);
}