/*
 * 
 */
package com.joyintech.service.disclosure.information.manage.workmanage.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年11月12日
 * @see ReportWorkManage001Dto
 * @since 1.0
 */
@PageId("ReportWorkManage001")
public class ReportWorkManage001Dto extends RepJob implements ProcessEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	private String reportName;

	private String loopTypeName;

	private String outTypeName;

	private String data;

	private String elementId;

	private String outType;
	/**
	 * 上次执行时间
	 */
	private String execDate;
	
	/**
	 * 批量删除集合
	 */
	private List<ReportWorkManage001Dto> selectedItem;

	/**
	 * 预计执行时间
	 */
	private String preExecDate;

	public String getPreExecDate() {
		return preExecDate;
	}

	public void setPreExecDate(String preExecDate) {
		this.preExecDate = preExecDate;
	}

	public String getExecDate() {
		return execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getOutType() {
		return outType;
	}

	/**
	 * 获取 reportName
	 * 
	 * @return reportName.
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * 设置 reportName
	 * 
	 * @param reportName
	 *            reportName
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * 获取 loopTypeName
	 * 
	 * @return loopTypeName.
	 */
	public String getLoopTypeName() {
		return loopTypeName;
	}

	/**
	 * 设置 loopTypeName
	 * 
	 * @param loopTypeName
	 *            loopTypeName
	 */
	public void setLoopTypeName(String loopTypeName) {
		this.loopTypeName = loopTypeName;
	}

	/**
	 * 获取 outTypeName
	 * 
	 * @return outTypeName.
	 */
	public String getOutTypeName() {
		return outTypeName;
	}

	/**
	 * 设置 outTypeName
	 * 
	 * @param outTypeName
	 *            outTypeName
	 */
	public void setOutTypeName(String outTypeName) {
		this.outTypeName = outTypeName;
	}

	/**
	 * 获取 data
	 * 
	 * @return data.
	 */
	public String getData() {
		return data;
	}

	/**
	 * 设置 data
	 * 
	 * @param data
	 *            data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 获取 elementId
	 * 
	 * @return elementId.
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * 设置 elementId
	 * 
	 * @param elementId
	 *            elementId
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public List<ReportWorkManage001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ReportWorkManage001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	
}
