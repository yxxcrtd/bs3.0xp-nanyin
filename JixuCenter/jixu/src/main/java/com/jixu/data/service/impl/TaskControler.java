package com.jixu.data.service.impl;

import java.sql.Date;

import com.jixu.data.service.OutDataInterface;

/** 
 * @ClassName 		TaskControler
 * @author  		Pan Wei 
 * @Date 			2017年10月31日 下午1:18:13 	
 * @Description		[prototype,每次都是新建对象,多线程,并行运行]  
 */
public class TaskControler implements Runnable{
	
	private String portnum;
	
	private Date exeDate;
	
	private OutDataInterface exeBean;
	
	public TaskControler(OutDataInterface exeBean, String portnum, Date exeDate) {
		this.setExeBean(exeBean);
		this.setPortnum(portnum);
		this.setExeDate(exeDate);
	}
	
	/**
	 * 获取 portnum
	 */
	
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public Date getExeDate() {
		return exeDate;
	}

	public void setExeDate(Date exeDate) {
		this.exeDate = exeDate;
	}
	
	public OutDataInterface getExeBean() {
		return exeBean;
	}
	
	public void setExeBean(OutDataInterface exeBean) {
		this.exeBean = exeBean;
	}

	public void run() {
		getExeBean().sendResults(getPortnum(), getExeDate());
	}
}
