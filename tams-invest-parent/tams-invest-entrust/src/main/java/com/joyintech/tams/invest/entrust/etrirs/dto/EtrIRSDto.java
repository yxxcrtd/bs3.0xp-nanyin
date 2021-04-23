/*
 * 文件名：EtrIRSDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrirs.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrirs.validator.EtrIRSValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 利率互换委托dto 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtrIRSDto
 * @since 1.0
 */
@PageId("EtrIRSDto002")
@ValidLogic(targetClass = EtrIRSValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrIRSDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3314619126979170188L;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 投资委托人员信息dto
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

    /**
     * 按钮id
     */
    private String elementId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 完成额度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal transactioncom;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 表格所选数据
     */
    private transient List<EtrIRSDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 起息日
     */
    private Date vdateAdd;

    /**
     * 到期日
     */
    private Date mdateAdd;
    
    /**
     * 到期日2
     */
    private Date mdate2Add;

    /**
     * 到期日（mdateAdd-mdate2Add)
     */
    private String dueDate;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 互换方式
     */
    
    private String busiPs;
    
    
    /**
     * 期限
     */
    
    private String termType;
    
    /**
     *固定利率%
     */
    
    private String matureYield;

    private String dealMode;
    
    /**
     * 获取 riskTrialRetDto
     * 
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    /**
     * 设置 riskTrialRetDto
     * 
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 vdateAdd
     * 
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }

    /**
     * 设置 vdateAdd
     * 
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }

    /**
     * 获取 mdateAdd
     * 
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }

    /**
     * 设置 mdateAdd
     * 
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
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
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 etrInvestPersonInfo
     * 
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }

    /**
     * 设置 etrInvestPersonInfo
     * 
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }

    /**
     * 获取 closeWay
     * 
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }

    /**
     * 设置 closeWay
     * 
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<EtrIRSDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrIRSDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 finishAmt
     * 
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 finishAmt
     * 
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    /**
     * 获取 transactioncom
     * 
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    /**
     * 设置 transactioncom
     * 
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }

	public Date getMdate2Add() {
		return mdate2Add;
	}

	public void setMdate2Add(Date mdate2Add) {
		this.mdate2Add = mdate2Add;
	}

	/**
	 * 互换方式
	 * @return
	 */
	@NotEmpty(fieldName = "EtrIRS.busiPs", groups = {ValidateGroupEdit.class})
	public String getBusiPs() {
		return busiPs;
	}

	public void setBusiPs(String busiPs) {
		this.busiPs = busiPs;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	@NotEmpty(fieldName = "EtrIRS.matureYield", groups = {ValidateGroupEdit.class})
	public String getMatureYield() {
		return matureYield;
	}

	public void setMatureYield(String matureYield) {
		this.matureYield = matureYield;
	}
	
	/**
     * 委托状态数据字典转换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getEntrustStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    
    /**
     *期限品种数据字典转换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getTermTypeStr() {
//    	if(StringUtil.isNotEmpty(this.getTermType())&&this.getTermType().contains(GlobalConstant.COMMA)){
//    		List<String> termTypeList = StringUtil.splitToList(GlobalConstant.COMMA,this.getTermType());
//    		StringBuffer sb = new StringBuffer();
//    		for(String s:termTypeList){
//    			sb.append(SysDataUtil.getNameFromDataDict(DictCode.期限品种_利率互换, s));
//    		}
//    		return sb.toString();
//    	}else{
    		 return SysDataUtil.getNameFromDataDict(DictCode.期限品种_利率互换, this.getTermType());
//    	}
    }
    
    /**
     * 币种数据字典装换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    
    /**
     * 互换方式数据字典装换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getBusiPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.互换方式, this.getBusiPs());
    }
    
    /**
     * 挂钩利率数据字典装换 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getDealModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.利率行情, this.getDealMode());
    }
    
    /**
     * 委托开始日 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }
    
    /**
     * 委托截止日 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }

    /**
     * 委托代码
     */
	@Override
	@PrimaryKey(value = Field.PK_1)
	public String getEntrustId() {
		return super.getEntrustId();
	}
    
	/**
     * 组合代码判空
     */
    @Override
    @NotEmpty(fieldName = "EtrIRS.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 委托开始日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrIRS.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate() {
        return super.getVdate();
    }

    /**
     * 委托终止日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrIRS.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate() {
        return super.getMdate();
    }


	@NotEmpty(fieldName = "EtrIRS.dealMode", groups = {ValidateGroupEdit.class})
    public String getDealMode() {
		return dealMode;
	}

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    @Override
	@NotEmpty(fieldName = "EtrIRS.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
		return super.getCcy();
	}

	@Override
	@NotEmpty(fieldName = "EtrIRS.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt() {
		return super.getEntrustAmt();
	}
    
    
    
    
    
    
}
