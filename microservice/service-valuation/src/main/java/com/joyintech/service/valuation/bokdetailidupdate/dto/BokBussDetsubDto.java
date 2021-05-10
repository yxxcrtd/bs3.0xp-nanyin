package com.joyintech.service.valuation.bokdetailidupdate.dto;

import com.joyintech.tams.common.base.entity.BokBussDetsub;

public class BokBussDetsubDto extends BokBussDetsub{

	//科目号
	private String subjectNo;
	//科目名称
	private String subjectName;
	
	private String bokDetailId;
	
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getBokDetailId() {
		return bokDetailId;
	}
	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
	
}
