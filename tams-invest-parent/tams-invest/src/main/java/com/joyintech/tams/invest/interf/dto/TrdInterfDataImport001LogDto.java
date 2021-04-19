package com.joyintech.tams.invest.interf.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 展示日志结构表
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see TrdInterfDataImport001LogDto
 * @since 1.0
 */
public class TrdInterfDataImport001LogDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String busidate;

	private String predate;

	private String preseqno;

	private String optdate;
	
	private String sttime;
	
	private String edtime;
	
	private String status;
	
	private String rtname;
	
	private String runmsg;
	
	private BigDecimal runtime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPredate() {
		return predate;
	}

	public void setPredate(String predate) {
		this.predate = predate;
	}

	public String getPreseqno() {
		return preseqno;
	}

	public void setPreseqno(String preseqno) {
		this.preseqno = preseqno;
	}

	public String getOptdate() {
		return optdate;
	}

	public void setOptdate(String optdate) {
		this.optdate = optdate;
	}

	public String getSttime() {
		return sttime;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public String getEdtime() {
		return edtime;
	}

	public void setEdtime(String edtime) {
		this.edtime = edtime;
	}

	public String getBusidate() {
		return busidate;
	}

	public void setBusidate(String busidate) {
		this.busidate = busidate;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRtname() {
		return rtname;
	}

	public void setRtname(String rtname) {
		this.rtname = rtname;
	}

	public String getRunmsg() {
		return runmsg;
	}

	public void setRunmsg(String runmsg) {
		this.runmsg = runmsg;
	}

	public BigDecimal getRuntime() {
		return runtime;
	}

	public void setRuntime(BigDecimal runtime) {
		this.runtime = runtime;
	}
}
