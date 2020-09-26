package com.joyintech.tams.svreport.pbc.classic.data.dto;

public class PbcDatfileDetailRelation extends PbcDatfileDetailRelationKey {
	/**
	 * 文件编号
	 */
	private String fileid;

	/**
	 * 明细文件代码
	 */
	private String detailcode;

	/**
	 * 文件存放路径
	 */
	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getDetailcode() {
		return detailcode;
	}

	public void setDetailcode(String detailcode) {
		this.detailcode = detailcode;
	}

}