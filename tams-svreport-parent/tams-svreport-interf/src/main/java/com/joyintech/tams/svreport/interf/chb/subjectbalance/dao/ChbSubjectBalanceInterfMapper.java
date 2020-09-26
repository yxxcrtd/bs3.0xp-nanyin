package com.joyintech.tams.svreport.interf.chb.subjectbalance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.svreport.interf.chb.subjectbalance.dto.BokBalance;


@Repository
public interface ChbSubjectBalanceInterfMapper {
	
	/**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<String> getTableColumnList(@Param("tableName") String tableName);
    
    /**
     * 根据valueDate删除当日全量数据
     * @param basisData
     */
    void deleteData(String batchTime);
    
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbSubjectBalanceData(@Param("newDataList")List<BokBalance> newDataList);
    
    void insert(BokBalance item);
    
    /**
     * 根据业务联合主键删除
     * @param list
     */
    void deleteBatchList(@Param("dataList") List<BokBalance> list);
    
    void mergeData(@Param("newDataList")List<BokBalance> newDataList);
}
