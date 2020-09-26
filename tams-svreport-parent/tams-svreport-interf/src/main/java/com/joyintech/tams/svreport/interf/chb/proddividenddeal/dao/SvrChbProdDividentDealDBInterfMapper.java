package com.joyintech.tams.svreport.interf.chb.proddividenddeal.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdBonusDeal;


@Repository
public interface SvrChbProdDividentDealDBInterfMapper {
	
	/**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<String> getTableColumnList(@Param("tableName") String tableName);

	
	
	
	
    int deleteByUpdateTime();
    
    void  deleteData(ChbPrdBonusDeal  deal);
    
    
    List<ChbPrdBonusDeal>  selectByValuedate(Date interfDate);
    
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbPrdBonusDeal(@Param("newDataList")List<ChbPrdBonusDeal> newDataList);

}
