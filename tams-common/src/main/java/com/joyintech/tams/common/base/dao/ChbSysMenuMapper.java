package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysMenu;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysMenu record);

    int insertSelective(ChbSysMenu record);

    int insertBasicByBasicInfo(ChbSysMenu record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(ChbSysMenu record);

    int updateByWorkflowIdSelective(ChbSysMenu record);

    int updateByWorkflowId(ChbSysMenu record);

    int updateByPrimaryKey(ChbSysMenu record);
}