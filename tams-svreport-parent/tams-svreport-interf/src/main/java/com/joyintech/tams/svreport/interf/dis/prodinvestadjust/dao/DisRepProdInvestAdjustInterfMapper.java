package com.joyintech.tams.svreport.interf.dis.prodinvestadjust.dao;

import com.joyintech.tams.common.base.entity.RepProdInvestAdjust;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DisRepProdInvestAdjustInterfMapper {

    /**
     * 查询产品资产调整数据
     * @param batchTime
     * @return
     */
    List<RepProdInvestAdjust> select(@Param("updatetime") Date updatetime);

}