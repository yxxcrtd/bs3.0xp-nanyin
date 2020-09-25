package com.jixu.joyin2jixu.dto;

public class SecurityInfoDto {
	
	/**
	 * <p>证券代码/资产代码</p>
	 */
	private String securityid;
	
	/**
	 *  <p>证券名称/资产名称</p>
	 */
	private String securityname;

	public String getSecurityid() {
		return securityid;
	}

	public void setSecurityid(String securityid) {
		this.securityid = securityid;
	}

	public String getSecurityname() {
		return securityname;
	}

	public void setSecurityname(String securityname) {
		this.securityname = securityname;
	}
	
	public SecurityInfoDto(String securityid, String securityname) {
		this.securityid=securityid;
		this.securityname=securityname;
	}
}
