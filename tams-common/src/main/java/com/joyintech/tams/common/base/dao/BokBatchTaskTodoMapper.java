package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBatchTaskTodoMapper {
    int insert(BokBatchTaskTodo record);

    int insertSelective(BokBatchTaskTodo record);

    int insertBasicByBasicInfo(BokBatchTaskTodo record);
}