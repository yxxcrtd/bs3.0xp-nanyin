package com.joyintech.tams.svreport.interf.dis.prodcashyield.dao;

import com.joyintech.tams.common.base.entity.RepCashYield;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepCashYieldInterfMapper {

    /**
     * 查询截止上次接入时间的产品费率数据
     * @param batchTime
     * @return
     */
    List<RepCashYield> select(@Param("updatetime") Date updatetime);

}