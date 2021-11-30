package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerStlDeaErrodata extends BaseEntity {


    private String registSeqno;
    /**
     * 提供销售结算资金服务机构代码 
     */
    private String institutionCode;

    /**
     * 产品登记编码 
     */
    private String prdRegistCode;

    /**
     * 销售结算资金账号 
     */
    private String accountNo;

    /**
     * 销售结算资金流动方向 
     */
    private String tradePs;

    /**
     * 销售结算资金交易流水号 
     */
    private String tradeNo;

    /**
     * 销售结算资金交易日期 
     */
    private Date tradeVdate;

    /**
     * 数据状态 
     */
    private String dataStatus;

    /**
     * 工作流代码 
     */
    private String workflowId;

    /**
     * 机构代码 
     */
    private String orgCode;

    /**
     * 部门代码 
     */
    private String deptCode;

    public String getRegistSeqno() {
        return registSeqno;
    }

    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno;
    }

    /**
     * 取得 提供销售结算资金服务机构代码 
     * @return 提供销售结算资金服务机构代码 
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 提供销售结算资金服务机构代码 
     * @param institutionCode 提供销售结算资金服务机构代码 
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 产品登记编码 
     * @return 产品登记编码 
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 产品登记编码 
     * @param prdRegistCode 产品登记编码 
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
    }

    /**
     * 取得 销售结算资金账号 
     * @return 销售结算资金账号 
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * 设置 销售结算资金账号 
     * @param accountNo 销售结算资金账号 
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     * 取得 销售结算资金流动方向 
     * @return 销售结算资金流动方向 
     */
    public String getTradePs() {
        return tradePs;
    }

    /**
     * 设置 销售结算资金流动方向 
     * @param tradePs 销售结算资金流动方向 
     */
    public void setTradePs(String tradePs) {
        this.tradePs = tradePs == null ? null : tradePs.trim();
    }

    /**
     * 取得 销售结算资金交易流水号 
     * @return 销售结算资金交易流水号 
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置 销售结算资金交易流水号 
     * @param tradeNo 销售结算资金交易流水号 
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * 取得 销售结算资金交易日期 
     * @return 销售结算资金交易日期 
     */
    public Date getTradeVdate() {
        return tradeVdate;
    }

    /**
     * 设置 销售结算资金交易日期 
     * @param tradeVdate 销售结算资金交易日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeVdate(Date tradeVdate) {
        this.tradeVdate = tradeVdate;
    }

    /**
     * 取得 数据状态 
     * @return 数据状态 
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态 
     * @param dataStatus 数据状态 
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
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
}