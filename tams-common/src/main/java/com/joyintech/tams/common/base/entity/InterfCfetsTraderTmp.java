package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCfetsTraderTmp extends BaseEntity {
    /**
     * 交易员ID
     */
    private String traderId;

    /**
     * 交易机构ID，6位编号
     */
    private String partyCustomerId;

    /**
     * 交易员姓名
     */
    private String traderName;

    /**
     * 座机、手机号码
     */
    private String telphoneNo;

    /**
     * 联系电话
     */
    private String telNo;

    /**
     * 传真
     */
    private String faxNo;

    /**
     * MSN
     */
    private String msn;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮政编号
     */
    private String postCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 交易员ID
     * @return 交易员ID
     */
    public String getTraderId() {
        return traderId;
    }

    /**
     * 设置 交易员ID
     * @param traderId 交易员ID
     */
    public void setTraderId(String traderId) {
        this.traderId = traderId == null ? null : traderId.trim();
    }

    /**
     * 取得 交易机构ID，6位编号
     * @return 交易机构ID，6位编号
     */
    public String getPartyCustomerId() {
        return partyCustomerId;
    }

    /**
     * 设置 交易机构ID，6位编号
     * @param partyCustomerId 交易机构ID，6位编号
     */
    public void setPartyCustomerId(String partyCustomerId) {
        this.partyCustomerId = partyCustomerId == null ? null : partyCustomerId.trim();
    }

    /**
     * 取得 交易员姓名
     * @return 交易员姓名
     */
    public String getTraderName() {
        return traderName;
    }

    /**
     * 设置 交易员姓名
     * @param traderName 交易员姓名
     */
    public void setTraderName(String traderName) {
        this.traderName = traderName == null ? null : traderName.trim();
    }

    /**
     * 取得 座机、手机号码
     * @return 座机、手机号码
     */
    public String getTelphoneNo() {
        return telphoneNo;
    }

    /**
     * 设置 座机、手机号码
     * @param telphoneNo 座机、手机号码
     */
    public void setTelphoneNo(String telphoneNo) {
        this.telphoneNo = telphoneNo == null ? null : telphoneNo.trim();
    }

    /**
     * 取得 联系电话
     * @return 联系电话
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * 设置 联系电话
     * @param telNo 联系电话
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    /**
     * 取得 传真
     * @return 传真
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * 设置 传真
     * @param faxNo 传真
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo == null ? null : faxNo.trim();
    }

    /**
     * 取得 MSN
     * @return MSN
     */
    public String getMsn() {
        return msn;
    }

    /**
     * 设置 MSN
     * @param msn MSN
     */
    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    /**
     * 取得 电子邮箱
     * @return 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 电子邮箱
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 取得 地址
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 取得 邮政编号
     * @return 邮政编号
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置 邮政编号
     * @param postCode 邮政编号
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
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
}