package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskFaciDeduceRuleCla;
import com.joyintech.tams.common.base.entity.RskFaciDeduceRuleClaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskFaciDeduceRuleClaMapper {
    int deleteByPrimaryKey(RskFaciDeduceRuleClaKey key);

    int insert(RskFaciDeduceRuleCla record);

    int insertSelective(RskFaciDeduceRuleCla record);

    int insertBasicByBasicInfo(RskFaciDeduceRuleCla record);

    RskFaciDeduceRuleCla selectByPrimaryKey(RskFaciDeduceRuleClaKey key);

    int updateByPrimaryKeySelective(RskFaciDeduceRuleCla record);

    int updateByPrimaryKey(RskFaciDeduceRuleCla record);
}