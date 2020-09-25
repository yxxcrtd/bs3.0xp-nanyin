package com.joyintech.tams.invest.risk.valtblimport.dto;


public class SumNameCodeEntry {

	private String sumName;
	private String sumCode;
	private ValDataType nextColType;
	private String tgt_sumName;
	
    /**
     * 获取 tgt_sumName
     * @return tgt_sumName.
     */
    public String getTgt_sumName() {
        return tgt_sumName;
    }

    
    /**
     * 设置 tgt_sumName
     * @param tgt_sumName tgt_sumName
     */
    public void setTgt_sumName(String tgt_sumName) {
        this.tgt_sumName = tgt_sumName;
    }

    public void setNextColType(String sNextColType) {
		if(sNextColType == null) {
			nextColType = ValDataType.NULL;
		} else {
			nextColType = ValDataType.valueOf(sNextColType.toUpperCase());
		}
	}
	
	public String getSumName() {
		return sumName;
	}
	public void setSumName(String sumName) {
		this.sumName = sumName;
	}
	public String getSumCode() {
		return sumCode;
	}
	public void setSumCode(String sumCode) {
		this.sumCode = sumCode;
	}
	public ValDataType getNextColType() {
		return nextColType;
	}
	public void setNextColType(ValDataType nextColType) {
		this.nextColType = nextColType;
	}
}