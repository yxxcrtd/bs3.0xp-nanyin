package com.joyintech.tams.svreport.chbsd.employeeinfo.dto;

import com.joyintech.tams.common.base.entity.ChbEmployeeInfoSub;
import com.joyintech.tams.report.common.base.annotate.TableName;

@TableName("CHB_EMPLOYEE_INFO_SUB")
public class SdEmployeeInfoSvrDto extends ChbEmployeeInfoSub {

	private static final long serialVersionUID = 2900887227223990019L;
	/**//**
     * 从业人员类型
     *//*
    private String employeeType;
    *//**
     * 性别
     *//*
    private String employeeSex;
    *//**
     * 所属总行或分支行
     *//*
    private String belongsBank;
    *//**
     * 所属区域
     *//*
    private String belongsPlace;
    *//**
     * 学历
     *//*
    private String education;

    *//**
     * 学位
     *//*
    private String academicDegree;
    *//**
     * 专业技术职称
     *//*
    private String professionalTitle;
    *//**
     * 理财专业证书
     *//*
    private String finProfessionalCrtf;
    *//**
     * 登记业务分类
     *//*
    private String regBusiType;

    *//**
     * 登记人员类别
     *//*
    private String regEmployeeType;

	public String getEmployeeType() {
		if(employeeType != null){
			employeeType = employeeType.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, employeeType));
			}
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeSex() {
		if(employeeSex != null){
			employeeSex = employeeSex.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, employeeSex));
			}
		return employeeSex;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getBelongsBank() {
		if(belongsBank != null){
			belongsBank = belongsBank.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, belongsBank));
			}
		return belongsBank;
	}

	public void setBelongsBank(String belongsBank) {
		this.belongsBank = belongsBank;
	}

	public String getBelongsPlace() {
		if(belongsPlace != null){
			belongsPlace = belongsPlace.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, belongsPlace));
			}
		return belongsPlace;
	}

	public void setBelongsPlace(String belongsPlace) {
		this.belongsPlace = belongsPlace;
	}

	public String getEducation() {
		if(education != null){
			education = education.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, education));
			}
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAcademicDegree() {
		if(academicDegree != null){
			academicDegree = academicDegree.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, academicDegree));
			}
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getProfessionalTitle() {
		if(professionalTitle != null){
			professionalTitle = professionalTitle.trim() + " "
						+ SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, professionalTitle));
			}
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getFinProfessionalCrtf() {
		if(finProfessionalCrtf != null){
		    List<String> crtfs = StringUtil.splitToList(GlobalConstant.COMMA, finProfessionalCrtf);
			finProfessionalCrtf = "";
			for(String crtf : crtfs){
				finProfessionalCrtf = finProfessionalCrtf + "," + crtf + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.理财专业证书, crtf);
			}
			finProfessionalCrtf = finProfessionalCrtf.substring(1);
		}
		return finProfessionalCrtf;
	}

	public void setFinProfessionalCrtf(String finProfessionalCrtf) {
		this.finProfessionalCrtf = finProfessionalCrtf;
	}

	public String getRegBusiType() {
		if(regBusiType != null){
		    List<String> rbts = StringUtil.splitToList(GlobalConstant.COMMA, regBusiType);
			regBusiType = "";
			for(String rbt : rbts){
				regBusiType = regBusiType + "," + rbt + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.登记业务分类, rbt);
			}
			regBusiType = regBusiType.substring(1);
		}
		return regBusiType;
	}

	public void setRegBusiType(String regBusiType) {
		this.regBusiType = regBusiType;
	}

	public String getRegEmployeeType() {
		if(regEmployeeType != null){
		    List<String> rets = StringUtil.splitToList(GlobalConstant.COMMA, regEmployeeType);
			regEmployeeType = "";
			for(String ret : rets){
				regEmployeeType = regEmployeeType + "," + ret + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.登记业务分类, ret);
			}
			regEmployeeType = regEmployeeType.substring(1);
		}
		return regEmployeeType;
	}

	public void setRegEmployeeType(String regEmployeeType) {
		this.regEmployeeType = regEmployeeType;
	}
    */

}
