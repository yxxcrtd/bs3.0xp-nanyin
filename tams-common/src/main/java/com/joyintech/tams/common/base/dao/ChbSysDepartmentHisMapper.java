package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysDepartmentHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysDepartmentHisMapper {
    int insert(ChbSysDepartmentHis record);

    int insertSelective(ChbSysDepartmentHis record);

    int insertHisByBasicInfo(ChbSysDepartmentHis record);

    int insertHisByWorkflowId(ChbSysDepartmentHis record);
}