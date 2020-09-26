package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfbalance.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;

public class ValSztInterfBalance001Dto extends BaseEntity implements ITableRow{

	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3456911295424392099L;

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
	 * 对账状态
	 */
	private String checkStatus;
	
	/**
	 * 对账状态字符串
	 */
	private String checkStatusString;
	
	/**
	 * 估值日期
	 */
    @NotEmpty(fieldName = "ValBalanceStatementQuery001.valDate")
	private String valDate;
	
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
	 * 获取接口类型字符串
	 * @return
	 */
	public String getInterTypeString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通接口类型, this.getInterType());
	}

	/**
	 * 获取对账状态
	 * @return
	 */
	public String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * 设置对账状态
	 * @param checkStatus
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus == null ? null : checkStatus.trim();
	}

	/**
	 * 获取对账状态字符串
	 * @return
	 */
	public String getCheckStatusString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通对账状态, this.getCheckStatus());
	}

	/**
	 * 获取估值日期
	 * @return
	 */
	public String getValDate() {
		return valDate;
	}

	/**
	 * 设置估值日期
	 * @param valDate
	 */
	public void setValDate(String valDate) {
		this.valDate = valDate;
	}
}
