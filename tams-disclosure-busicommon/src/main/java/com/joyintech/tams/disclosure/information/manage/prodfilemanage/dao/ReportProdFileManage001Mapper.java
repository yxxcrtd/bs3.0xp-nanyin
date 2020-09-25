package com.joyintech.tams.disclosure.information.manage.prodfilemanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.RepInvassetkindDto;
import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDto;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;

/**
 * 产品文件管理Mapper层
 * --微服务
 * @author hpp
 *
 */
@Repository
public interface ReportProdFileManage001Mapper {
	
	/**
	 * 分页查询条件-产品代码下拉框
	 * --产品文件产品要素表
	 * @param paraMap
	 * @return
	 */
	List<SelectOptionDto> getPrdInfoSelectPF(Map<String, Object> paraMap);


	/**
	 * 分页查询条件-产品系列下拉框
	 * --产品文件产品要素表
	 * @param paraMap
	 * @return
	 */
	List<SelectOptionDto> getProdSeriesSelectPF(Map<String, Object> paraMap);

	/**
	 * 分页查询事件
	 * @param param
	 * @return
	 */
	List<ReportProdFileManage001Dto> searchPage(Map<String, Object> param);
	
	/**
	 * 根据主键更新产品文件产品要素表
	 * --批量逻辑删除
	 * @param statusD
	 * @param keyList
	 * @return
	 */
	int updateByKeyList(List<ReportProdFileManage001Dto> keyList);
	
	/**
	 * 查询产品代码是否已存在
	 * @param prodId
	 * @return
	 */
	int checkProdInfoExistPF(String prodId);
	
	/**
	 * 查询费用信息
	 * @param map 参数
	 * @return 费用信息下拉框
	 */
	List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);
	
	/**
	 * 查询费用信息列表
	 * @param prodId
	 * @return
	 */
	List<ReportProdFileManage001Dto> queryFeeInfoPF(String prodId);
	
	/**
	 * 查询投资比例区间信息列表
	 * @param prodId
	 * @return
	 */
	List<ReportProdFileManage001Dto> queryInvestProporPF(String prodId);
	
	/**
	 * 插入产品文件管理基本信息表
	 * @param record
	 * @return
	 */
	 int insertPF(ReportProdFileManage001Dto dto);
	 /**
	  * 更新产品文件管理基本信息表
	  * @param dto
	  * @return
	  */
	 int updateByPrimaryKeyPF(ReportProdFileManage001Dto dto);
	 /**
	  * 根据主键查询
	  * @param produuid
	  * @return
	  */
	 ReportProdFileManage001Dto selectByPrimaryKeyPF(String produuid);
	 
	 
	 /**
	  * 批量插入产品文件管理费用信息表中
	  * @param dataList
	  */
    void insertFeeInfoBatchPF(@Param("dataList") List<RepProdFileFeeInfoDto> dataList);
	 
    /**
	 * 批量删除产品文件管理费用信息表数据
	 * @param subList list
	 * @return
	 */
	int deleteFeeInfoBatchPF(@Param("subList") List<ReportProdFileManage001Dto> subList);
	
	/**
	  * 批量插入投资资产种类表中
	  * @param dataList
	  */
   void insertInvestKindBatchPF(@Param("dataList") List<RepInvassetkindDto> dataList);
	 
   /**
	 * 批量删除投资资产种类表数据
	 * @param subList list
	 * @return
	 */
	int deleteInvestKindBatchPF(@Param("subList") List<ReportProdFileManage001Dto> subList);
	
}
