package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskOutValtblImportHisMapper {
    int deleteByPrimaryKey(RskOutValtblImportHisKey key);

    int insert(RskOutValtblImportHis record);

    int insertSelective(RskOutValtblImportHis record);

    int insertBasicByBasicInfo(RskOutValtblImportHis record);

    RskOutValtblImportHis selectByPrimaryKey(RskOutValtblImportHisKey key);

    int updateByPrimaryKeySelective(RskOutValtblImportHis record);

    int updateByPrimaryKey(RskOutValtblImportHis record);
}