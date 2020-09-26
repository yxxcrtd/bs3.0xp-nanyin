package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondBenchmark;

@Repository
public interface CBondBenchmarkMapper {
    /**
     * 法定存贷款利率
     * 删除15天之前的数据
     */
    public int deleteFifthDay(@Param("cdate")String cdate);
    
    /**
     * 法定存贷款利率
     * 删除当天的数据
     */
    public int deleteThisDayDay(@Param("cdate")String cdate);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<CBondBenchmark> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<CBondBenchmark> queryData(@Param("cdate")String cdate);
}