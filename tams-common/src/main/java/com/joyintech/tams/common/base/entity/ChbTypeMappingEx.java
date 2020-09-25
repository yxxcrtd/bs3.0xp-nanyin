package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_TYPE_MAPPING_EX表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-08
 */
public class ChbTypeMappingEx extends BaseEntity {
    /**
     * 映射编号
     */
    private String mappingid;

    /**
     * 映射名称
     */
    private String mappingname;

    /**
     * 源属性1024
     */
    private String srcfield;

    /**
     * 源属性1
     */
    private String srcfield1;

    /**
     * 源属性2
     */
    private String srcfield2;

    /**
     * 源属性3
     */
    private String srcfield3;

    /**
     * 源属性4
     */
    private String srcfield4;

    /**
     * 源属性5
     */
    private String srcfield5;

    /**
     * 默认目标属性：F1－目标属性1，F2－目标属性2，F3－目标属性3，F4－目标属性4
     */
    private String objfield;

    /**
     * 目标属性1
     */
    private String objfield1;

    /**
     * 目标属性2
     */
    private String objfield2;

    /**
     * 目标属性3
     */
    private String objfield3;

    /**
     * 目标属性4
     */
    private String objfield4;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

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
     * 机构代码
     */
    private String orgCode;

    /**
     * 映射代码：CHB_ASSETTYPE－资产类型,CHB_ASSETCODE-资产代码
     */
    private String mappingcode;

    /**
     * 映射关键字
     */
    private String mappingkey;
    
    /**
     * 映射编号
     * @return MAPPINGID 映射编号
     */
    public String getMappingid() {
        return mappingid;
    }

    /**
     * 映射编号
     * @param mappingid 映射编号
     */
    public void setMappingid(String mappingid) {
        this.mappingid = mappingid == null ? null : mappingid.trim();
    }

    /**
     * 映射名称
     * @return MAPPINGNAME 映射名称
     */
    public String getMappingname() {
        return mappingname;
    }

    /**
     * 映射名称
     * @param mappingname 映射名称
     */
    public void setMappingname(String mappingname) {
        this.mappingname = mappingname == null ? null : mappingname.trim();
    }

    /**
     * 源属性1
     * @return SRCFIELD1 源属性1
     */
    public String getSrcfield1() {
        return srcfield1;
    }

    /**
     * 源属性1
     * @param srcfield1 源属性1
     */
    public void setSrcfield1(String srcfield1) {
        this.srcfield1 = srcfield1 == null ? null : srcfield1.trim();
    }

    /**
     * 源属性2
     * @return SRCFIELD2 源属性2
     */
    public String getSrcfield2() {
        return srcfield2;
    }

    /**
     * 源属性2
     * @param srcfield2 源属性2
     */
    public void setSrcfield2(String srcfield2) {
        this.srcfield2 = srcfield2 == null ? null : srcfield2.trim();
    }

    /**
     * 源属性3
     * @return SRCFIELD3 源属性3
     */
    public String getSrcfield3() {
        return srcfield3;
    }

    /**
     * 源属性3
     * @param srcfield3 源属性3
     */
    public void setSrcfield3(String srcfield3) {
        this.srcfield3 = srcfield3 == null ? null : srcfield3.trim();
    }

    /**
     * 源属性4
     * @return SRCFIELD4 源属性4
     */
    public String getSrcfield4() {
        return srcfield4;
    }

    /**
     * 源属性4
     * @param srcfield4 源属性4
     */
    public void setSrcfield4(String srcfield4) {
        this.srcfield4 = srcfield4 == null ? null : srcfield4.trim();
    }

    /**
     * 源属性5
     * @return SRCFIELD5 源属性5
     */
    public String getSrcfield5() {
        return srcfield5;
    }

    /**
     * 源属性5
     * @param srcfield5 源属性5
     */
    public void setSrcfield5(String srcfield5) {
        this.srcfield5 = srcfield5 == null ? null : srcfield5.trim();
    }

