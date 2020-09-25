package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinOpenPlanInfo extends BaseEntity {


	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -7743137046485036915L;

	private String openDateStr;
    
	private String openDateEnd;
    
	private String openVdate;
    
	private String openNdate;

	public String getOpenDateStr() {
		return openDateStr;
	}

	public void setOpenDateStr(String openDateStr) {
		this.openDateStr = openDateStr;
	}

	public String getOpenDateEnd() {
		return openDateEnd;
	}

	public void setOpenDateEnd(String openDateEnd) {
		this.openDateEnd = openDateEnd;
	}

	public String getOpenVdate() {
		return openVdate;
	}

	public void setOpenVdate(String openVdate) {
		this.openVdate = openVdate;
	}

	public String getOpenNdate() {
		return openNdate;
	}

	public void setOpenNdate(String openNdate) {
		this.openNdate = openNdate;
	}
    
    
}