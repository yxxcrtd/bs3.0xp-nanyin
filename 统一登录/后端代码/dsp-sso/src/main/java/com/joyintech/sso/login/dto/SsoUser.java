package com.joyintech.sso.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 * @auther zhangnian
 * @date 2021/5/12
 */
@ApiModel(value="用户信息",description="用户信息")
public class SsoUser {

//    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required=false,name="userId",value="用户ID",example="")
    private String userId;

    @ApiModelProperty(required=false,name="systemCode",value="系统代码",example="")
    private String systemCode;

    @ApiModelProperty(required=false,name="userName",value="用户名称",example="")
    private String userName;

    @ApiModelProperty(required=false,name="password",value="密码",example="")
    private String password;

    @ApiModelProperty(required=false,name="salt",value="盐",example="")
    private String salt;

    @ApiModelProperty(required=false,name="realName",value="真实姓名",example="")
    private String realName;

    @ApiModelProperty(required=false,name="mailAddr",value="邮件地址",example="")
    private String mailAddr;

    @ApiModelProperty(required=false,name="phoneNum",value="电话",example="")
    private String phoneNum;

    @ApiModelProperty(required=false,name="deptCode",value="部门代码",example="")
    private String deptCode;

    @ApiModelProperty(required=false,name="deptName",value="部门名称",example="")
    private String deptName;

    @ApiModelProperty(required=false,name="orgCode",value="机构代码",example="")
    private String orgCode;

    @ApiModelProperty(required=false,name="orgName",value="机构名称",example="")
    private String orgName;

    @ApiModelProperty(required=false,name="mobNum",value="手机号",example="")
    private String mobNum;

    @ApiModelProperty(required=false,name="wechatNum",value="微信号",example="")
    private String wechatNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }
}

