package com.joyintech.tams.common.svreport.salecustsvrreportfordeal.dao;

import com.joyintech.tams.common.svreport.salecustsvrreportfordeal.dto.SaleCustSvrreportVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleCustSvrreportVoMapper {

    List<SaleCustSvrreportVo> getSaleCustSvrreportOfFx(@Param("batchTime") Date batchTime);
    
    int deleteSaleCustSvrreportOfFx(@Param("batchTime") Date batchTime);
    
    int insertSaleCustSvrreportHis(@Param("batchTime") Date batchTime);
}