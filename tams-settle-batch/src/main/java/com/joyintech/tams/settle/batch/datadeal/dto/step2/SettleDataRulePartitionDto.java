package com.joyintech.tams.settle.batch.datadeal.dto.step2;

import java.util.Objects;

import com.joyintech.framework.util.StringUtil;

/**
 * 清算数据规则表分组对象Dto（step2 reader用）
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see SettleDataRulePartitionDto
 * @since 1.0
 */
public class SettleDataRulePartitionDto {
    
	// 证券品种
	private String finprodType;
		
	// 清算数据表
	private String tableName;
	
	// 清算数据源文件
	private String dataFile;
	
	public SettleDataRulePartitionDto() {
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SettleDataRulePartitionDto) {
			SettleDataRulePartitionDto settleDataRulePartitionDto = (SettleDataRulePartitionDto)obj;
		    return StringUtil.equals(this.finprodType, settleDataRulePartitionDto.getFinprodType()) &&
		    	   StringUtil.equals(this.tableName, settleDataRulePartitionDto.getTableName()) &&
		    	   StringUtil.equals(this.dataFile, settleDataRulePartitionDto.getDataFile());  
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(finprodType,tableName,dataFile);
	}
}
