package com.joyintech.service.product.prdrisklevel.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * yangxiaofeng
 * 2019-10-28
 * 新发产品风险等级评定查询
 */
public class PrdRiskLevel extends BaseEntity {
	
	private static final long serialVersionUID = 7619015922098853127L;

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
}