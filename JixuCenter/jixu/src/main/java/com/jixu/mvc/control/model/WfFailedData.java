package com.jixu.mvc.control.model;

import java.util.Date;

/** 
 * @ClassName 		FailedWFData
 * @author  		Pan Wei 
 * @Date 			2016年9月23日 下午10:07:48 	
 * @Description		[]  
 */

public class WfFailedData {
	//"编号", "数据来源", "所属组合", "所在文件", "错误描述", "操作人", "失败时间","是否忽略"
	private int id;
	private String source;
	private String portname;
	private String filename;
	private String errDesc;
	private String msg;
	private String operationor;
	private Date operationTime;
	private boolean ingore;
	
	public WfFailedData(int id, String source,String portname,String filename,
			String errDesc,String msg,String operationor,Date operationTime,boolean ingore) {
		this.setId(id);
		this.setSource(source);
		this.setPortname(portname);
		this.setFilename(filename);
		this.setErrDesc(errDesc);
		this.setMsg(msg);
		this.setOperationor(operationor);
		this.setOperationTime(operationTime);
		this.setIngore(ingore);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getOperationor() {
		return operationor;
	}
	public void setOperationor(String operationor) {
		this.operationor = operationor;
	}
	public boolean isIngore() {
		return ingore;
	}
	public void setIngore(boolean ingore) {
		this.ingore = ingore;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
}
