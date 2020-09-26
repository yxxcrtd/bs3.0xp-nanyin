package com.joyintech.tams.valuation.common.interf.transdata.pabhq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity.ValPayhsrcsecoption;

@Repository
public interface ValPayhsrcsecoptionMapper {
    /**
     * 债券行权信息
     * 删除15天之前的数据
     */
    public int deleteFifthDay(@Param("cdate")String cdate);
    
    /**
     * 债券行权信息
     * 删除当天的数据
     */
    public int deleteThisDayDay(@Param("cdate")String cdate);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int insertBatch(List<ValPayhsrcsecoption> list);
    
    /**
     * 查询当天的数据
     * @param cdate
     * @return
     */
    public List<ValPayhsrcsecoption> queryData(@Param("cdate")String cdate);
}