/*
 * 文件名：ReportModelManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.disclosure.information.manage.workrule.dto.ReportRule001Dto;

public class PoiReportModelManage001Dto extends BaseEntity{
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
     * 参数条件
     */
    private String tempPrams;
    
    
    /**
   	 * 批量删除集合
   	 */
   	private List<PoiReportModelManage001Dto> selectedItem;
   	
   /**
    * 删除方式
    * 
    */
    private  String  operateType;
    
    
    
    
	public String getOperateType() {
	return operateType;
}

public void setOperateType(String operateType) {
	this.operateType = operateType;
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

	public String getTempPrams() {
		return tempPrams;
	}

	public void setTempPrams(String tempPrams) {
		this.tempPrams = tempPrams;
	}

	public List<PoiReportModelManage001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<PoiReportModelManage001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
    
    
}
