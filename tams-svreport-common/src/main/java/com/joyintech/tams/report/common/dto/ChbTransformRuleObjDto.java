package com.joyintech.tams.report.common.dto;

import java.util.Date;

/**
 * 描述:CHB_TRANSFORM_RULE_OBJ表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-12-16
 */
public class ChbTransformRuleObjDto {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 对象表id
     */
    private String objTableId;

    /**
     * 对象表字段
     */
    private String objTableFiled;

    /**
     * 类型
     */
    private String transType;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 维护表属性 
     */
    private String infoProperty;

    /**
     * 登记表属性
     */
    private String subProperty;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 对象表id
     * @return OBJ_TABLE_ID 对象表id
     */
    public String getObjTableId() {
        return objTableId;
    }

    /**
     * 对象表id
     * @param objTableId 对象表id
     */
    public void setObjTableId(String objTableId) {
        this.objTableId = objTableId == null ? null : objTableId.trim();
    }

    /**
     * 对象表字段
     * @return OBJ_TABLE_FILED 对象表字段
     */
    public String getObjTableFiled() {
        return objTableFiled;
    }

    /**
     * 对象表字段
     * @param objTableFiled 对象表字段
     */
    public void setObjTableFiled(String objTableFiled) {
        this.objTableFiled = objTableFiled == null ? null : objTableFiled.trim();
    }

    /**
     * 类型
     * @return TRANS_TYPE 类型
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 类型
     * @param transType 类型
     */
    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
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
     * 维护表属性 
     * @return INFO_PROPERTY 维护表属性 
     */
    public String getInfoProperty() {
        return infoProperty;
    }

    /**
     * 维护表属性 
     * @param infoProperty 维护表属性 
     */
    public void setInfoProperty(String infoProperty) {
        this.infoProperty = infoProperty == null ? null : infoProperty.trim();
    }

    /**
     * 登记表属性
     * @return SUB_PROPERTY 登记表属性
     */
    public String getSubProperty() {
        return subProperty;
    }

    /**
     * 登记表属性
     * @param subProperty 登记表属性
     */
    public void setSubProperty(String subProperty) {
        this.subProperty = subProperty == null ? null : subProperty.trim();
    }
}