    /**
     * 默认目标属性：F1－目标属性1，F2－目标属性2，F3－目标属性3，F4－目标属性4
     * @return OBJFIELD 默认目标属性：F1－目标属性1，F2－目标属性2，F3－目标属性3，F4－目标属性4
     */
    public String getObjfield() {
        String r = "";
        
        switch ( objfield.toLowerCase()) {
            case "f1":
                r=objfield1;
                break;
            case "f2":
                r=objfield2;
                break;
            case "f3":
                r=objfield3;
                break;
            case "f4":
                r=objfield4;
                break;
            default:
                r="";
        }
        return r;
    }

    /**
     * 默认目标属性：F1－目标属性1，F2－目标属性2，F3－目标属性3，F4－目标属性4
     * @param objfield 默认目标属性：F1－目标属性1，F2－目标属性2，F3－目标属性3，F4－目标属性4
     */
    public void setObjfield(String objfield) {
        this.objfield = objfield == null ? null : objfield.trim();
    }

    /**
     * 目标属性1
     * @return OBJFIELD1 目标属性1
     */
    public String getObjfield1() {
        return objfield1;
    }

    /**
     * 目标属性1
     * @param objfield1 目标属性1
     */
    public void setObjfield1(String objfield1) {
        this.objfield1 = objfield1 == null ? null : objfield1.trim();
    }

    /**
     * 目标属性2
     * @return OBJFIELD2 目标属性2
     */
    public String getObjfield2() {
        return objfield2;
    }

    /**
     * 目标属性2
     * @param objfield2 目标属性2
     */
    public void setObjfield2(String objfield2) {
        this.objfield2 = objfield2 == null ? null : objfield2.trim();
    }

    /**
     * 目标属性3
     * @return OBJFIELD3 目标属性3
     */
    public String getObjfield3() {
        return objfield3;
    }

    /**
     * 目标属性3
     * @param objfield3 目标属性3
     */
    public void setObjfield3(String objfield3) {
        this.objfield3 = objfield3 == null ? null : objfield3.trim();
    }

    /**
     * 目标属性4
     * @return OBJFIELD4 目标属性4
     */
    public String getObjfield4() {
        return objfield4;
    }

    /**
     * 目标属性4
     * @param objfield4 目标属性4
     */
    public void setObjfield4(String objfield4) {
        this.objfield4 = objfield4 == null ? null : objfield4.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 工作流代码
     * @return WORKFLOW_ID 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
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
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 映射代码：CHB_ASSETTYPE－资产类型,CHB_ASSETCODE-资产代码
     * @return MAPPINGCODE 映射代码：CHB_ASSETTYPE－资产类型,CHB_ASSETCODE-资产代码
     */
    public String getMappingcode() {
        return mappingcode;
    }

    /**
     * 映射代码：CHB_ASSETTYPE－资产类型,CHB_ASSETCODE-资产代码
     * @param mappingcode 映射代码：CHB_ASSETTYPE－资产类型,CHB_ASSETCODE-资产代码
     */
    public void setMappingcode(String mappingcode) {
        this.mappingcode = mappingcode == null ? null : mappingcode.trim();
    }

    public String getSrcfield() {
        return srcfield;
    }

    public void setSrcfield(String srcfield) {
        this.srcfield = srcfield;
    }

    public String getMappingkey() {
        return mappingcode + "|" 
               + (srcfield1 == null ? "" : srcfield1 + "|")
               + (srcfield2 == null ? "" : srcfield2 + "|")
               + (srcfield3 == null ? "" : srcfield3 + "|")
               + (srcfield4 == null ? "" : srcfield4 + "|") 
               + (srcfield5 == null ? "" : srcfield5);
    }

    public String getMappingkeyAll() {
        return mappingcode + "|" 
                + (srcfield == null ? "" : srcfield + "|");
    }
    
    public void setMappingkey(String mappingkey) {
        this.mappingkey = mappingkey;
    }
    
} 