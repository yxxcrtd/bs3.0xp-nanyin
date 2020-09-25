package com.joyintech.tams.invest.product.prdrisklevel.dto;

import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.product.prdrisklevel.validator.PrdRiskLevel002ServiceCheckValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 新发产品风险等级评定新建
 */
@PageId("PrdRiskLevel002")
@ValidLogic(targetClass = PrdRiskLevel002ServiceCheckValidator.class)
public class PrdRiskLevel002Dto extends BaseEntity implements ProcessEntity {

	private static final long serialVersionUID = -9127258249482796931L;

	// 流水号
	private String seqNo;

	// 生成时间
	private Date generateTime;

	// 金融产品代码
	private String finProductCode;

	// 分支序号
	private Long branch;

	// 风险等级
	private String riskLevel;

	// 调整后风险等级
	private String ajustRiskLevel;

	// 金融产品名称
	private String finProductName;

	// 创建人
	private String createUser;

	// 创建部门
	private String createDept;

	// 创建时间
	private Date createTime;

	// 更新人
	private String updateUser;

	// 更新时间
	private Date updateTime;

	// 操作类型
	private String operateType;

	// 要素ID
	private String elementId;

	private String riskLevelStr; // 风险等级str

	private String ajustRiskLevelStr; // 调整后风险等级str

	public String getRiskLevelStr() {
		return riskLevelStr;
	}

	public void setRiskLevelStr(String riskLevelStr) {
		this.riskLevelStr = riskLevelStr;
	}

	public String getAjustRiskLevelStr() {
		return ajustRiskLevelStr;
	}

	public void setAjustRiskLevelStr(String ajustRiskLevelStr) {
		this.ajustRiskLevelStr = ajustRiskLevelStr;
	}

	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public String getFinProductCode() {
		return finProductCode;
	}

	public void setFinProductCode(String finProductCode) {
		this.finProductCode = finProductCode;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getAjustRiskLevel() {
		return ajustRiskLevel;
	}

	public void setAjustRiskLevel(String ajustRiskLevel) {
		this.ajustRiskLevel = ajustRiskLevel;
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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getFinProductName() {
		return finProductName;
	}

	public void setFinProductName(String finProductName) {
		this.finProductName = finProductName;
	}
}
