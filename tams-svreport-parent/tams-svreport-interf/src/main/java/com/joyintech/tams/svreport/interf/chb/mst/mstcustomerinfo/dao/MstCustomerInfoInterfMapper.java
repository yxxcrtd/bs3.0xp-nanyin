package com.joyintech.tams.svreport.interf.chb.mst.mstcustomerinfo.dao;

import com.joyintech.tams.svreport.interf.chb.mst.mstcustomerinfo.dto.MstCustomerInfoInterfDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MstCustomerInfoInterfMapper {

    /**
     * 查询截止上次接入时间的市场机构
     * @param batchTime
     * @return
     */
    List<MstCustomerInfoInterfDto> select(@Param("updatetime") Date updatetime);

}