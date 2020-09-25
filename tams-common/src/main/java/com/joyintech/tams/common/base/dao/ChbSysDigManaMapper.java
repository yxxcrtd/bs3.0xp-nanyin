package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysDigMana;
import com.joyintech.tams.common.base.entity.ChbSysDigManaKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysDigManaMapper {
    int deleteByPrimaryKey(ChbSysDigManaKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysDigMana record);

    int insertSelective(ChbSysDigMana record);

    int insertBasicByBasicInfo(ChbSysDigMana record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysDigMana selectByPrimaryKey(ChbSysDigManaKey key);

    int updateByPrimaryKeySelective(ChbSysDigMana record);

    int updateByWorkflowIdSelective(ChbSysDigMana record);

    int updateByWorkflowId(ChbSysDigMana record);

    int updateByPrimaryKey(ChbSysDigMana record);
}