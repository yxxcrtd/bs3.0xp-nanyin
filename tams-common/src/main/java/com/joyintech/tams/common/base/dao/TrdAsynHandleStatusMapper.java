package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdAsynHandleStatus;
import com.joyintech.tams.common.base.entity.TrdAsynHandleStatusKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdAsynHandleStatusMapper {
    int deleteByPrimaryKey(TrdAsynHandleStatusKey key);

    int insert(TrdAsynHandleStatus record);

    int insertSelective(TrdAsynHandleStatus record);

    int insertBasicByBasicInfo(TrdAsynHandleStatus record);

    TrdAsynHandleStatus selectByPrimaryKey(TrdAsynHandleStatusKey key);

    int updateByPrimaryKeySelective(TrdAsynHandleStatus record);

    int updateByPrimaryKey(TrdAsynHandleStatus record);
}