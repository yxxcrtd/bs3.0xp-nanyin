package com.joyintech.tams.svreport.interf.dis.prodbonusregister.dao;

import com.joyintech.tams.common.base.entity.RepProdBonusRegister;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProdBonusInterfMapper {

    /**
     * 查询截止上次接入时间的分红登记
     * @param batchTime
     * @return
     */
    List<RepProdBonusRegister> select(@Param("updatetime") Date updatetime);

}