package com.joyintech.tams.valuation.interf.valuation.sztinterf.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 结果汇总表
 * @author hudongyang
 *
 */
public class SztIntfLogModel extends BaseEntity{


	private static final long serialVersionUID = -2125457930713546271L;
	/**
	 * 业务流水号
	 */
	private String serialno;
	
	/**
	 * 产品代码
	 */
	private String finprodId;
	
	/**
	 * 产品名称
	 */
	private String finprodName;

	/**
	 * 接口类型
	 */
	private String interType;
	
	/**
	 * 发送时间
	 */
	private Date sendTime;

	
	/**
	 * 接收时间
	 */
	private Date returnTime;
	
	/**
	 * 接收信息
	 */
	private String returnTxt;
	
	/**
	 * 估值日期
	 */
	private String cdate;
	
	/**
	 * 报文状态
	 */
	private String xmlStatus;
	
	/**
	 * 对账状态
	 */
	private String checkStatus;

	/**
	 * remark
	 */
	private String remark;
	
	/**
	 * 关联流水号 -- 返回对账明细报文关联流水号与我方发送报文的业务流水号一致
	 */
	private String refNo;
	
	/**
	 * 明细条数
	 */
	private Integer detailCount;


	/**
	 * 获取产品id
	 * @return
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置产品id
	 * @param finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
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
		this.finprodName = finprodName;
	}

	/**
	 * 获取业务流水号
	 * @return
	 */
	public String getSerialno() {
		return serialno;
	}

	/**
	 * 设置业务流水号
	 * @param serialno
	 */
	public void setSerialno(String serialno) {
		this.serialno = serialno;
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
		this.interType = interType;
	}

	/**
	 * 获取发送时间
	 * @return
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置发送时间
	 * @param sendTime
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	/**
	 * 获取接收时间
	 * @return
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 * 设置接收时间
	 * @param returnTime
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 获取接收信息
	 * @return
	 */
	public String getReturnTxt() {
		return returnTxt;
	}

	/**
	 * 设置接收信息
	 * @param returnTxt
	 */
	public void setReturnTxt(String returnTxt) {
		this.returnTxt = returnTxt;
	}

	/**
	 * 获取估值日期
	 * @return
	 */
	public String getCdate() {
		return cdate;
	}

	/**
	 * 设置估值日期
	 * @param cdate
	 */
	public void setCdate(String cdate) {
		this.cdate = cdate;
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
		this.checkStatus = checkStatus;
	}

	/**
	 * 获取关联流水号
	 * @return
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * 设置关联流水号
	 * @param refNo
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * 获取明细条数
	 * @return
	 */
	public Integer getDetailCount() {
		return detailCount;
	}

	/**
	 * 设置明细条数
	 * @param detailCount
	 */
	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
