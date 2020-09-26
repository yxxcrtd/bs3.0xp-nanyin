package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.CMoneyMarketFincome;

@Repository
public interface CMoneyMarketFincomeMapper {
    /**
     * 货币基金净值收益
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
    public int insertBatch(List<CMoneyMarketFincome> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<CMoneyMarketFincome> queryData(@Param("cdate")String cdate);
}