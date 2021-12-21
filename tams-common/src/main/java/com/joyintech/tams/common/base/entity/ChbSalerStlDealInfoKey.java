package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSalerStlDealInfoKey extends BaseEntity {
    /**
     * 提供销售结算资金服务机构代码 
     */
    private String institutionCode;

    /**
     * 销售结算资金交易流水号 
     */
    private String tradeNo;

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
}