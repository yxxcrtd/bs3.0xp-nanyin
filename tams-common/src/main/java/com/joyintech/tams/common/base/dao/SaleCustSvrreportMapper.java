package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SaleCustSvrreport;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleCustSvrreportMapper {
    int insert(SaleCustSvrreport record);

    int insertSelective(SaleCustSvrreport record);

    int insertBasicByBasicInfo(SaleCustSvrreport record);
    
    List<SaleCustSvrreport> getSaleCustSvrreportOfFx(@Param("batchTime")Date batchTime);
}