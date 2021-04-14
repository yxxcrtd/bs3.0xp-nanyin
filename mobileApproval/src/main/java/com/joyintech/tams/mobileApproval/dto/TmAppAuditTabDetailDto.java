package com.joyintech.tams.mobileApproval.dto;

public class TmAppAuditTabDetailDto {
	private String id;	
	private String tabConfigId;
	private String key;
	private String title;
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
	public String getTabConfigId() {
		return tabConfigId;
	}
	public void setTabConfigId(String tabConfigId) {
		this.tabConfigId = tabConfigId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TmAppAuditTabDetailDto() {
		super();
	}

}
