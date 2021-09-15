package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValtblImportHisTraceMapper {
    int insert(RskOutValtblImportHis record);
}