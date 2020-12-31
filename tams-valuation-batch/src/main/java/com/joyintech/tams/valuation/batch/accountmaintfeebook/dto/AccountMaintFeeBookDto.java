package com.joyintech.tams.valuation.batch.accountmaintfeebook.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

/**
 * 账户维护费每日计提 Dto
 * @author hpp
 *
 */
public class AccountMaintFeeBookDto{
	/**
	 * 账套代码
	 */
	private String booksetId;
	/**
     * 业务明细代码(金融产品代码)
     */
    private String busiId;

    /**
     * 业务明细类型（金融产品类型）
     */
    private String busiDetailType;
   	/**
   	 * 账务明细代码
   	 */
   	private String bokDetailId;
   	/**
   	 * 计提开始日
   	 */
   	private Date positionStartDate;
   	/**
   	 * 计提结束日
   	 */
   	private Date positionEndDate;
   	/**
   	 * 每季费用金额
   	 */
   	private BigDecimal feeAmtDetail;
   	/**
   	 * 帐套日期
   	 */
   	private Date booksetDate;
   	/**
     * 发生日期
     */
    private Date happenDate;
   	/**
   	 * 入账日期
   	 */
   	private Date bookDate;
   	/**
   	 * 会计场景
   	 */
   	private TreeNode bookSetTreeNode;
   	/**
   	 * 任务优先级
   	 */
   	private Long taskPri;
   	/**
     * 任务代码
     */
    private String taskId;
    
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public String getBusiId() {
		return busiId;
	}
	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	public String getBusiDetailType() {
		return busiDetailType;
	}
	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}
	public String getBokDetailId() {
		return bokDetailId;
	}
	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
	public Date getPositionStartDate() {
		return positionStartDate;
	}
	public void setPositionStartDate(Date positionStartDate) {
		this.positionStartDate = positionStartDate;
	}
	public Date getPositionEndDate() {
		return positionEndDate;
	}
	public void setPositionEndDate(Date positionEndDate) {
		this.positionEndDate = positionEndDate;
	}
	public BigDecimal getFeeAmtDetail() {
		return feeAmtDetail;
	}
	public void setFeeAmtDetail(BigDecimal feeAmtDetail) {
		this.feeAmtDetail = feeAmtDetail;
	}
	public Date getBooksetDate() {
		return booksetDate;
	}
	public void setBooksetDate(Date booksetDate) {
		this.booksetDate = booksetDate;
	}
	public Date getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public TreeNode getBookSetTreeNode() {
		return bookSetTreeNode;
	}
	public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
		this.bookSetTreeNode = bookSetTreeNode;
	}
	public Long getTaskPri() {
		return taskPri;
	}
	public void setTaskPri(Long taskPri) {
		this.taskPri = taskPri;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
