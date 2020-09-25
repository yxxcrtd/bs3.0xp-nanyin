package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfTask;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfTaskMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(ValInterfTask record);

    int insertSelective(ValInterfTask record);

    int insertBasicByBasicInfo(ValInterfTask record);

    ValInterfTask selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(ValInterfTask record);

    int updateByPrimaryKey(ValInterfTask record);
}