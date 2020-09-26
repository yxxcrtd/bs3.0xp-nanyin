package com.joyintech.tams.svreport.interf.chb.feebasis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisDataChild;
import com.joyintech.tams.common.base.entity.ChbPrdfeeBasisData;


@Repository
public interface SvrChbPrdFeeBasisDBInterfMapper {
	
	/**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<String> getTableColumnList(@Param("tableName") String tableName);

	
    /**
     * 查询上游数据库的数据变动日期
     * @param list
     */
	List<Date> isUpdated(Date batchTime);
	
	
	List<ChbPrdfeeBasisData>  selectByValuedate(String batchTime);
	
	void  deleteData(ChbPrdfeeBasisData  data);
	
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbPrdfeeBasisData(@Param("newDataList")List<ChbPrdfeeBasisData> newDataList);

    int deleteByUpdateTime();
}
