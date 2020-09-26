package com.joyintech.tams.svreport.interf.chb.assetbasis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisData;


@Repository
public interface ChbAssetBasisInterfMapper {
	
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
    void deleteData(ChbAssetsBasisData basisData);
    /**
     * 根据valueDate删除当日全量数据
     * @param basisData
     */
    void deleteDataNoBtm(ChbAssetsBasisData basisData);
    
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbAssetBasisData(@Param("newDataList")List<ChbAssetsBasisData> newDataList);
    /**
     * 增量插入数据
     * @param newDataList
     */
    void insertChbAssetBasisDataNoBtm(@Param("newDataList")List<ChbAssetsBasisData> newDataList);
    
    /**
     * 根据业务联合主键删除
     * @param list
     */
    void deleteBatchList(@Param("dataList") List<ChbAssetsBasisData> list);
    
    /**
     * 查询上游数据库的数据变动日期
     * @param list
     */
	List<Date> isUpdated(Date batchTime);
	
	List<ChbAssetsBasisData> selectByValuedate(String batchTime);
	
	int deleteByUpdateTime();
	
	int deleteNoBtmByUpdateTime();

	List<ChbAssetsBasisData> selectChlBtmByValuedate(String batchTime);
	
	/**
	 * 
	 * @Description 查询所有有效的货币基金资产代码  
	 * @Author      高健  
	 * @Date        2019年12月27日 下午1:38:43  
	 * @param @return 参数  
	 * @return List<String> 返回类型   
	 * @throws
	 */
	List<String> queryAllImfAssetIds();
	
	/**
	 * 查询所有有效的行权债资产代码
	 * @return
	 */
	List<String> queryAllSecXQAssetIds();
	/**
	 * 根据行权债id和日期查询最近的行权日
	 * @param secid
	 * @param batchTime
	 * @return
	 */
	Date queryExerciseDate(@Param("secid") String secid,@Param("valueDate") Date valueDate);
	
}
