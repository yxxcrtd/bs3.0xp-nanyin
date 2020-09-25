/*
 * 文件名：FinProductFileInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dto;

/**
 * @author 何阳阳
 * @version 1.0 2018年10月29日
 * @see FinProductFileInfoDto
 * @since 1.0
 */
public class FinProductFileInfoDto {
	/**
	 * 金融产品代码
	 */
	private String finprodId;

	/**
	 * 披露文档代码
	 */
	private String revealFileId;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件模板类型
	 */
	private String fileType;

	/**
	 * 备注
	 */
	private String remark;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getRevealFileId() {
		return revealFileId;
	}

	public void setRevealFileId(String revealFileId) {
		this.revealFileId = revealFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
