package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.ShiborPrices;

@Repository
public interface ShiborPricesMapper {
    /**
     * SHIRBO行情
     * 删除15天之前的数据
     */
    public int deleteFifthDay(@Param("cdate")String cdate);
    
    /**
     * SHIRBO行情
     * 删除当天的数据
     */
    public int deleteThisDayDay(@Param("cdate")String cdate);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<ShiborPrices> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<ShiborPrices> queryData(@Param("cdate")String cdate);
}