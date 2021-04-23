/*
 * 文件名：BaseEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.base;

import com.joyintech.background.component.common.pack.common.constant.DictCodeList;
import com.joyintech.background.component.common.pack.common.export.paginator.domain.ITableRow;
import com.joyintech.background.component.common.pack.common.security.UserUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author 何阳阳
 * @version 1.0 2018年1月26日
 * @see BaseEntity
 * @since 1.0
 */
public class BaseEntity implements ITableRow, Serializable {
    
    /**
     */
    private static final long serialVersionUID = 7899921079885082389L;

    /**
     * 状态: A,E,P,D
     */
    private String status;
    
    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    public BaseEntity() {
        this.createUser = UserUtil.getUserName();
        this.createUser = this.createUser == null ? UserUtil.DEFAULT_USER : this.createUser;
        this.updateUser = UserUtil.getUserName();
        this.updateUser = this.updateUser == null ? UserUtil.DEFAULT_USER : this.updateUser;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.createDept = UserUtil.getDeptCode();
        this.createTime = new Date();
        this.updateTime = new Date();
        this.status = DictCodeList.数据状态_新建.getValue();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取 createDept
     * 
     * @return createDept.
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 设置 createDept
     * 
     * @param createDept createDept
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    /**
     * 获取 workflowId
     * @return workflowId.
     */
    public String getWorkflowId() {
        return this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    
    /**
     * 设置 workflowId
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 表格行是否可修改标识（01：可以，02：不可以）
     */
    private String updateFlg;

    public String getUpdateFlg() {
        return updateFlg;
    }

    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }

    @Override
    public String toString() {
        StringBuffer strb = new StringBuffer("");

        for (Field field : this.getClass().getDeclaredFields()) {
            String filedName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Method m;
            try {
                m = this.getClass().getMethod("get" + filedName);
                Object fieldValue = m.invoke(this);
                
                if (fieldValue != null && StringUtils.isNotEmpty(fieldValue.toString()) && !"NULL".equalsIgnoreCase(fieldValue.toString())) {
                    strb.append(filedName + ":" + fieldValue.toString() + "\n");
                }
            } catch (SecurityException e) {
                //e.printStackTrace();
            } catch (NoSuchMethodException e) {
                //e.printStackTrace();
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            } catch (InvocationTargetException e) {
                //e.printStackTrace();
            }
        }
        
       if (strb.length() < 1) {
           return "";
       }
        
        return strb.replace(strb.length() - 1, strb.length(), "").toString();
    }
}
