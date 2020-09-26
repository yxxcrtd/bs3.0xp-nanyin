package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfsubject.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class ValSztInterfSubject001Dto extends BaseEntity implements ITableRow{


	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 483155189731781687L;

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
	 * 组合代码
	 */
	private String fundId;
	
	/**
	 * 产品名称
	 */
	private String finprodName;
	
	/**
	 * 科目代码
	 */
	private String fAcctCode;
	
	/**
	 * 科目名称
	 */
	private String fAcctName;
	
	/**
	 * 科目级别
	 */
	private String fAcctLevel;
	
	/**
	 * 上级科目代码
	 */
	private String fAcctParent;
	
	/**
	 * 是否明细科目
	 */
	private String fAcctDetail;
	
	/**
	 * 科目类别
	 */
	private String fAcctClass;
	
	/**
	 * 余额方向
	 */
	private String fBalDC;
	
	/**
	 * 报文状态
	 */
	private String xmlStatus;
	
	/**
	 * 报文状态字符串
	 */
	private String xmlStatusString;
	
	/**
	 * 接口类型
	 */
	private String interType;
	
	/**
	 * 接口类型字符串
	 */
	private String interTypeString;
	
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
	 * 获取产品名称
	 * @return
	 */
	public String getFinprodName() {
		return finprodName;
	}

	/**
	 * 设置产品名称
	 * @param finprodName
	 */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName == null ? null : finprodName.trim();
	}
	
	/**
	 * 获取科目代码
	 * @return
	 */
	public String getfAcctCode() {
		return fAcctCode;
	}

	/**
	 * 设置科目代码
	 * @param fAcctCode
	 */
	public void setfAcctCode(String fAcctCode) {
		this.fAcctCode = fAcctCode == null ? null : fAcctCode.trim();
	}

	/**
	 * 获取科目名称
	 * @return
	 */
	public String getfAcctName() {
		return fAcctName;
	}

	/**
	 * 设置科目名称
	 * @param fAcctName
	 */
	public void setfAcctName(String fAcctName) {
		this.fAcctName = fAcctName;
	}

	/**
	 * 获取科目级别
	 * @return
	 */
	public String getfAcctLevel() {
		return fAcctLevel;
	}

	/**
	 * 设置科目级别
	 * @param fAcctLevel
	 */
	public void setfAcctLevel(String fAcctLevel) {
		this.fAcctLevel = fAcctLevel == null ? null : fAcctLevel.trim();
	}

	/**
	 * 获取上级科目代码
	 * @return
	 */
	public String getfAcctParent() {
		return fAcctParent;
	}

	/**
	 * 设置上级科目代码
	 * @param fAcctParent
	 */
	public void setfAcctParent(String fAcctParent) {
		this.fAcctParent = fAcctParent == null ? null : fAcctParent.trim();
	}

	/**
	 * 获取是否明细科目
	 * @return
	 */
	public String getfAcctDetail() {
		return fAcctDetail;
	}

	/**
	 * 设置是否明细科目
	 * @param fAcctDetail
	 */
	public void setfAcctDetail(String fAcctDetail) {
		this.fAcctDetail = fAcctDetail == null ? null : fAcctDetail.trim();
	}

	/**
	 * 获取科目类别
	 * @return
	 */
	public String getfAcctClass() {
		return fAcctClass;
	}

	/**
	 * 设置科目类别
	 * @param fAcctClass
	 */
	public void setfAcctClass(String fAcctClass) {
		this.fAcctClass = fAcctClass == null ? null : fAcctClass.trim();
	}

	/**
	 * 获取余额方向
	 * @return
	 */
	public String getfBalDC() {
		return fBalDC;
	}

	/**
	 * 设置余额方向
	 * @param fBalDC
	 */
	public void setfBalDC(String fBalDC) {
		this.fBalDC = fBalDC == null ? null : fBalDC.trim();
	}

	/**
     * 获取接口类型
     * @return
     */
    public String getInterType() {
		return interType;
	}

	/**
     * 设置接口类型
     * @param interType
     */
	public void setInterType(String interType) {
		this.interType = interType == null ? null : interType.trim();
	}

    /**
     * 获取报文状态
     * @return
     */
    public String getXmlStatus() {
		return xmlStatus;
	}

    /**
     * 设置报文状态
     * @param xmlStatus
     */
	public void setXmlStatus(String xmlStatus) {
		this.xmlStatus = xmlStatus;
	}

	/**
	 * 获取报文状态字符串
	 * @return
	 */
	public String getXmlStatusString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通报文状态, this.getXmlStatus());
	}

	/**
	 * 获取接口类型字符串
	 * @return
	 */
	public String getInterTypeString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型, this.getInterType());
	}


}
