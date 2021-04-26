package com.joyintech.tams.settle.batch.datadeal.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.settle.batch.datadeal.dto.step2.StlDownDataDealOperatorBaseDto;

/**
 * 数据清算批处理主处理参数DTO
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see StlDownDataDealOperatorDto
 * @since 1.0
 */
public class StlDownDataDealOperatorDto extends NeedOperateEntity {
	
	// 证券品种
	private String finprodType;
	
	// 清算数据表
	private String tableName;
	
	// 清算数据源文件
	private String dataFile;
	
	// 证券账户
	private String secAcctId;
	
	// 组合代码List
	private List<String> portfolioIdList;
	
	// 清算数据DTO
	private StlDownDataDealOperatorBaseDto stlDownDataDealOperatorBaseDto;
	
	// 执行日期
	private Date dealDate;
	
	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public List<String> getPortfolioIdList() {
		return portfolioIdList;
	}

	public void setPortfolioIdList(List<String> portfolioIdList) {
		this.portfolioIdList = portfolioIdList;
	}

	public StlDownDataDealOperatorBaseDto getStlDownDataDealOperatorBaseDto() {
		return stlDownDataDealOperatorBaseDto;
	}

	public void setStlDownDataDealOperatorBaseDto(StlDownDataDealOperatorBaseDto stlDownDataDealOperatorBaseDto) {
		this.stlDownDataDealOperatorBaseDto = stlDownDataDealOperatorBaseDto;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
}
