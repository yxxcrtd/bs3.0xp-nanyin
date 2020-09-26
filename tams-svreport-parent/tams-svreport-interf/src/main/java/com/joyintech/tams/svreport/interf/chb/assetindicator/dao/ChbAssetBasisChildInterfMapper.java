package com.joyintech.tams.svreport.interf.chb.assetindicator.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisDataChild;


@Repository
public interface ChbAssetBasisChildInterfMapper {
	
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
    void deleteData(ChbAssetsBasisDataChild basisData);
    
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbAssetBasisData(@Param("newDataList")List<ChbAssetsBasisDataChild> newDataList);
    
    /**
     * 根据业务联合主键删除
     * @param list
     */
    void deleteBatchList(@Param("dataList") List<ChbAssetsBasisDataChild> list);
    
    /**
     * 查询上游数据库的数据变动日期
     * @param list
     */
	List<Date> isUpdated(Date batchTime);
	
	List<ChbAssetsBasisDataChild> selectByValuedate(String batchTime);
	
	int deleteByUpdateTime();

	List<ChbAssetsBasisDataChild> selectChlBtmByValuedate(String batchTime);
}
