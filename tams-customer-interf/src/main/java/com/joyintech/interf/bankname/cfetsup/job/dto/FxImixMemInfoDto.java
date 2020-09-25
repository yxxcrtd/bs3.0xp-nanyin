package com.joyintech.interf.bankname.cfetsup.job.dto;

import java.io.Serializable;


/**
 * Table SA_IMIX_MEM_INFO
 * PK: Field id Column SEQNO
 * 	
 * @hibernate.class
 *  table="SA_IMIX_MEM_INFO"
 */

public class FxImixMemInfoDto   implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 流水号 */
	private String id;
	/**交易成员ID */
	private String jycyid;
	/**机构代码 */
	private String jgdm;
	/**交易成员全称 */
	private String jycyqc;
	/**中文简称 */
	private String zwjc;
	/**英文全称 */
	private String ywqc;
	/**英文简称 */
	private String ywjc;
	/** 创建人*/
	private String creatorid;
	/** 创建时间*/
	private java.util.Date createdtime;
	/**更新时间 */
	private java.util.Date lastmodifiedtime;
	/** 更新人*/
	private String lastmodifierid;

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


	public String getJgdm () {
		return jgdm;
	}

	public void setJgdm (String jgdm) {
		this.jgdm = jgdm;
	}


	public String getJycyqc () {
		return jycyqc;
	}

	public void setJycyqc (String jycyqc) {
		this.jycyqc = jycyqc;
	}


	public String getZwjc () {
		return zwjc;
	}

	public void setZwjc (String zwjc) {
		this.zwjc = zwjc;
	}


	public String getYwqc () {
		return ywqc;
	}

	public void setYwqc (String ywqc) {
		this.ywqc = ywqc;
	}


	public String getYwjc () {
		return ywjc;
	}

	public void setYwjc (String ywjc) {
		this.ywjc = ywjc;
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


	public java.util.Date getLastmodifiedtime () {
		return lastmodifiedtime;
	}

	public void setLastmodifiedtime (java.util.Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}


	public String getLastmodifierid () {
		return lastmodifierid;
	}

	public void setLastmodifierid (String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}





}