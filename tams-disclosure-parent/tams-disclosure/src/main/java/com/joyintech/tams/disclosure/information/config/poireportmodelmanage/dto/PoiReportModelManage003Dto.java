package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.common.base.entity.DisPrams;

public class PoiReportModelManage003Dto extends DisPrams {

	private static final long serialVersionUID = 6922242739488302153L;

	/**
	 * 报表编号
	 */
	private String tabNum;

	/**
	 * 报表名称
	 */
	private String tabName;

	/**
	 * 模板路径
	 */
	private String tempPath;

	/**
	 * 模板名称
	 */
	private String tempName;

	/**
	 * 查询条件编号
	 */
	private String disId;

	/**
	 * 序号
	 */
	private String disOrder;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 列表展示栏
	 */
	private List<DisPraamsSub> addParam;

	private String pageModel;

	private String liparams;

	private String LAY_TABLE_INDEX;

	public String getLAY_TABLE_INDEX() {
		return LAY_TABLE_INDEX;
	}

	public void setLAY_TABLE_INDEX(String lAY_TABLE_INDEX) {
		LAY_TABLE_INDEX = lAY_TABLE_INDEX;
	}

	public String getLiparams() {
		return liparams;
	}

	public void setLiparams(String liparams) {
		this.liparams = liparams;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<DisPraamsSub> getAddParam() {
		return addParam;
	}

	public void setAddParam(List<DisPraamsSub> addParam) {
		this.addParam = addParam;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTabNum() {
		return tabNum;
	}

	public void setTabNum(String tabNum) {
		this.tabNum = tabNum;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getDisId() {
		return disId;
	}

	public void setDisId(String disId) {
		this.disId = disId;
	}

	public String getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(String disOrder) {
		this.disOrder = disOrder;
	}

}
