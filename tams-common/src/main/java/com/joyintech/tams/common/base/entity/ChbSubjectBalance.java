package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_SUBJECT_BALANCE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-05-05
 */
public class ChbSubjectBalance extends BaseEntity{
    /**
     * 主键
     */
    private String produuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 余额日期
     */
    private Date balanceDate;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 父科目号
     */
    private String fsubjectNo;

    /**
     * 科目级别
     */
    private Long subjectLevel;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 原币
     */
    private String oCcy;

    /**
     * 原币余额
     */
    private BigDecimal oAmt;

    /**
     * 原币当日发生额贷方
     */
    private BigDecimal tdyOCAmt;

    /**
     * 原币当日发生额借方
     */
    private BigDecimal tdyODAmt;

    /**
     * 原币结转当日发生额贷方
     */
    private BigDecimal tdyOCoCAmt;

    /**
     * 原币结转当日发生额借方
     */
    private BigDecimal tdyOCoDAmt;

    /**
     * 原币申购未实现平准金借
     */
    private BigDecimal tdyPurODUlamt;

    /**
     * 原币赎回未实现平准金借
     */
    private BigDecimal tdyRedODUlamt;

    /**
     * 原币赎回未实现平准金贷
     */
    private BigDecimal tdyRedOCUlamt;

    /**
     * 原币申购未实现平准金贷
     */
    private BigDecimal tdyPurOCUlamt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据状态 A-待处理 E-有效 D-删除
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 数据来源
     */
    private String dataSource;
    
    /**
     * 业务主键加密
     */
    private String md5primarykey;

    /**
     * 主键
     * @return PRODUUID 主键
     */
    public String getProduuid() {
        return produuid;
    }

    /**
     * 主键
     * @param produuid 主键
     */
    public void setProduuid(String produuid) {
        this.produuid = produuid == null ? null : produuid.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 余额日期
     * @return BALANCE_DATE 余额日期
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    /**
     * 余额日期
     * @param balanceDate 余额日期
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    /**
     * 科目号
     * @return SUBJECT_NO 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 父科目号
     * @return FSUBJECT_NO 父科目号
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }

    /**
     * 父科目号
     * @param fsubjectNo 父科目号
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo == null ? null : fsubjectNo.trim();
    }

    /**
     * 科目级别
     * @return SUBJECT_LEVEL 科目级别
     */
    public Long getSubjectLevel() {
        return subjectLevel;
    }

    /**
     * 科目级别
     * @param subjectLevel 科目级别
     */
    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    /**
     * 余额方向
     * @return BAL_FLAG 余额方向
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 余额方向
     * @param balFlag 余额方向
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag == null ? null : balFlag.trim();
    }

    /**
     * 原币
     * @return O_CCY 原币
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 原币
     * @param oCcy 原币
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 原币余额
     * @return O_AMT 原币余额
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    /**
     * 原币余额
     * @param oAmt 原币余额
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

    /**
     * 原币当日发生额贷方
     * @return TDY_O_C_AMT 原币当日发生额贷方
     */
    public BigDecimal getTdyOCAmt() {
        return tdyOCAmt;
    }

    /**
     * 原币当日发生额贷方
     * @param tdyOCAmt 原币当日发生额贷方
     */
    public void setTdyOCAmt(BigDecimal tdyOCAmt) {
        this.tdyOCAmt = tdyOCAmt;
    }

    /**
     * 原币当日发生额借方
     * @return TDY_O_D_AMT 原币当日发生额借方
     */
    public BigDecimal getTdyODAmt() {
        return tdyODAmt;
    }

    /**
     * 原币当日发生额借方
     * @param tdyODAmt 原币当日发生额借方
     */
    public void setTdyODAmt(BigDecimal tdyODAmt) {
        this.tdyODAmt = tdyODAmt;
    }

    /**
     * 原币结转当日发生额贷方
     * @return TDY_O_CO_C_AMT 原币结转当日发生额贷方
     */
    public BigDecimal getTdyOCoCAmt() {
        return tdyOCoCAmt;
    }

    /**
     * 原币结转当日发生额贷方
     * @param tdyOCoCAmt 原币结转当日发生额贷方
     */
    public void setTdyOCoCAmt(BigDecimal tdyOCoCAmt) {
        this.tdyOCoCAmt = tdyOCoCAmt;
    }

    /**
     * 原币结转当日发生额借方
     * @return TDY_O_CO_D_AMT 原币结转当日发生额借方
     */
    public BigDecimal getTdyOCoDAmt() {
        return tdyOCoDAmt;
    }

    /**
     * 原币结转当日发生额借方
     * @param tdyOCoDAmt 原币结转当日发生额借方
     */
    public void setTdyOCoDAmt(BigDecimal tdyOCoDAmt) {
        this.tdyOCoDAmt = tdyOCoDAmt;
    }

    /**
     * 原币申购未实现平准金借
     * @return TDY_PUR_O_D_ULAMT 原币申购未实现平准金借
     */
    public BigDecimal getTdyPurODUlamt() {
        return tdyPurODUlamt;
    }

    /**
     * 原币申购未实现平准金借
     * @param tdyPurODUlamt 原币申购未实现平准金借
     */
    public void setTdyPurODUlamt(BigDecimal tdyPurODUlamt) {
        this.tdyPurODUlamt = tdyPurODUlamt;
    }

    /**
     * 原币赎回未实现平准金借
     * @return TDY_RED_O_D_ULAMT 原币赎回未实现平准金借
     */
    public BigDecimal getTdyRedODUlamt() {
        return tdyRedODUlamt;
    }

    /**
     * 原币赎回未实现平准金借
     * @param tdyRedODUlamt 原币赎回未实现平准金借
     */
    public void setTdyRedODUlamt(BigDecimal tdyRedODUlamt) {
        this.tdyRedODUlamt = tdyRedODUlamt;
    }

    /**
     * 原币赎回未实现平准金贷
     * @return TDY_RED_O_C_ULAMT 原币赎回未实现平准金贷
     */
    public BigDecimal getTdyRedOCUlamt() {
        return tdyRedOCUlamt;
    }

    /**
     * 原币赎回未实现平准金贷
     * @param tdyRedOCUlamt 原币赎回未实现平准金贷
     */
    public void setTdyRedOCUlamt(BigDecimal tdyRedOCUlamt) {
        this.tdyRedOCUlamt = tdyRedOCUlamt;
    }

    /**
     * 原币申购未实现平准金贷
     * @return TDY_PUR_O_C_ULAMT 原币申购未实现平准金贷
     */
    public BigDecimal getTdyPurOCUlamt() {
        return tdyPurOCUlamt;
    }

    /**
     * 原币申购未实现平准金贷
     * @param tdyPurOCUlamt 原币申购未实现平准金贷
     */
    public void setTdyPurOCUlamt(BigDecimal tdyPurOCUlamt) {
        this.tdyPurOCUlamt = tdyPurOCUlamt;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 数据状态 A-待处理 E-有效 D-删除
     * @return STATUS 数据状态 A-待处理 E-有效 D-删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态 A-待处理 E-有效 D-删除
     * @param status 数据状态 A-待处理 E-有效 D-删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * 创建用户
     * @return CREATE_USER 创建用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建用户
     * @param createUser 创建用户
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 更新用户
     * @return UPDATE_USER 更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新用户
     * @param updateUser 更新用户
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

	public String getMd5primarykey() {
		return md5primarykey;
	}

	public void setMd5primarykey(String md5primaryKey) {
		this.md5primarykey = md5primaryKey == null?null:md5primaryKey.trim();
	}
    
    
}