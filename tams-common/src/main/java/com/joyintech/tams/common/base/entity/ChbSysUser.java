package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbSysUser extends ChbSysUserKey {
    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮件地址
     */
    private String mailAddr;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * SSO用户名
     */
    private String ssoUserId;

    /**
     * 手机号
     */
    private String mobNum;

    /**
     * 备注保留字段
     */
    private String wechaNum;

    /**
     * 管理员标识
     */
    private String adminFlag;

    /**
     * 密码输入错误次数
     */
    private Short failedCount;

    /**
     * 上次密码修改时间
     */
    private Date lastPassDate;

    /**
     * 是否冻结
     */
    private String isFrozen;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 微信号
     */
    private String wechatNum;

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
     * 取得 用户名称
     * @return 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名称
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 取得 真实姓名
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置 真实姓名
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 取得 邮件地址
     * @return 邮件地址
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * 设置 邮件地址
     * @param mailAddr 邮件地址
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr == null ? null : mailAddr.trim();
    }

    /**
     * 取得 电话
     * @return 电话
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置 电话
     * @param phoneNum 电话
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 取得 密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 取得 SSO用户名
     * @return SSO用户名
     */
    public String getSsoUserId() {
        return ssoUserId;
    }

    /**
     * 设置 SSO用户名
     * @param ssoUserId SSO用户名
     */
    public void setSsoUserId(String ssoUserId) {
        this.ssoUserId = ssoUserId == null ? null : ssoUserId.trim();
    }

    /**
     * 取得 手机号
     * @return 手机号
     */
    public String getMobNum() {
        return mobNum;
    }

    /**
     * 设置 手机号
     * @param mobNum 手机号
     */
    public void setMobNum(String mobNum) {
        this.mobNum = mobNum == null ? null : mobNum.trim();
    }

    /**
     * 取得 备注保留字段
     * @return 备注保留字段
     */
    public String getWechaNum() {
        return wechaNum;
    }

    /**
     * 设置 备注保留字段
     * @param wechaNum 备注保留字段
     */
    public void setWechaNum(String wechaNum) {
        this.wechaNum = wechaNum == null ? null : wechaNum.trim();
    }

    /**
     * 取得 管理员标识
     * @return 管理员标识
     */
    public String getAdminFlag() {
        return adminFlag;
    }

    /**
     * 设置 管理员标识
     * @param adminFlag 管理员标识
     */
    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag == null ? null : adminFlag.trim();
    }

    /**
     * 取得 密码输入错误次数
     * @return 密码输入错误次数
     */
    public Short getFailedCount() {
        return failedCount;
    }

    /**
     * 设置 密码输入错误次数
     * @param failedCount 密码输入错误次数
     */
    public void setFailedCount(Short failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * 取得 上次密码修改时间
     * @return 上次密码修改时间
     */
    public Date getLastPassDate() {
        return lastPassDate;
    }

    /**
     * 设置 上次密码修改时间
     * @param lastPassDate 上次密码修改时间
     */
    public void setLastPassDate(Date lastPassDate) {
        this.lastPassDate = lastPassDate;
    }

    /**
     * 取得 是否冻结
     * @return 是否冻结
     */
    public String getIsFrozen() {
        return isFrozen;
    }

    /**
     * 设置 是否冻结
     * @param isFrozen 是否冻结
     */
    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen == null ? null : isFrozen.trim();
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
     * 取得 微信号
     * @return 微信号
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * 设置 微信号
     * @param wechatNum 微信号
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum == null ? null : wechatNum.trim();
    }
}