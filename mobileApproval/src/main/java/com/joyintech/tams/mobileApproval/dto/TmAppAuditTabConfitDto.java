package com.joyintech.tams.mobileApproval.dto;

public class TmAppAuditTabConfitDto {
	private String id;		
	private String businessType;	//	业务类型
	private String tabName;	//	导航栏名称（展示tab）
	private String tabId;	//		tabid
	private String tmEffectflag;
	
	public String getTmEffectflag() {
		return tmEffectflag;
	}
	public void setTmEffectflag(String tmEffectflag) {
		this.tmEffectflag = tmEffectflag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public TmAppAuditTabConfitDto() {
		super();
	}

}
