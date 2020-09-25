package com.jixu.common.types;

/**
 * 该类的输入，可以转换成json存入数据库，根据输入条件决定了采用哪个总账组
 * @author 桃汰猪
 *
 */

public class AssetCooaMapType {
	/**
	 * 资产类型
	 */
	private String assetType="";
	
	/**
	 * 自定义证券类型编号。上交所普通股，深交所普通股等
	 */
	private int rpSecType=0;
	
	/**
	 * 投资目的
	 */
	private String purpose="";
	
	/**
	 * 多方货币
	 */
	private String longCur="";
	
	/**
	 * 空方货币
	 */
	private String shortCur="";

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public int getRpSecType() {
		return rpSecType;
	}

	public void setRpSecType(int rpSecType) {
		this.rpSecType = rpSecType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getLongCur() {
		return longCur;
	}

	public void setLongCur(String longCur) {
		this.longCur = longCur;
	}

	public String getShortCur() {
		return shortCur;
	}

	public void setShortCur(String shortCur) {
		this.shortCur = shortCur;
	}
	
	@Override
	public boolean equals(Object obj) {
		AssetCooaMapType type=(AssetCooaMapType)obj;
		if(!type.getAssetType().equals(this.assetType))
			return false;
		
		if(type.getRpSecType()!=this.getRpSecType())
			return false;
		
		if(!type.getPurpose().equals(this.getPurpose()))
			return false;
		
		if(type.getLongCur().equals(this.getLongCur()))
			return false;
		
		if(type.getShortCur().equals(this.getShortCur()))
			return false;
		
		return true;
	}
}
