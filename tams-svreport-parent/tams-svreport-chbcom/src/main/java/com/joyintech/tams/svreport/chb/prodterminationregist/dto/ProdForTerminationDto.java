package com.joyintech.tams.svreport.chb.prodterminationregist.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;


public class ProdForTerminationDto {

	/**
     * 产品代码
     */
    private String prodId;
    /**
     * 父产品代码
     */
    private String fprodId;
    /**
     * 机构代码
     */
    private String orgCode;
    /**
     * 运作模式
     */
    private String operateMode;
    /**
     * 产品登记编码
     */
    private String regCode;
    
    private String prodRegistCode;
    /**
     * 登记银行代码
     */
    private String registBankCode;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 确认日
     */
    private Date settDate;
    /**
     * 终止登记中已报送登记序列
     */
    private String regSeq;
    /**
     * 终止登记中未报送登记序列
     */
    private String unRegSeq;
    /**
     * 募集币种
     */
    private String raiseCcy;
    /**
     * 费用列表
     */
    private List<ChbPrdFeeDeal> feeList;
    /**
     * 交易信息列表
     */
    private List<ChbPrdDeal> dealList;
    /**
     * 费用头寸列表
     */
    private List<ChbPrdFeePosition> feePositionList;
    /**
     * 产品交易头寸列表
     */
    private List<ChbPrdPosition> dealPositionList;
    /**
     * 产品类型
     */
    private String prodtype;
    /**
     * 计息基础
     */
    private String basic;
    /**
     * 数据状态
     */
    private String status;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ChbPrdFeePosition> getFeePositionList() {
		return feePositionList;
	}
	public void setFeePositionList(List<ChbPrdFeePosition> feePositionList) {
		this.feePositionList = feePositionList;
	}
	public List<ChbPrdPosition> getDealPositionList() {
		return dealPositionList;
	}
	public void setDealPositionList(List<ChbPrdPosition> dealPositionList) {
		this.dealPositionList = dealPositionList;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getProdtype() {
		return prodtype;
	}
	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getFprodId() {
		return fprodId;
	}
	public void setFprodId(String fprodId) {
		this.fprodId = fprodId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOperateMode() {
		return operateMode;
	}
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRegistBankCode() {
		return registBankCode;
	}
	public void setRegistBankCode(String registBankCode) {
		this.registBankCode = registBankCode;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public Date getSettDate() {
		return settDate;
	}
	public void setSettDate(Date settDate) {
		this.settDate = settDate;
	}
	public String getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	public String getUnRegSeq() {
		return unRegSeq;
	}
	public void setUnRegSeq(String unRegSeq) {
		this.unRegSeq = unRegSeq;
	}
	public String getRaiseCcy() {
		return raiseCcy;
	}
	public void setRaiseCcy(String raiseCcy) {
		this.raiseCcy = raiseCcy;
	}
	public List<ChbPrdFeeDeal> getFeeList() {
		return feeList;
	}
	public void setFeeList(List<ChbPrdFeeDeal> feeList) {
		this.feeList = feeList;
	}
	public List<ChbPrdDeal> getDealList() {
		return dealList;
	}
	public void setDealList(List<ChbPrdDeal> dealList) {
		this.dealList = dealList;
	}
	public String getProdRegistCode() {
		return prodRegistCode;
	}
	public void setProdRegistCode(String prodRegistCode) {
		this.prodRegistCode = prodRegistCode;
	}
    
}
