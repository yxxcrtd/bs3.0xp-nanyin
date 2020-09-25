package com.joyintech.interf.bankname.cfetsup.job.dto;

import java.io.Serializable;


public class FxJgzcgxDto implements Serializable {
	/** 标识 */
	private static final long serialVersionUID = 1L;
	/** 流水号 */
	private String seqno;
	/** 主机构名称 */
	private String zjgmc;
	/** 主机构6位码 */
	private String zjglwm;
	/** 从机构名称 */
	private String cjgmc;
	/** 从机构6位码 */
	private String cjglwm;
	/** 主关系类型名称 */
	private String zgxlxmc;
	/** 从关系类型名称 */
	private String cgxlxmc;
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getZjgmc() {
		return zjgmc;
	}
	public void setZjgmc(String zjgmc) {
		this.zjgmc = zjgmc;
	}
	public String getZjglwm() {
		return zjglwm;
	}
	public void setZjglwm(String zjglwm) {
		this.zjglwm = zjglwm;
	}
	public String getCjgmc() {
		return cjgmc;
	}
	public void setCjgmc(String cjgmc) {
		this.cjgmc = cjgmc;
	}
	public String getCjglwm() {
		return cjglwm;
	}
	public void setCjglwm(String cjglwm) {
		this.cjglwm = cjglwm;
	}
	public String getZgxlxmc() {
		return zgxlxmc;
	}
	public void setZgxlxmc(String zgxlxmc) {
		this.zgxlxmc = zgxlxmc;
	}
	public String getCgxlxmc() {
		return cgxlxmc;
	}
	public void setCgxlxmc(String cgxlxmc) {
		this.cgxlxmc = cgxlxmc;
	}

}
