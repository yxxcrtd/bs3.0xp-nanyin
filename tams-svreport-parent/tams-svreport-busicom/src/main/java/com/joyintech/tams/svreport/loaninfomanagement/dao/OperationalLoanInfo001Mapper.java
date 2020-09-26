package com.joyintech.tams.svreport.loaninfomanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;

/**
 * 贷款明细信息维护 服务dao
 * @author qiangchengxi
 *
 */
@Repository
public interface OperationalLoanInfo001Mapper {
	
	/**
	 * 根据主键更新数据状态
	 * @param dto
	 * @return
	 */
	int updateStatus(OperationalLoanInfo001Dto dto);
	/**
	 * 根据主键查询资产收益权贷款明细基本信息表数据
	 * @param assetUuid
	 * @return
	 */
	OperationalLoanInfo001Dto selectByPrimaryKey(String assetUuid);
	
	/**
	 * 查询产品资产代码
	 * @return
	 */
	List<SelectOptionDto> queryPbcAssetCode(Map<String, Object> param);
	String queryPbcAssetType(String assetCode);
	/**
	 * 插入资产收益权贷款明细基本信息表
	 * @param dto
	 * @return
	 */
	int insertLoanInfo(OperationalLoanInfo001Dto dto);
	/**
	 * 更新资产收益权贷款明细基本信息表
	 * @param dto
	 * @return
	 */
	int updateByPrimaryKey(OperationalLoanInfo001Dto dto);
	
	/**
	 * 查询资产代码是否已存在
	 * @param assetCode
	 * @return
	 */
	int checkAssetCodeExist(String assetCode);
	
	/**
	 * 根据计划名称查询计划id以及待维护表ID
	 * @param pid
	 * @return
	 */
	OperationalLoanInfo001Dto queryPlanInfo(String pid);
	
	/**
     * 省市县联动
     */
    List<SelectOptionDto> getAddressSelectOption(Map<String, Object> param);
}
