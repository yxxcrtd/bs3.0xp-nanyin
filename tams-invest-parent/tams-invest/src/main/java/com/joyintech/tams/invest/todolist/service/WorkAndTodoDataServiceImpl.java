package com.joyintech.tams.invest.todolist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.system.main.service.WorkAndTodoDataService;
import com.joyintech.tams.invest.todolist.dao.WorkAndTodoDataDaoMapper;
import com.joyintech.tams.invest.todolist.dto.WorkFlowPersonalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * wj
 * 2021-04-07
 */
@Service
public class WorkAndTodoDataServiceImpl extends BaseService implements WorkAndTodoDataService<WorkFlowPersonalDto>{

    @Autowired
    private WorkAndTodoDataDaoMapper workAndTodoDataDaoMapper;

    @Override
    public List<WorkFlowPersonalDto> searchTodoList(Map<String, Object> paraMap) {
         return  workAndTodoDataDaoMapper.searchTodoList(paraMap);
    }

    @Override
    public Page<WorkFlowPersonalDto> searchMyWorkList(Map<String, Object> paraMap) {
         return super.queryForPage(() ->workAndTodoDataDaoMapper.searchMyWorkList(paraMap));
    }
}
