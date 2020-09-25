package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto;

import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品需求上报dto
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月27日
 * @see ReportProductRequirement001Dto
 * @since 1.0
 * PRODUCT_REQUIREMENT	产品需求表	
 */
@PageId(value = "ReportProductRequirement001")
public class ReportProductRequirement001Dto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    /**
     * 画面Id
     */
    private String pageId;
    
    /**
     * elementId
     */
    private String elementId;
    /**
     * 产品需求编号
     */
    private String requirementId;
    
    /**
     * 产品形成依据
     */
    private String requirementBasis;
    
    /**
     * 需求发起时间
     */
    private Date  requirementTime;
    
    /**
     * 目标客户
     */
    private String tarCustomer;
    
    /**
     * 规模
     */
    private String prdScale;
    
    /**
     * 期限
     */
    private String prdPeriod;
    
    /**
     * 收益率
     */
    private String prdRate;
    
    /**
     * 产品详细需求
     */
    private String prdRequirementDetail;
    
    /**
     * 需求发起端
     */
    private String requirementSource;
    
    /**
     * 数据状态
     */
    private String dataStatus;
    
    /**
     * 创建人	CREATE_USER	VARCHAR(20)
     */
    private String createUser;
    
    /**
     * 创建部门	CREATE_DEPT	VARCHAR(32)
     */
    private String createDept;
    
    /**
     * 创建时间	CREATE_TIME	TIMESTAMP
     */
    private Date createTime;
    
    /**
     * 更新人	UPDATE_USER	VARCHAR(20)
     */
    private String updateUser;
    
    /**
     * 更新时间	UPDATE_TIME	TIMESTAMP
     */
    private Date updateTime;
    
    /**
     * 上报状态
     */
    private String reportStatus;
    
    /**
     * 上报说明
     * @return
     */
    private String reportDecr;
    
    
	public String getReportDecr() {
		return reportDecr;
	}

	public void setReportDecr(String reportDecr) {
		this.reportDecr = reportDecr;
	}

	public String getReportStatus() {
		return reportStatus;
	}
	
	/**
     * 是否已发起初评
     */
    private String reviewYesno;

    /**
     * 需求发起人
     */
    private String requirementUser;
	
	/**
	 * 上报状态
	 * @param reportStatus
	 */
	public String getReportStatusStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.初评结果, this.getReportStatus());
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	@PrimaryKey(Field.PK_1)
	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getRequirementBasis() {
		return requirementBasis;
	}

	public void setRequirementBasis(String requirementBasis) {
		this.requirementBasis = requirementBasis;
	}

	public Date getRequirementTime() {
		return requirementTime;
	}
	
	/**
	 * 需求发起时间
	 * @return
	 */
	public String getRequirementTimeStr() {
		return DateUtil.format(this.getRequirementTime());
	}

	public void setRequirementTime(Date requirementTime) {
		this.requirementTime = requirementTime;
	}

	public String getTarCustomer() {
		return tarCustomer;
	}

	public void setTarCustomer(String tarCustomer) {
		this.tarCustomer = tarCustomer;
	}

	public String getPrdScale() {
		return prdScale;
	}

	public void setPrdScale(String prdScale) {
		this.prdScale = prdScale;
	}

	public String getPrdPeriod() {
		return prdPeriod;
	}

	public void setPrdPeriod(String prdPeriod) {
		this.prdPeriod = prdPeriod;
	}

	public String getPrdRate() {
		return prdRate;
	}

	public void setPrdRate(String prdRate) {
		this.prdRate = prdRate;
	}

	public String getPrdRequirementDetail() {
		return prdRequirementDetail;
	}

	public void setPrdRequirementDetail(String prdRequirementDetail) {
		this.prdRequirementDetail = prdRequirementDetail;
	}

	public String getRequirementSource() {
		return requirementSource;
	}
	
	public String getRequirementSourceStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.需求发起端, this.getRequirementSource());
	}

	public void setRequirementSource(String requirementSource) {
		this.requirementSource = requirementSource;
	}

	public String getDataStatus() {
		return dataStatus;
	}
	
	/**
	 * 有效状态
	 * @return
	 */
	public String getDataStatusStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.有效状态, this.getDataStatus());
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getReviewYesno() {
		return reviewYesno;
	}

	public void setReviewYesno(String reviewYesno) {
		this.reviewYesno = reviewYesno;
	}

	public String getRequirementUser() {
		return requirementUser;
	}

	public void setRequirementUser(String requirementUser) {
		this.requirementUser = requirementUser;
	}
	
}
