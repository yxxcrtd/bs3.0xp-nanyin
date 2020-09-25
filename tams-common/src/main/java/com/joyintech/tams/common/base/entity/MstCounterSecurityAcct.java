package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCounterSecurityAcct extends BaseEntity {
    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * 证券账户账号
     */
    private String securityAcctNo;

    /**
     * 证券账户名称
     */
    private String securityAcctName;

    /**
     * 托管机构
     */
    private String registOrg;

    /**
     * 是否启用
     */
    private String isOpen;

    /**
     * 取得 交易对手代码
     * @return 交易对手代码
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手代码
     * @param counterId 交易对手代码
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 证券账户账号
     * @return 证券账户账号
     */
    public String getSecurityAcctNo() {
        return securityAcctNo;
    }

    /**
     * 设置 证券账户账号
     * @param securityAcctNo 证券账户账号
     */
    public void setSecurityAcctNo(String securityAcctNo) {
        this.securityAcctNo = securityAcctNo == null ? null : securityAcctNo.trim();
    }

    /**
     * 取得 证券账户名称
     * @return 证券账户名称
     */
    public String getSecurityAcctName() {
        return securityAcctName;
    }

    /**
     * 设置 证券账户名称
     * @param securityAcctName 证券账户名称
     */
    public void setSecurityAcctName(String securityAcctName) {
        this.securityAcctName = securityAcctName == null ? null : securityAcctName.trim();
    }

    /**
     * 取得 托管机构
     * @return 托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 托管机构
     * @param registOrg 托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 是否启用
     * @return 是否启用
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * 设置 是否启用
     * @param isOpen 是否启用
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }
}