package com.joyintech.tams.invest.settle.ordernewbuild.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.settle.ordernewbuild.validator.OrderNewBuild001Validator;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yaozhenguo
 * @version 1.0 2019年10月29日 下午4:27:36
 * @ClassName OrderNewBuild001Dto
 * @Description
 */
@PageId("OrderNewBuild001Dto")
@ValidLogic(targetClass = OrderNewBuild001Validator.class,groups = {ValidateGroupEdit.class})
public class OrderNewBuild001Dto extends BaseEntity implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 画面Id
     */
    private String pageId;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 页面模式
     */
    private String pageModel;
    
    /**
     * elementId
     */
    private String elementId;
	/**
	 * 指令编号
	 */
	private String actOrderId;

	/**
	 * 业务类型,产品认购....
	 */
	private String busiType;

	/**
	 * 划款类型，托管划款、清算划款
	 */
	private String payType;

	/**
	 * 组合代码
	 */
	private String portfolioId;

	/**
	 * 指令类型
	 */
	private String orderType;
	/**
	 * 理财产品代码
	 */
	private String prdId;

	/**
	 * 结算日期
	 */
	private Date settleDate;

	/**
	 * 本方资金账户
	 */
	private String myAcctNo;

	/**
	 * 收付方向
	 */
	private String ps;

	/**
	 * 对方资金账户
	 */
	private String counterAcctNo;

	/**
	 * 结算金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal settleAmt;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 结算状态
	 */
	private String settleStatus;

	/**
	 * 用途
	 */
	private String purpose;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否需要发送
	 */
	private String isNeedSend;

	/**
	 * 指令状态，新建、撤销
	 */
	private String orderStatus;

	/**
	 * 原指令编号，撤销时存被撤销的指令编号
	 */
	private String pTradeId;

	/**
	 * 发送时间
	 */
	private Date sendDate;

	/**
	 * 发送状态
	 */
	private String sendStatus;

	/**
	 * 指令接收状态
	 */
	private String orderRecStatus;

	/**
	 * 处理状态
	 */
	private String orderRetStatus;

	/**
	 * 取得 指令编号
	 * 
	 * @return 指令编号
	 */
	@PrimaryKey(Field.PK_1)
	public String getActOrderId() {
		return actOrderId;
	}

	/**
	 * 设置 指令编号
	 * 
	 * @param orderId
	 *            指令编号
	 */
	public void setActOrderId(String actOrderId) {
		this.actOrderId = actOrderId == null ? null : actOrderId.trim();
	}

	/**
	 * 取得 业务类型,产品认购....
	 * 
	 * @return 业务类型,产品认购....
	 */
	public String getBusiType() {
		return busiType;
	}
	
	public String getBusiTypeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.平安业务类型, this.getBusiType());
	}

	/**
	 * 设置 业务类型,产品认购....
	 * 
	 * @param busiType
	 *            业务类型,产品认购....
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType == null ? null : busiType.trim();
	}

	/**
	 * 取得 划款类型，托管划款、清算划款
	 * 
	 * @return 划款类型，托管划款、清算划款
	 */
	public String getPayType() {
		return payType;
	}
	
	public String getPayTypeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.平安划款类型, getPayType());
	}
	/**
	 * 设置 划款类型，托管划款、清算划款
	 * 
	 * @param payType
	 *            划款类型，托管划款、清算划款
	 */
	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	/**
	 * 取得 组合代码
	 * 
	 * @return 组合代码
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * 设置 组合代码
	 * 
	 * @param portfolioId
	 *            组合代码
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId == null ? null : portfolioId.trim();
	}

	/**
	 * 取得 理财产品代码
	 * 
	 * @return 理财产品代码
	 */
	public String getPrdId() {
		return prdId;
	}

	/**
	 * 设置 理财产品代码
	 * 
	 * @param prdId
	 *            理财产品代码
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId == null ? null : prdId.trim();
	}

	/**
	 * 取得 结算日期
	 * 
	 * @return 结算日期
	 */
	public Date getSettleDate() {
		return settleDate;
	}
	
	public String getSettleDateStr() {
		return DateUtil.format(getSettleDate());
	}

	/**
	 * 设置 结算日期
	 * 
	 * @param settleDate
	 *            结算日期
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	/**
	 * 取得 本方资金账户
	 * 
	 * @return 本方资金账户
	 */
	public String getMyAcctNo() {
		return myAcctNo;
	}

	/**
	 * 设置 本方资金账户
	 * 
	 * @param myAcctNo
	 *            本方资金账户
	 */
	public void setMyAcctNo(String myAcctNo) {
		this.myAcctNo = myAcctNo == null ? null : myAcctNo.trim();
	}

	/**
	 * 取得 收付方向
	 * 
	 * @return 收付方向
	 */
	public String getPs() {
		return ps;
	}

	/**
	 * 设置 收付方向
	 * 
	 * @param ps
	 *            收付方向
	 */
	public void setPs(String ps) {
		this.ps = ps == null ? null : ps.trim();
	}

	/**
	 * 取得 对方资金账户
	 * 
	 * @return 对方资金账户
	 */
	public String getCounterAcctNo() {
		return counterAcctNo;
	}

	/**
	 * 设置 对方资金账户
	 * 
	 * @param counterAcctNo
	 *            对方资金账户
	 */
	public void setCounterAcctNo(String counterAcctNo) {
		this.counterAcctNo = counterAcctNo == null ? null : counterAcctNo.trim();
	}

	/**
	 * 取得 结算金额
	 * 
	 * @return 结算金额
	 */
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	/**
	 * 设置 结算金额
	 * 
	 * @param settleAmt
	 *            结算金额
	 */
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	/**
	 * 取得 币种
	 * 
	 * @return 币种
	 */
	public String getCcy() {
		return ccy;
	}
	
	public String getCcyStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.币种, getCcy());
	}

	/**
	 * 设置 币种
	 * 
	 * @param ccy
	 *            币种
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy == null ? null : ccy.trim();
	}

	/**
	 * 取得 结算状态
	 * 
	 * @return 结算状态
	 */
	public String getSettleStatus() {
		return settleStatus;
	}

	/**
	 * 设置 结算状态
	 * 
	 * @param settleStatus
	 *            结算状态
	 */
	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus == null ? null : settleStatus.trim();
	}

	/**
	 * 取得 用途
	 * 
	 * @return 用途
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * 设置 用途
	 * 
	 * @param purpose
	 *            用途
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose == null ? null : purpose.trim();
	}

	/**
	 * 取得 备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 取得 是否需要发送
	 * 
	 * @return 是否需要发送
	 */
	public String getIsNeedSend() {
		return isNeedSend;
	}

	/**
	 * 设置 是否需要发送
	 * 
	 * @param isNeedSend
	 *            是否需要发送
	 */
	public void setIsNeedSend(String isNeedSend) {
		this.isNeedSend = isNeedSend == null ? null : isNeedSend.trim();
	}

	/**
	 * 取得 指令状态，新建、撤销
	 * 
	 * @return 指令状态，新建、撤销
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置 指令状态，新建、撤销
	 * 
	 * @param orderStatus
	 *            指令状态，新建、撤销
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus == null ? null : orderStatus.trim();
	}

	/**
	 * 取得 原指令编号，撤销时存被撤销的指令编号
	 * 
	 * @return 原指令编号，撤销时存被撤销的指令编号
	 */
	public String getpTradeId() {
		return pTradeId;
	}

	/**
	 * 设置 原指令编号，撤销时存被撤销的指令编号
	 * 
	 * @param pTradeId
	 *            原指令编号，撤销时存被撤销的指令编号
	 */
	public void setpTradeId(String pTradeId) {
		this.pTradeId = pTradeId == null ? null : pTradeId.trim();
	}

	/**
	 * 取得 发送时间
	 * 
	 * @return 发送时间
	 */
	public Date getSendDate() {
		return sendDate;
	}
	
	public String getSendDateStr() {
		return DateUtil.format(getSendDate());
	}

	/**
	 * 设置 发送时间
	 * 
	 * @param sendDate
	 *            发送时间
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 取得 发送状态
	 * 
	 * @return 发送状态
	 */
	public String getSendStatus() {
		return sendStatus;
	}
	
	public String getSendStatusStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus());
	}

	/**
	 * 设置 发送状态
	 * 
	 * @param sendStatus
	 *            发送状态
	 */
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus == null ? null : sendStatus.trim();
	}

	/**
	 * 取得 指令接收状态
	 * 
	 * @return 指令接收状态
	 */
	public String getOrderRecStatus() {
		return orderRecStatus;
	}
	
	public String getOrderRecStatusStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.成功失败标志, getOrderRecStatus());
	}

	/**
	 * 设置 指令接收状态
	 * 
	 * @param orderRecStatus
	 *            指令接收状态
	 */
	public void setOrderRecStatus(String orderRecStatus) {
		this.orderRecStatus = orderRecStatus == null ? null : orderRecStatus.trim();
	}

	/**
	 * 取得 处理状态
	 * 
	 * @return 处理状态
	 */
	public String getOrderRetStatus() {
		return orderRetStatus;
	}
	
	public String getOrderRetStatusStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.指令处理状态, getOrderRetStatus());
	}

	/**
	 * 设置 处理状态
	 * 
	 * @param orderRetStatus
	 *            处理状态
	 */
	public void setOrderRetStatus(String orderRetStatus) {
		this.orderRetStatus = orderRetStatus == null ? null : orderRetStatus.trim();
	}

	public String getOrderType() {
		return orderType;
	}
	
	public String getOrderTypeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.平安划款类型, getOrderType());
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	
	
}
