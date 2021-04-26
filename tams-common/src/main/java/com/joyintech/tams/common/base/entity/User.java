/*
 * 文件名：User.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.base.entity;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.dto.BaseDto;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;

/**
* 系统用户
* 
* @author 何阳阳
* @version 1.0 2018年1月18日
* @see User
* @since 1.0
*/
public class User extends BaseDto implements com.joyintech.framework.security.User{

   /**
    */
   private static final long serialVersionUID = 1L;

   /**
    * 用户ID
    */
   private String uid;

   /**
    * 用户名称
    */
   private String userName;

   /**
    * 密码
    */
   private String password;

   /**
    * 盐
    */
   private String salt;

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
    * 部门代码
    */
   private String deptCode;

   /**
    * 部门代码
    */
   private String deptName;

   /**
    * 机构代码
    */
   private String orgCode;

   /**
    * 机构名称
    */
   private String orgName;

   /**
    * SSO用户名
    */
   private String ssoUserId;

   /**
    * 手机号
    */
   private String mobNum;

   /**
    * 微信号
    */
   private String wechatNum;

   /**
    * 管理员标识
    */
   private String adminFlag;

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
    * 数据状态
    */
   private String status;

   /**
    * 工作流代码
    */
   private String workflowId;

   /**
    * 操作模式
    */
   private String operateType;

   /**
    * 选中的数据
    */
   private transient List<User> selectedItem;

    /**
     * 银行间交易员ID
     */
    private String ibuTrader;

   @Override
   public String getUid() {
       return uid;
   }

   @Override
   public String getUserName() {
       return userName;
   }

   @Override
   public String getPassword() {
       return password;
   }

   @Override
   public void setPassword(String password) {
       this.password = password;
   }

   @Override
   public void setSalt(String salt) {
       this.salt = salt;
   }

   @Override
   public String getSalt() {
       return salt;
   }

   public void setUid(String uid) {
       this.uid = uid;
   }

   public void setUserName(String username) {
       this.userName = username;
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

   public String getSsoUserId() {
       return ssoUserId;
   }

   public void setSsoUserId(String ssoUserId) {
       this.ssoUserId = ssoUserId;
   }

   public String getMobNum() {
       return mobNum;
   }

   public void setMobNum(String mobNum) {
       this.mobNum = mobNum;
   }

   public Date getLastPassDate() {
       return lastPassDate;
   }

   public void setLastPassDate(Date lastPassDate) {
       this.lastPassDate = lastPassDate;
   }

   public String getRemark() {
       return remark;
   }

   public void setRemark(String remark) {
       this.remark = remark;
   }

   public String getStatus() {
       return status;
   }

   public void setStatus(String status) {
       this.status = status;
   }

   @Override
   public boolean isExpired() {
       return false;
   }

   /**
    * 获取 workflowId
    * 
    * @return workflowId.
    */
   public String getWorkflowId() {
       return workflowId;
   }

   /**
    * 设置 workflowId
    * 
    * @param workflowId workflowId
    */
   public void setWorkflowId(String workflowId) {
       this.workflowId = workflowId;
   }

   /**
    * 获取 deptName
    * 
    * @return deptName.
    */
   public String getDeptName() {
       return deptName;
   }

   /**
    * 设置 deptName
    * 
    * @param deptName deptName
    */
   public void setDeptName(String deptName) {
       this.deptName = deptName;
   }

   /**
    * 获取 operateType
    * 
    * @return operateType.
    */
   public String getOperateType() {
       return operateType;
   }

   /**
    * 设置 operateType
    * 
    * @param operateType operateType
    */
   public void setOperateType(String operateType) {
       this.operateType = operateType;
   }

   /**
    * 获取 selectedItem
    * 
    * @return selectedItem.
    */
   public List<User> getSelectedItem() {
       return selectedItem;
   }

   /**
    * 设置 selectedItem
    * 
    * @param selectedItem selectedItem
    */
   public void setSelectedItem(List<User> selectedItem) {
       this.selectedItem = selectedItem;
   }

   /**
    * 获取 adminFlag
    * 
    * @return adminFlag.
    */
   public String getAdminFlag() {
       return adminFlag;
   }

   /**
    * 设置 adminFlag
    * 
    * @param adminFlag adminFlag
    */
   public void setAdminFlag(String adminFlag) {
       this.adminFlag = adminFlag;
   }

   /**
    * 获取 isFrozen
    * 
    * @return isFrozen.
    */
   public String getIsFrozen() {
       return isFrozen;
   }

   /**
    * 设置 isFrozen
    * 
    * @param isFrozen isFrozen
    */
   public void setIsFrozen(String isFrozen) {
       this.isFrozen = isFrozen;
   }

   /**
    * 获取 orgCode
    * 
    * @return orgCode.
    */
   public String getOrgCode() {
       return orgCode;
   }

   /**
    * 设置 orgCode
    * 
    * @param orgCode orgCode
    */
   public void setOrgCode(String orgCode) {
       this.orgCode = orgCode;
   }

   /**
    * 获取 orgName
    * 
    * @return orgName.
    */
   public String getOrgName() {
       return orgName;
   }

   /**
    * 设置 orgName
    * 
    * @param orgName orgName
    */
   public void setOrgName(String orgName) {
       this.orgName = orgName;
   }
   
   public String getWechatNum() {
       return wechatNum;
   }

   public void setWechatNum(String wechatNum) {
       this.wechatNum = wechatNum;
   }

/**
 * {@inheritDoc}
 */
@Override
public boolean isAdmin() {
    // TODO Auto-generated method stub
    return StringUtil.equals(this.getAdminFlag(), DictCodeList.是否_是.getValue());
}

/**
 * {@inheritDoc}
 */
@Override
public boolean isLocked() {
    // TODO Auto-generated method stub
    return StringUtil.equals(this.getIsFrozen(), DictCodeList.是否_是.getValue());
}

    /**
     * 获取 ibuTrader
     *
     * @return ibuTrader.
     */
    public String getIbuTrader() {
        return ibuTrader;
    }

    /**
     * 设置 ibuTrader
     *
     * @param ibuTrader ibuTrader
     */
    public void setIbuTrader(String ibuTrader) {
        this.ibuTrader = ibuTrader;
    }
}
