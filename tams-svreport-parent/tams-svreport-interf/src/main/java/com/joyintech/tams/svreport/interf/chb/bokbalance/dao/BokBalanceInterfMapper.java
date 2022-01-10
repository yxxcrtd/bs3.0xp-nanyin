package com.joyintech.tams.svreport.interf.chb.bokbalance.dao;

import com.joyintech.tams.common.base.entity.BokBalance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BokBalanceInterfMapper {

    /**
     * 查询截止上次接入时间的科目余额
     * @param batchTime
     * @return
     */
    List<BokBalance> select(@Param("updatetime") Date updatetime);

}