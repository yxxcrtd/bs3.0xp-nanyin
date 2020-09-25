package com.joyintech.tams.invest.product.messageadjust.supplement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.messageadjust.supplement.validate.FinSupplementPlanChg001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
@PageId("FinSupplementPlanChg001")
@ValidLogic(targetClass = FinSupplementPlanChg001Validator.class)
public class FinSupplementPlanChg001Dto extends FinFloatAccrualSche implements ProcessEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	/**
	 * 产品代码
	 */
	private String finprodId;
	/**
	 * 产品简称
	 */
	private String finprodAbbr;
	
	/**
	 * 产品款型
	 */
	private String prodStyle;
	
	/**
	 * 分层子产品代码
	 */
	private String layFinprodId;
	
	/**
	 * 是否分层
	 */
	private String isLay;
	/**
	 * 顺序行号
	 */
	private BigDecimal rn;
	
	/**
	 * 合并/拆分列表
	 */
	private List<FinSupplementPlanChg001Dto> delList;
	
	/**
	 * 原列表
	 */
    private List<FinSupplementPlanChg001Dto> dataList;
	
    /**
     * 要素id
     */
    private String elementId;
	
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 1:合并  2：拆分
     */
    private String opType;
    
    /**
     * 计划是否有效
     */
    private String  isAvailable;
	/**
	 * 回补状态
	 */
	private String supplementStatus;
	
	/**
	 * 交易编号
	 */
	private String tradeId;
	
	/**
	 * 新增区间拆分日
	 */
	private Date splitDate;
	
	/**
	 * 新的回补日
	 */
	private Date newAccrDate;
	

	@PrimaryKey(Field.PK_1)
	public String getCashId(){
		return super.getCashId();
	}


    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }

    /**
     * 获取 layFinprodId
     * @return layFinprodId.
     */
    public String getLayFinprodId() {
        return layFinprodId;
    }

    /**
     * 设置 layFinprodId
     * @param layFinprodId layFinprodId
     */
    public void setLayFinprodId(String layFinprodId) {
        this.layFinprodId = layFinprodId;
    }

    /**
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    @PrimaryKey(Field.PK_3)
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    
    /**
     * 获取 delList
     * @return delList.
     */
    public List<FinSupplementPlanChg001Dto> getDelList() {
        return delList;
    }

    
    /**
     * 设置 delList
     * @param delList delList
     */
    public void setDelList(List<FinSupplementPlanChg001Dto> delList) {
        this.delList = delList;
    }

    
    /**
     * 获取 dataList
     * @return dataList.
     */
    public List<FinSupplementPlanChg001Dto> getDataList() {
        return dataList;
    }

    
    /**
     * 设置 dataList
     * @param dataList dataList
     */
    public void setDataList(List<FinSupplementPlanChg001Dto> dataList) {
        this.dataList = dataList;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    
    /**
     * 获取 opType
     * @return opType.
     */
    public String getOpType() {
        return opType;
    }

    
    /**
     * 设置 opType
     * @param opType opType
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

    
    /**
     * 获取 supplementStatus
     * @return supplementStatus.
     */
    public String getSupplementStatus() {
        return supplementStatus;
    }

    public String getSupplementStatusStr() {
      return SysDataUtil.getNameFromDataDict(DictCode.贴补状态, this.supplementStatus);
    }

    
    /**
     * 设置 supplementStatus
     * @param supplementStatus supplementStatus
     */
    public void setSupplementStatus(String supplementStatus) {
        this.supplementStatus = supplementStatus;
    }

    
    /**
     * 获取 tradeId
     * @return tradeId.
     */
    @PrimaryKey(Field.PK_2)
    public String getTradeId() {
        return tradeId;
    }

    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * 获取 rn
     * @return rn.
     */
    public BigDecimal getRn() {
        return rn;
    }


    /**
     * 设置 rn
     * @param rn rn
     */
    public void setRn(BigDecimal rn) {
        this.rn = rn;
    }


    /**
     * 获取 isAvailable
     * @return isAvailable.
     */
    public String getIsAvailable() {
        return isAvailable;
    }


    /**
     * 设置 isAvailable
     * @param isAvailable isAvailable
     */
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }


    /**
     * 获取 splitDate
     * @return splitDate.
     */
    public Date getSplitDate() {
        return splitDate;
    }


    /**
     * 设置 splitDate
     * @param splitDate splitDate
     */
    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }


    /**
     * 获取 newAccrDate
     * @return newAccrDate.
     */
    public Date getNewAccrDate() {
        return newAccrDate;
    }


    /**
     * 设置 newAccrDate
     * @param newAccrDate newAccrDate
     */
    public void setNewAccrDate(Date newAccrDate) {
        this.newAccrDate = newAccrDate;
    }
   
	
}
