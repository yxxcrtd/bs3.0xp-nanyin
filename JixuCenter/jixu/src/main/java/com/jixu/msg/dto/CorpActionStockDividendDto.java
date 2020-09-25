package com.jixu.msg.dto;

import com.jixu.common.valid.IsDateYYYYMMDDJixu;
import com.jixu.common.valid.IsNumberJixu;
import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;

public class CorpActionStockDividendDto extends BaseMsgDto{
	
	private static final long serialVersionUID = 4401955489903439982L;

	private int id;
	
	/**
	 * 资产类型
	 */
	private String assetType;
	
	/**
	 * 证券ID
	 */
	@NotEmptyJixu(keymsg="证券代码")
	private String secid;
	
	/**
	 * 公司行为类型
	 */
	private int corpActionType;
	
	/**
	 * 股权登记日
	 */
	@IsDateYYYYMMDDJixu(keymsg="股利登记日")
	private String recordDate;
	
	/**
	 * 除权除息日
	 */
	@IsDateYYYYMMDDJixu(keymsg="除权除息日")
	private String exDividentDate;
	
	/**
	 * 分红比例
	 */
	@IsNumberJixu(keymsg="分红比例")
	private String alRate;

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getSecid() {
		return secid;
	}

	public void setSecid(String secid) {
		this.secid = secid;
	}

	public int getCorpActionType() {
		return corpActionType;
	}

	public void setCorpActionType(int corpActionType) {
		this.corpActionType = corpActionType;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getExDividentDate() {
		return exDividentDate;
	}

	public void setExDividentDate(String exDividentDate) {
		this.exDividentDate = exDividentDate;
	}

	public String getAlRate() {
		return alRate;
	}

	public void setAlRate(String alRate) {
		this.alRate = alRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
