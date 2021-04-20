package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatus;
import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatusKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrQuoteFinishStatusMapper {
    int deleteByPrimaryKey(EtrQuoteFinishStatusKey key);

    int insert(EtrQuoteFinishStatus record);

    int insertSelective(EtrQuoteFinishStatus record);

    int insertBasicByBasicInfo(EtrQuoteFinishStatus record);

    EtrQuoteFinishStatus selectByPrimaryKey(EtrQuoteFinishStatusKey key);

    int updateByPrimaryKeySelective(EtrQuoteFinishStatus record);

    int updateByPrimaryKey(EtrQuoteFinishStatus record);
}