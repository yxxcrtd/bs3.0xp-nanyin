package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubinfoBatchLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSubinfoBatchLogMapper {
    int deleteByPrimaryKey(String batchExeId);

    int insert(ChbSubinfoBatchLog record);

    int insertSelective(ChbSubinfoBatchLog record);

    int insertBasicByBasicInfo(ChbSubinfoBatchLog record);

    ChbSubinfoBatchLog selectByPrimaryKey(String batchExeId);

    int updateByPrimaryKeySelective(ChbSubinfoBatchLog record);

    int updateByPrimaryKey(ChbSubinfoBatchLog record);
}