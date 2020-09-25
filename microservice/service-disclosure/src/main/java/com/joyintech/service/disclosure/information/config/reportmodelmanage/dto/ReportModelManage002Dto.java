/*
 * 文件名：ReportModelManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.reportmodelmanage.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

public class ReportModelManage002Dto extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -459083868293354979L;
	private List<ReportModelManage002Dto> addParam=new ArrayList<>();
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 模板中文描述
	 */
	private String repId;
	/**
	 * 模板中文描述
	 */
	private String reportDesc;
	/**
	 * 报表名称
	 */
	private String repName;
	/**
	 * 文件夹
	 */
	private String folder;
	/**
	 * 参数编号
	 */
	private int paramNum;
	/**
	 * 参数名称
	 */
	private String paramName;
	/**
	 * 类型
	 */
	private String paramType;
	/**
	 * 参数中文描述
	 */
	private String name;
	/**
	 * 模板子路径
	 */
	private String temLoad;
	/**
	 * 主键Id
	 */
	private String keyId;
	/**
	 * 报表参数
	 */
	private String reportParams;
	/**
	 * 数据源
	 */
	private String dataSource;
	/**
	 * 生成方式
	 */
	private String createWay;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 报表仓库
	 */
	private String reportWarehouse;
	
	public String getReportWarehouse() {
		return reportWarehouse;
	}

	public void setReportWarehouse(String reportWarehouse) {
		this.reportWarehouse = reportWarehouse;
	}

	/**
	 * 获取 name
	 * 
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 temLoad
	 * 
	 * @return temLoad.
	 */
	public String getTemLoad() {
		return temLoad;
	}

	/**
	 * 设置 temLoad
	 * 
	 * @param temLoad
	 *            temLoad
	 */
	public void setTemLoad(String temLoad) {
		this.temLoad = temLoad;
	}

	/**
	 * 获取 keyId
	 * 
	 * @return keyId.
	 */
	public String getKeyId() {
		return keyId;
	}
	/**
	 * 设置 keyId
	 * 
	 * @param keyId
	 *            keyId
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	/**
	 * 获取 paramNum
	 * 
	 * @return paramNum.
	 */
	public int getParamNum() {
		return paramNum;
	}
	/**
	 * 设置 paramNum
	 * 
	 * @param paramNum
	 *            paramNum
	 */
	public void setParamNum(int paramNum) {
		this.paramNum = paramNum;
	}
	/**
	 * 获取 paramName
	 * 
	 * @return paramName.
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * 设置 paramName
	 * 
	 * @param paramName
	 *            paramName
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * 获取 paramNum
	 * 
	 * @return paramNum.
	 */
	public String getParamType() {
		return paramType;
	}
	/**
	 * 设置 paramType
	 * 
	 * @param paramType
	 *            paramType
	 */
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	/**
	 * 获取 addParam
	 * 
	 * @return addParam.
	 */
	public List<ReportModelManage002Dto> getAddParam() {
		return addParam;
	}
	/**
	 * 设置 addParam
	 * 
	 * @param addParam
	 *            addParam
	 */
	public void setAddParam(List<ReportModelManage002Dto> addParam) {
		this.addParam = addParam;
	}
	/**
	 * 获取 reportDesc
	 * 
	 * @return reportDesc.
	 */
	public String getReportDesc() {
		return reportDesc;
	}
	/**
	 * 设置 reportDesc
	 * 
	 * @param reportDesc
	 *            reportDesc
	 */
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}
	/**
	 * 获取 repName
	 * 
	 * @return repName.
	 */
	public String getRepName() {
		return repName;
	}
	/**
	 * 设置 repName
	 * 
	 * @param repName
	 *            repName
	 */
	public void setRepName(String repName) {
		this.repName = repName;
	}
	/**
	 * 获取 folder
	 * 
	 * @return folder.
	 */
	public String getFolder() {
		return folder;
	}
	/**
	 * 设置 file
	 * 
	 * @param folder
	 *            folder
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getReportParams() {
		return reportParams;
	}

	public void setReportParams(String reportParams) {
		this.reportParams = reportParams;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getCreateWay() {
		return createWay;
	}

	public void setCreateWay(String createWay) {
		this.createWay = createWay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	

	

}
