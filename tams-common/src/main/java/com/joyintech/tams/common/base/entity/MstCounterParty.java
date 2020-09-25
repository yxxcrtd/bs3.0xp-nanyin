package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCounterParty extends BaseEntity {
    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * 交易对手类型，市场机构、资管产品、通道
     */
    private String counterType;

    /**
     * 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    private String counterName;

    /**
     * 关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品
     */
    private String linkId;

    /**
     * 管理人，资管产品时录入
     */
    private String managerId;

    /**
     * 所属总行
     */
    private String headBank;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联内部机构
     */
    private String linkDeptCode;

    /**
     * 人行一级分类
     */
    private String pTypeOne;

    /**
     * 人行二级分类
     */
    private String pTypeTwo;

    /**
     * 交易对手一级类型
     */
    private String counterType1;

    /**
     * 交易对手二级类型
     */
    private String counterType2;

    /**
     * 是否为中央交易对手方
     */
    private String isCentralCounter;

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
     * 取得 交易对手类型，市场机构、资管产品、通道
     * @return 交易对手类型，市场机构、资管产品、通道
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 交易对手类型，市场机构、资管产品、通道
     * @param counterType 交易对手类型，市场机构、资管产品、通道
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType == null ? null : counterType.trim();
    }

    /**
     * 取得 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     * @return 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * 设置 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     * @param counterName 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName == null ? null : counterName.trim();
    }

    /**
     * 取得 关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品
     * @return 关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品
     */
    public String getLinkId() {
        return linkId;
    }

    /**
     * 设置 关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品
     * @param linkId 关联代码，市场机构关联机构信息、资管产品无，通道关联标的类金融产品
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId == null ? null : linkId.trim();
    }

    /**
     * 取得 管理人，资管产品时录入
     * @return 管理人，资管产品时录入
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 管理人，资管产品时录入
     * @param managerId 管理人，资管产品时录入
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 取得 所属总行
     * @return 所属总行
     */
    public String getHeadBank() {
        return headBank;
    }

    /**
     * 设置 所属总行
     * @param headBank 所属总行
     */
    public void setHeadBank(String headBank) {
        this.headBank = headBank == null ? null : headBank.trim();
    }

    /**
     * 取得 联系人
     * @return 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置 联系人
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 取得 联系方式
     * @return 联系方式
     */
    public String getContactWay() {
        return contactWay;
    }

    /**
     * 设置 联系方式
     * @param contactWay 联系方式
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
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
     * 取得 关联内部机构
     * @return 关联内部机构
     */
    public String getLinkDeptCode() {
        return linkDeptCode;
    }

    /**
     * 设置 关联内部机构
     * @param linkDeptCode 关联内部机构
     */
    public void setLinkDeptCode(String linkDeptCode) {
        this.linkDeptCode = linkDeptCode == null ? null : linkDeptCode.trim();
    }

    /**
     * 取得 人行一级分类
     * @return 人行一级分类
     */
    public String getpTypeOne() {
        return pTypeOne;
    }

    /**
     * 设置 人行一级分类
     * @param pTypeOne 人行一级分类
     */
    public void setpTypeOne(String pTypeOne) {
        this.pTypeOne = pTypeOne == null ? null : pTypeOne.trim();
    }

    /**
     * 取得 人行二级分类
     * @return 人行二级分类
     */
    public String getpTypeTwo() {
        return pTypeTwo;
    }

    /**
     * 设置 人行二级分类
     * @param pTypeTwo 人行二级分类
     */
    public void setpTypeTwo(String pTypeTwo) {
        this.pTypeTwo = pTypeTwo == null ? null : pTypeTwo.trim();
    }

    /**
     * 取得 交易对手一级类型
     * @return 交易对手一级类型
     */
    public String getCounterType1() {
        return counterType1;
    }

    /**
     * 设置 交易对手一级类型
     * @param counterType1 交易对手一级类型
     */
    public void setCounterType1(String counterType1) {
        this.counterType1 = counterType1 == null ? null : counterType1.trim();
    }

    /**
     * 取得 交易对手二级类型
     * @return 交易对手二级类型
     */
    public String getCounterType2() {
        return counterType2;
    }

    /**
     * 设置 交易对手二级类型
     * @param counterType2 交易对手二级类型
     */
    public void setCounterType2(String counterType2) {
        this.counterType2 = counterType2 == null ? null : counterType2.trim();
    }

    /**
     * 取得 是否为中央交易对手方
     * @return 是否为中央交易对手方
     */
    public String getIsCentralCounter() {
        return isCentralCounter;
    }

    /**
     * 设置 是否为中央交易对手方
     * @param isCentralCounter 是否为中央交易对手方
     */
    public void setIsCentralCounter(String isCentralCounter) {
        this.isCentralCounter = isCentralCounter == null ? null : isCentralCounter.trim();
    }
}
