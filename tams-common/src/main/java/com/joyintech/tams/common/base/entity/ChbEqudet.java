package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbEqudet extends ChbEqudetKey {
    /**
     * 产品代码
     */
    private String account;

    /**
     * 登记银行代码（即发行机构代码）
     */
    private String registBankCode;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 业务确认日期
     */
    private Date equityConfDate;

    /**
     * 业务确认时间
     */
    private String shareConfDatetime;

    /**
     * 关联账号开户所在地
     */
    private String fundAccPlace;

    /**
     * 业务种类
     */
    private String busiType;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountCny;

    /**
     * 份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shares;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 关联活期存款账号
     */
    private String fundAcc;

    /**
     * 是否代销
     */
    private String isConsign;

    /**
     * 备注
     */
    private String remark;

    /**
     * 销售合同号
     */
    private String contractNo;

    /**
     * 理财账号
     */
    private String finAcc;

    /**
     * 客户统一编号
     */
    private String custContractNo;

    /**
     * 客户姓名
     */
    private String custName;

    /**
     * 代销机构代码
     */
    private String agencyCode;

    /**
     * 代销机构名称
     */
    private String agencyName;

    /**
     * 代销机构所属监管机构
     */
    private String agencyFsa;

    /**
     * 业务发生地所属监管
     */
    private String busiFsa;

    /**
     * 确认净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodValue;

    /**
     * 费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 交易柜员号
     */
    private String tranTellName;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 经办人
     */
    private String agent;

    /**
     * 是否报送
     */
    private String isSubmit;
    
    private String regCode;

    
    public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	/**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 登记银行代码（即发行机构代码）
     * @return 登记银行代码（即发行机构代码）
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码（即发行机构代码）
     * @param registBankCode 登记银行代码（即发行机构代码）
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 识别标识
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 取得 业务确认日期
     * @return 业务确认日期
     */
    public Date getEquityConfDate() {
        return equityConfDate;
    }

    /**
     * 设置 业务确认日期
     * @param equityConfDate 业务确认日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }

    /**
     * 取得 业务确认时间
     * @return 业务确认时间
     */
    public String getShareConfDatetime() {
        return shareConfDatetime;
    }

    /**
     * 设置 业务确认时间
     * @param shareConfDatetime 业务确认时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setShareConfDatetime(String shareConfDatetime) {
        this.shareConfDatetime = shareConfDatetime;
    }

    /**
     * 取得 关联账号开户所在地
     * @return 关联账号开户所在地
     */
    public String getFundAccPlace() {
        return fundAccPlace;
    }

    /**
     * 设置 关联账号开户所在地
     * @param fundAccPlace 关联账号开户所在地
     */
    public void setFundAccPlace(String fundAccPlace) {
        this.fundAccPlace = fundAccPlace == null ? null : fundAccPlace.trim();
    }

    /**
     * 取得 业务种类
     * @return 业务种类
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务种类
     * @param busiType 业务种类
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getAmountCny() {
        return amountCny;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param amountCny 折算人民币金额（元）
     */
    public void setAmountCny(BigDecimal amountCny) {
        this.amountCny = amountCny;
    }

    /**
     * 取得 份额
     * @return 份额
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置 份额
     * @param shares 份额
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    /**
     * 取得 渠道
     * @return 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置 渠道
     * @param channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * 取得 关联活期存款账号
     * @return 关联活期存款账号
     */
    public String getFundAcc() {
        return fundAcc;
    }

    /**
     * 设置 关联活期存款账号
     * @param fundAcc 关联活期存款账号
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc == null ? null : fundAcc.trim();
    }

    /**
     * 取得 是否代销
     * @return 是否代销
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 是否代销
     * @param isConsign 是否代销
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 销售合同号
     * @return 销售合同号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 销售合同号
     * @param contractNo 销售合同号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * 取得 理财账号
     * @return 理财账号
     */
    public String getFinAcc() {
        return finAcc;
    }

    /**
     * 设置 理财账号
     * @param finAcc 理财账号
     */
    public void setFinAcc(String finAcc) {
        this.finAcc = finAcc == null ? null : finAcc.trim();
    }

    /**
     * 取得 客户统一编号
     * @return 客户统一编号
     */
    public String getCustContractNo() {
        return custContractNo;
    }

    /**
     * 设置 客户统一编号
     * @param custContractNo 客户统一编号
     */
    public void setCustContractNo(String custContractNo) {
        this.custContractNo = custContractNo == null ? null : custContractNo.trim();
    }

    /**
     * 取得 客户姓名
     * @return 客户姓名
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置 客户姓名
     * @param custName 客户姓名
     */
    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    /**
     * 取得 代销机构代码
     * @return 代销机构代码
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * 设置 代销机构代码
     * @param agencyCode 代销机构代码
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode == null ? null : agencyCode.trim();
    }

    /**
     * 取得 代销机构名称
     * @return 代销机构名称
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * 设置 代销机构名称
     * @param agencyName 代销机构名称
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    /**
     * 取得 代销机构所属监管机构
     * @return 代销机构所属监管机构
     */
    public String getAgencyFsa() {
        return agencyFsa;
    }

    /**
     * 设置 代销机构所属监管机构
     * @param agencyFsa 代销机构所属监管机构
     */
    public void setAgencyFsa(String agencyFsa) {
        this.agencyFsa = agencyFsa == null ? null : agencyFsa.trim();
    }

    /**
     * 取得 业务发生地所属监管
     * @return 业务发生地所属监管
     */
    public String getBusiFsa() {
        return busiFsa;
    }

    /**
     * 设置 业务发生地所属监管
     * @param busiFsa 业务发生地所属监管
     */
    public void setBusiFsa(String busiFsa) {
        this.busiFsa = busiFsa == null ? null : busiFsa.trim();
    }

    /**
     * 取得 确认净值
     * @return 确认净值
     */
    public BigDecimal getProdValue() {
        return prodValue;
    }

    /**
     * 设置 确认净值
     * @param prodValue 确认净值
     */
    public void setProdValue(BigDecimal prodValue) {
        this.prodValue = prodValue;
    }

    /**
     * 取得 费用
     * @return 费用
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 费用
     * @param feeAmt 费用
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 交易柜员号
     * @return 交易柜员号
     */
    public String getTranTellName() {
        return tranTellName;
    }

    /**
     * 设置 交易柜员号
     * @param tranTellName 交易柜员号
     */
    public void setTranTellName(String tranTellName) {
        this.tranTellName = tranTellName == null ? null : tranTellName.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 取得 是否报送
     * @return 是否报送
     */
    public String getIsSubmit() {
        return isSubmit;
    }

    /**
     * 设置 是否报送
     * @param isSubmit 是否报送
     */
    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit == null ? null : isSubmit.trim();
    }
}