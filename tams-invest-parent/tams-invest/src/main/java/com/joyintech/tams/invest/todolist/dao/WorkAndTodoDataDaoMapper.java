package com.joyintech.tams.invest.todolist.dao;

import com.joyintech.tams.invest.todolist.dto.WorkFlowPersonalDto;

import java.util.List;
import java.util.Map;

public interface WorkAndTodoDataDaoMapper {

    List<WorkFlowPersonalDto> searchTodoList(Map<String, Object> paraMap);

    List<WorkFlowPersonalDto> searchMyWorkList(Map<String, Object> paraMap);
}
