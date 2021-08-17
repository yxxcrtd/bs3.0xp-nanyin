package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValtblImportDateTrace;
import org.springframework.stereotype.Repository;

@Repository
public interface ValtblImportDateTraceMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ValtblImportDateTrace record);

    int insertSelective(ValtblImportDateTrace record);

    int insertBasicByBasicInfo(ValtblImportDateTrace record);

    ValtblImportDateTrace selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ValtblImportDateTrace record);

    int updateByPrimaryKey(ValtblImportDateTrace record);
}