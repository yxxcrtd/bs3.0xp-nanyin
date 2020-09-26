package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CBondAnalysisCNBD;

@Repository
public interface CBondAnalysisCNBDMapper {
	/**
     * 中债登估值
     * 删除15天之前的数据
     */
    public int deleteFifthDay(@Param("cdate")String cdate);
    
    /**
     * 中债登估值
     * 删除当天的数据
     */
    public int deleteThisDayDay(@Param("cdate")String cdate);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<CBondAnalysisCNBD> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<CBondAnalysisCNBD> queryData(@Param("cdate")String cdate);
}