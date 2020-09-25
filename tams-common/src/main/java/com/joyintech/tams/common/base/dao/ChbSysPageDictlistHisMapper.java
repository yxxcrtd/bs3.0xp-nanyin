package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysPageDictlistHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysPageDictlistHisMapper {
    int insert(ChbSysPageDictlistHis record);

    int insertSelective(ChbSysPageDictlistHis record);

    int insertHisByBasicInfo(ChbSysPageDictlistHis record);

    int insertHisByWorkflowId(ChbSysPageDictlistHis record);
}