package com.joyintech.service.portfolio.ptlsubfundsmange.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;

/**
 * 
 * @author pancz
 *
 */
public class PtlSubFundsMangeDto extends BaseEntity implements GridRowDto{
	
	
 /**   
  * 流程状态
  */
    private  String  workStatus;

/**
 * 交易编号
 */
    private  String  tradeId;

/**    
 * 交易日
 */
    private  Date  settleDate;
    
/**
 * 产品代码
 */
    private   String   prodId;
    
/**    
 * 产品名称
 */
    private   String  prodName;
    
/**    
 * 交易金额
 */
    private  BigDecimal tradeAmt;
    
 /**
  * 子组合代码   
  */
    private  String   portfolioId; 
    
    
    /**
     * 组合代码
     */
    private String otherPortfolioId;
    
 /**
  * 子组合名称   
  */
    private   String  portfolioName;
   
/**    
 * 分配金额
 */
    private BigDecimal  allotAmt;
    
 /**
  * 币种
  */
    private  String  ccy;
    
/**    
 * 流水号
 */
  private  String  seqNo;
  
  /**    
   * 流水号
   */
    private  String  otherSeqNo;
  
  /**
   * 流水号list
   * @return
   */
  private  List<String>  seqNoList;
  
  /**
   * 父节点
   */
  private String parentInfo;

  /**
   * 子节点
   */
  private String childInfo;

  /**
   * 是否有子节点
   */
  private String hasChildren;
 
 /**
  * 工作流代码
  */
  private  String workflowId;
  
  
  
  
  
public String getWorkflowId() {
	return workflowId;
}

public void setWorkflowId(String workflowId) {
	this.workflowId = workflowId;
}

public String getOtherSeqNo() {
	return otherSeqNo;
}

public void setOtherSeqNo(String otherSeqNo) {
	this.otherSeqNo = otherSeqNo;
}



public String getCcy() {
	return ccy;
}

public void setCcy(String ccy) {
	this.ccy = ccy;
}

public String getSeqNo() {
	return seqNo;
}

public void setSeqNo(String seqNo) {
	this.seqNo = seqNo;
}

public String getWorkStatus() {
	return workStatus;
}

public void setWorkStatus(String workStatus) {
	this.workStatus = workStatus;
}

public String getTradeId() {
	return tradeId;
}

public void setTradeId(String tradeId) {
	this.tradeId = tradeId;
}

public Date getSettleDate() {
	return settleDate;
}

public void setSettleDate(Date settleDate) {
	this.settleDate = settleDate;
}

public String getProdId() {
	return prodId;
}

public void setProdId(String prodId) {
	this.prodId = prodId;
}

public String getProdName() {
	return prodName;
}

public void setProdName(String prodName) {
	this.prodName = prodName;
}

public BigDecimal getTradeAmt() {
	return tradeAmt;
}

public void setTradeAmt(BigDecimal tradeAmt) {
	this.tradeAmt = tradeAmt;
}

public String getPortfolioId() {
	return portfolioId;
}

public void setPortfolioId(String portfolioId) {
	this.portfolioId = portfolioId;
}

public String getPortfolioName() {
	return portfolioName;
}

public void setPortfolioName(String portfolioName) {
	this.portfolioName = portfolioName;
}

public BigDecimal getAllotAmt() {
	return allotAmt;
}

public void setAllotAmt(BigDecimal allotAmt) {
	this.allotAmt = allotAmt;
}

public List<String> getSeqNoList() {
	return seqNoList;
}

public void setSeqNoList(List<String> seqNoList) {
	this.seqNoList = seqNoList;
}

public String getParentInfo() {
	return parentInfo;
}

public void setParentInfo(String parentInfo) {
	this.parentInfo = parentInfo;
}

public String getChildInfo() {
	return childInfo;
}

public void setChildInfo(String childInfo) {
	this.childInfo = childInfo;
}

public String getHasChildren() {
	return hasChildren;
}

public void setHasChildren(String hasChildren) {
	this.hasChildren = hasChildren;
}

@Override
public String getCreateDept() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getCreateUser() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setCreateDept(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setCreateUser(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public String getId() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getName() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int getOrder() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public String getParentId() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getRowId() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isLeaf() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void setId(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setLeaf(boolean arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setName(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setOrder(int arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setParentId(String arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void setRowId(String arg0) {
	// TODO Auto-generated method stub
	
}

public String getOtherPortfolioId() {
	return otherPortfolioId;
}

public void setOtherPortfolioId(String otherPortfolioId) {
	this.otherPortfolioId = otherPortfolioId;
}


}
