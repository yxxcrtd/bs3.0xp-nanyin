package com.joyintech.tams.common.base.entity;

import java.util.Date;

/**
 * 描述:REP_INVASSETKIND表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-01-17
 */
public class RepInvassetkindDto extends RepInvassetkindDtoKey {
    /**
     * 比例上限
     */
    private String tLimit;

    /**
     * 比例下限
     */
    private String dLimit;

    /**
     * 状态
     */
    private String status;

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
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 比例上限
     * @return T_LIMIT 比例上限
     */
    public String gettLimit() {
        return tLimit;
    }

    /**
     * 比例上限
     * @param tLimit 比例上限
     */
    public void settLimit(String tLimit) {
        this.tLimit = tLimit == null ? null : tLimit.trim();
    }

    /**
     * 比例下限
     * @return D_LIMIT 比例下限
     */
    public String getdLimit() {
        return dLimit;
    }

    /**
     * 比例下限
     * @param dLimit 比例下限
     */
    public void setdLimit(String dLimit) {
        this.dLimit = dLimit == null ? null : dLimit.trim();
    }

    /**
     * 状态
     * @return STATUS 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}