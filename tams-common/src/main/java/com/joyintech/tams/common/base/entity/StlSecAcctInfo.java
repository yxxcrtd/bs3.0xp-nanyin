package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlSecAcctInfo extends BaseEntity {
    /**
     * 证券账户代码
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 开户来源，理财、自营
     */
    private String secAcctSource;

    /**
     * 登记托管机构，上清、中债、中证等
     */
    private String registOrg;

    /**
     * 关联资金账户，DVP账户、结算备付金账户、自营账户等
     */
    private String trdAcctId;

    /**
     * 关联资金账户币种
     */
    private String trdAcctCcy;

    /**
     * 关联回款账户
     */
    private String recAcctId;

    /**
     * 关联回款账户币种
     */
    private String recAcctCcy;

    /**
     * 备注
     */
    private String remark;

    /**
     * null
     */
    private String tradePlace;

    /**
     * 账户类型：SEC证券账户，FUND基金账户
     */
    private String secAcctType;

    /**
     * 基金管理人
     */
    private String manager;

    /**
     * 取得 证券账户代码
     * @return 证券账户代码
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券账户代码
     * @param secAcctId 证券账户代码
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
    }

    /**
     * 取得 证券账户名称
     * @return 证券账户名称
     */
    public String getSecAcctName() {
        return secAcctName;
    }

    /**
     * 设置 证券账户名称
     * @param secAcctName 证券账户名称
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName == null ? null : secAcctName.trim();
    }

    /**
     * 取得 开户来源，理财、自营
     * @return 开户来源，理财、自营
     */
    public String getSecAcctSource() {
        return secAcctSource;
    }

    /**
     * 设置 开户来源，理财、自营
     * @param secAcctSource 开户来源，理财、自营
     */
    public void setSecAcctSource(String secAcctSource) {
        this.secAcctSource = secAcctSource == null ? null : secAcctSource.trim();
    }

    /**
     * 取得 登记托管机构，上清、中债、中证等
     * @return 登记托管机构，上清、中债、中证等
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构，上清、中债、中证等
     * @param registOrg 登记托管机构，上清、中债、中证等
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 关联资金账户，DVP账户、结算备付金账户、自营账户等
     * @return 关联资金账户，DVP账户、结算备付金账户、自营账户等
     */
    public String getTrdAcctId() {
        return trdAcctId;
    }

    /**
     * 设置 关联资金账户，DVP账户、结算备付金账户、自营账户等
     * @param trdAcctId 关联资金账户，DVP账户、结算备付金账户、自营账户等
     */
    public void setTrdAcctId(String trdAcctId) {
        this.trdAcctId = trdAcctId == null ? null : trdAcctId.trim();
    }

    /**
     * 取得 关联资金账户币种
     * @return 关联资金账户币种
     */
    public String getTrdAcctCcy() {
        return trdAcctCcy;
    }

    /**
     * 设置 关联资金账户币种
     * @param trdAcctCcy 关联资金账户币种
     */
    public void setTrdAcctCcy(String trdAcctCcy) {
        this.trdAcctCcy = trdAcctCcy == null ? null : trdAcctCcy.trim();
    }

    /**
     * 取得 关联回款账户
     * @return 关联回款账户
     */
    public String getRecAcctId() {
        return recAcctId;
    }

    /**
     * 设置 关联回款账户
     * @param recAcctId 关联回款账户
     */
    public void setRecAcctId(String recAcctId) {
        this.recAcctId = recAcctId == null ? null : recAcctId.trim();
    }

    /**
     * 取得 关联回款账户币种
     * @return 关联回款账户币种
     */
    public String getRecAcctCcy() {
        return recAcctCcy;
    }

    /**
     * 设置 关联回款账户币种
     * @param recAcctCcy 关联回款账户币种
     */
    public void setRecAcctCcy(String recAcctCcy) {
        this.recAcctCcy = recAcctCcy == null ? null : recAcctCcy.trim();
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
     * 取得 null
     * @return null
     */
    public String getTradePlace() {
        return tradePlace;
    }

    /**
     * 设置 null
     * @param tradePlace null
     */
    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace == null ? null : tradePlace.trim();
    }

    /**
     * 取得 账户类型：SEC证券账户，FUND基金账户
     * @return 账户类型：SEC证券账户，FUND基金账户
     */
    public String getSecAcctType() {
        return secAcctType;
    }

    /**
     * 设置 账户类型：SEC证券账户，FUND基金账户
     * @param secAcctType 账户类型：SEC证券账户，FUND基金账户
     */
    public void setSecAcctType(String secAcctType) {
        this.secAcctType = secAcctType == null ? null : secAcctType.trim();
    }

    /**
     * 取得 基金管理人
     * @return 基金管理人
     */
    public String getManager() {
        return manager;
    }

    /**
     * 设置 基金管理人
     * @param manager 基金管理人
     */
    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }
}