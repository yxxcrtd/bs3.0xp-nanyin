package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;


import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 评级数据信息DTO
 * @author zhangfei
 *
 */
public class ModRateDataDto extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 评级主体
	 */
	private String ocode;
	/**
	 * 评级类型
	 */
	private String otype;
	/**
	 * 评级机构
	 */
	private String gradeorgid;
	/**
	 * 评级日期
	 */
	private Date gradedate;
	/**
	 * 评级结果
	 */
	private String graderesult;

	public String getOcode() {
		return ocode;
	}

	public String getOtype() {
		return otype;
	}

	public String getGradeorgid() {
		return gradeorgid;
	}

	public Date getGradedate() {
		return gradedate;
	}

	public String getGraderesult() {
		return graderesult;
	}

	public void setOcode(String ocode) {
		this.ocode = ocode;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	public void setGradeorgid(String gradeorgid) {
		this.gradeorgid = gradeorgid;
	}

	public void setGradedate(Date gradedate) {
		this.gradedate = gradedate;
	}

	public void setGraderesult(String graderesult) {
		this.graderesult = graderesult;
	}
	
	
}
