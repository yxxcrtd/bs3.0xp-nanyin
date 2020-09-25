package com.jixu.common.types;

/** 
 * @ClassName 		NewFundType
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午1:46:13 	
 * @Description		[]  
 */

public enum NewFundType {
	newFund(0, "新基金上线配置"), copyOldFund(1, "老基金迁移配置");
	
	private int index;
	private String name;

	private NewFundType(int index, String name) {
		this.name=name;
		this.index=index;
	}

	public static String getName(int index) {
		for (NewFundType c : NewFundType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
