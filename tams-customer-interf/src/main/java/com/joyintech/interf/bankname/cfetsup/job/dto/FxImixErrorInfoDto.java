package com.joyintech.interf.bankname.cfetsup.job.dto;

import java.io.Serializable;

/**
 * Table SA_IMIX_ERROR_INFO
 * PK: Field id Column SEQNO
 * 	
 * @hibernate.class
 *  table="SA_IMIX_ERROR_INFO"
 */

public class FxImixErrorInfoDto   implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String marketindicator;
	private String applerrorcode;
	private String applerrordesc;
	private String creatorid;
	private java.util.Date createdtime;
	private String lastmodifierid;
	private java.util.Date lastmodifiedtime;

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}
	
	public String getObjectId() {
		if (id != null) {
			return id.toString();
		}
		return null;
	}

	public String getMarketindicator () {
		return marketindicator;
	}

	public void setMarketindicator (String marketindicator) {
		this.marketindicator = marketindicator;
	}


	public String getApplerrorcode () {
		return applerrorcode;
	}

	public void setApplerrorcode (String applerrorcode) {
		this.applerrorcode = applerrorcode;
	}


	public String getApplerrordesc () {
		return applerrordesc;
	}

	public void setApplerrordesc (String applerrordesc) {
		this.applerrordesc = applerrordesc;
	}


	public String getCreatorid () {
		return creatorid;
	}

	public void setCreatorid (String creatorid) {
		this.creatorid = creatorid;
	}


	public java.util.Date getCreatedtime () {
		return createdtime;
	}

	public void setCreatedtime (java.util.Date createdtime) {
		this.createdtime = createdtime;
	}


	public String getLastmodifierid () {
		return lastmodifierid;
	}

	public void setLastmodifierid (String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}


	public java.util.Date getLastmodifiedtime () {
		return lastmodifiedtime;
	}

	public void setLastmodifiedtime (java.util.Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}





}