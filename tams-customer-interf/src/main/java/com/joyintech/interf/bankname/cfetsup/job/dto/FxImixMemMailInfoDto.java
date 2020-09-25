package com.joyintech.interf.bankname.cfetsup.job.dto;

import java.io.Serializable;


/**
 * Table SA_IMIX_MEM_MAIL_INFO
 * PK: Field id Column SEQNO
 * 	
 * @hibernate.class
 *  table="SA_IMIX_MEM_MAIL_INFO"
 */

public class FxImixMemMailInfoDto   implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	private String jycyid;
	private String jycy;
	private String jyyid;
	private String jyyxm;
	private String lxdh;
	private String cz;
	private String email;
	private String msn;
	private String txdz;
	private String yzbm;
	private String creatorid;
	private java.util.Date createdtime;
	private String lastmodifierid;
	private java.util.Date lastmodifiedtime;
	private String cjyyid;
	private String cjyyxm;

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

	public String getJycyid () {
		return jycyid;
	}

	public void setJycyid (String jycyid) {
		this.jycyid = jycyid;
	}


	public String getJycy () {
		return jycy;
	}

	public void setJycy (String jycy) {
		this.jycy = jycy;
	}


	public String getJyyid () {
		return jyyid;
	}

	public void setJyyid (String jyyid) {
		this.jyyid = jyyid;
	}


	public String getJyyxm () {
		return jyyxm;
	}

	public void setJyyxm (String jyyxm) {
		this.jyyxm = jyyxm;
	}


	public String getLxdh () {
		return lxdh;
	}

	public void setLxdh (String lxdh) {
		this.lxdh = lxdh;
	}


	public String getCz () {
		return cz;
	}

	public void setCz (String cz) {
		this.cz = cz;
	}


	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}


	public String getMsn () {
		return msn;
	}

	public void setMsn (String msn) {
		this.msn = msn;
	}


	public String getTxdz () {
		return txdz;
	}

	public void setTxdz (String txdz) {
		this.txdz = txdz;
	}


	public String getYzbm () {
		return yzbm;
	}

	public void setYzbm (String yzbm) {
		this.yzbm = yzbm;
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

	/**
	 * @return the cjyyid
	 */
	public String getCjyyid() {
		return cjyyid;
	}

	/**
	 * @param cjyyid the cjyyid to set
	 */
	public void setCjyyid(String cjyyid) {
		this.cjyyid = cjyyid;
	}

	/**
	 * @return the cjyyxm
	 */
	public String getCjyyxm() {
		return cjyyxm;
	}

	/**
	 * @param cjyyxm the cjyyxm to set
	 */
	public void setCjyyxm(String cjyyxm) {
		this.cjyyxm = cjyyxm;
	}





}