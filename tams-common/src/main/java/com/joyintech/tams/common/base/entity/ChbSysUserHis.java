package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbSysUserHis extends ChbSysUserHisKey {
    /**
     * ����
     */
    private String userName;

    /**
     * ����
     */
    private String realName;

    /**
     * ����
     */
    private String mailAddr;

    /**
     * ��
     */
    private String phoneNum;

    /**
     * ��
     */
    private String password;

    /**
     * ����
     */
    private String deptCode;

    /**
     * SSO���
     */
    private String ssoUserId;

    /**
     * ���
     */
    private String mobNum;

    /**
     * ���
     */
    private String wechatNum;

    /**
     * ����� ������
     */
    private String adminFlag;

    /**
     * �������� ����������,�������
     */
    private Short failedCount;

    /**
     * ��������
     */
    private Date lastPassDate;

    /**
     * ����
     */
    private String isFrozen;

    /**
     * �����
     */
    private String workflowId;

    /**
     * ��
     */
    private String remark;

    /**
     * 取得 ����
     * @return ����
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 ����
     * @param userName ����
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置 ����
     * @param realName ����
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * 设置 ����
     * @param mailAddr ����
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr == null ? null : mailAddr.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置 ��
     * @param phoneNum ��
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 ��
     * @param password ��
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 ����
     * @param deptCode ����
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 SSO���
     * @return SSO���
     */
    public String getSsoUserId() {
        return ssoUserId;
    }

    /**
     * 设置 SSO���
     * @param ssoUserId SSO���
     */
    public void setSsoUserId(String ssoUserId) {
        this.ssoUserId = ssoUserId == null ? null : ssoUserId.trim();
    }

    /**
     * 取得 ���
     * @return ���
     */
    public String getMobNum() {
        return mobNum;
    }

    /**
     * 设置 ���
     * @param mobNum ���
     */
    public void setMobNum(String mobNum) {
        this.mobNum = mobNum == null ? null : mobNum.trim();
    }

    /**
     * 取得 ���
     * @return ���
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * 设置 ���
     * @param wechatNum ���
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum == null ? null : wechatNum.trim();
    }

    /**
     * 取得 ����� ������
     * @return ����� ������
     */
    public String getAdminFlag() {
        return adminFlag;
    }

    /**
     * 设置 ����� ������
     * @param adminFlag ����� ������
     */
    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag == null ? null : adminFlag.trim();
    }

    /**
     * 取得 �������� ����������,�������
     * @return �������� ����������,�������
     */
    public Short getFailedCount() {
        return failedCount;
    }

    /**
     * 设置 �������� ����������,�������
     * @param failedCount �������� ����������,�������
     */
    public void setFailedCount(Short failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * 取得 ��������
     * @return ��������
     */
    public Date getLastPassDate() {
        return lastPassDate;
    }

    /**
     * 设置 ��������
     * @param lastPassDate ��������
     */
    public void setLastPassDate(Date lastPassDate) {
        this.lastPassDate = lastPassDate;
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getIsFrozen() {
        return isFrozen;
    }

    /**
     * 设置 ����
     * @param isFrozen ����
     */
    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen == null ? null : isFrozen.trim();
    }

    /**
     * 取得 �����
     * @return �����
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 �����
     * @param workflowId �����
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 ��
     * @return ��
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 ��
     * @param remark ��
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}