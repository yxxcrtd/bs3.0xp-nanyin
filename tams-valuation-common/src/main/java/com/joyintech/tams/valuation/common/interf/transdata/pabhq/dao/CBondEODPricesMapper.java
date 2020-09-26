package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondEODPrices;

@Repository
public interface CBondEODPricesMapper {

    /**
     * 中国债券交易所债券行情
     * 删除15天之前的数据
     */
    public int deleteFifthDay(@Param("cdate")String cdate);
    
    /**
     * 中国债券交易所债券行情
     * 删除当天的数据
     */
    public int deleteThisDayDay(@Param("cdate")String cdate);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<CBondEODPrices> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<CBondEODPrices> queryData(@Param("cdate")String cdate);
}