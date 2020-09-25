package com.orm.obj;

/** 
 * @ClassName 		SingleAdapterControl
 * @author  		Pan Wei 
 * @Date 			2019年01月02日 下午11:45:11 	
 * @Description		[记录当前运行的适配器]  
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ADPT_AdapterUniqueRun") 
public class AdapterUniqueRun {
	private int adapterID;
	
	private String opMachineName;
	
	private String startTime;
	
	@Id
	@Column(name="adapterID")
	public int getAdapterID() {
		return adapterID;
	}

	public void setAdapterID(int adapterID) {
		this.adapterID = adapterID;
	}

	@Column(name="opMachineName")
	public String getOpMachineName() {
		return opMachineName;
	}

	public void setOpMachineName(String opMachineName) {
		this.opMachineName = opMachineName;
	}

	@Column(name="startTime")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
