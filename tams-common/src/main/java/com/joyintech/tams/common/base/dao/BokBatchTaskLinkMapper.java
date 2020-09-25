package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBatchTaskLink;
import com.joyintech.tams.common.base.entity.BokBatchTaskLinkKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBatchTaskLinkMapper {
    int deleteByPrimaryKey(BokBatchTaskLinkKey key);

    int insert(BokBatchTaskLink record);

    int insertSelective(BokBatchTaskLink record);

    int insertBasicByBasicInfo(BokBatchTaskLink record);

    BokBatchTaskLink selectByPrimaryKey(BokBatchTaskLinkKey key);

    int updateByPrimaryKeySelective(BokBatchTaskLink record);

    int updateByPrimaryKey(BokBatchTaskLink record);
}