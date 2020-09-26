package com.joyintech.tams.valuation.interf.valuation.sztinterf.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 返回明细信息
 * @author hudongyang
 *
 */
public class SztResultDetailModel extends BaseEntity{
	
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 组合代码
	 */
	private String fundId;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 报表类型
	 */
	private String reportType;
	
	/**
	 * 开始日期
	 */
	private String beginDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 公司代码
	 */
	private String deptCode;
	
	/**
	 * 证书代码
	 */
	private String certId;
	
	/**
	 * 业务流水号
	 */
	private String serialNo;
	
	/**
	 * 对账详细
	 */
	private List<SztResultDetailXmlModel> reconciliaResults;
	
	/**
	 * 获取组合代码
	 * @return
	 */
    public String getFundId() {
		return fundId;
	}

    /**
     * 设置组合代码
     * @param fundId
     */
	public void setFundId(String fundId) {
		this.fundId = fundId == null ? null : fundId.trim();
	}
	
	/**
	 * 获取文件类型
	 * @return
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 设置文件类型
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}
	
	/**
	 * 获取报表类型
	 * @return
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * 设置报表类型
	 * @param reportType
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType == null ? null : reportType.trim();
	}

	/**
	 * 获取开始日期
	 * @return
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置开始日期
	 * @param beginDate
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate == null ? null : beginDate.trim();
	}

	/**
	 * 获取结束日期
	 * @return
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate == null ? null : endDate.trim();
	}

	/**
	 * 获取公司代码
	 * @return
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 设置公司代码
	 * @param deptCode
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode == null ? null : deptCode.trim();
	}

	/**
	 * 获取证书代码
	 * @return
	 */
	public String getCertId() {
		return certId;
	}

	/**
	 * 设置证书代码
	 * @param certId
	 */
	public void setCertId(String certId) {
		this.certId = certId == null ? null : certId.trim();
	}

	/**
	 * 获取业务流水号
	 * @return
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * 设置业务流水号
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo == null ? null : serialNo.trim();
	}

	/**
	 * 获取对账明细集合
	 * @return
	 */
	public List<SztResultDetailXmlModel> getReconciliaResults() {
		return reconciliaResults;
	}

	/**
	 * 设置对账明细集合
	 * @param reconciliaResults
	 */
	public void setReconciliaResults(List<SztResultDetailXmlModel> reconciliaResults) {
		this.reconciliaResults = reconciliaResults;
	}
}
