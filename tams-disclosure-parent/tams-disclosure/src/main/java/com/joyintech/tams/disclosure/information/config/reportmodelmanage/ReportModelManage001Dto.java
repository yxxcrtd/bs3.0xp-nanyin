/*
 * 文件名：ReportModelManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportmodelmanage;

import java.math.BigDecimal;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;

public class ReportModelManage001Dto extends BaseEntity{
	private Map<String, Object> param;
	/**
	 * 创建时间
	 */
	private String cdate;
	/**
	 * 更新时间
	 */
	private String udate;
	/**
	 * 模板英文名
	 */
	private String code;

	/**
	 * 模板中文名
	 */
	private String name;

	/**
	 * 模板子路径
	 */
	private String temLoad;
	

	private String url;

	private String repId;
	private String keyId;
	private String fileType;
	private String updateDate;
	private String creationDate;
	private String description;
	private String label;
	private String uri;
	private BigDecimal rowNo;
	public BigDecimal getRowNo() {
		return rowNo;
	}

	public void setRowNo(BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 获取 code
	 * 
	 * @return code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 code
	 * 
	 * @param code
	 *            code
	 */
	public void setCode(String code) {
		this.code = code;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRepId() {
		return repId;
	}

	public void setRepId(String repId) {
		this.repId = repId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getUdate() {
		return udate;
	}

	public void setUdate(String udate) {
		this.udate = udate;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}


}
