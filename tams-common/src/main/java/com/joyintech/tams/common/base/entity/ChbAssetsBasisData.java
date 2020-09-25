package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_ASSETS_BASIS_DATA表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-05-10
 */
public class ChbAssetsBasisData extends BaseEntity{
    /**
     * 主键
     */
    private String valId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 币种
     */
    private String ccy;

    /**
     * 日期
     */
    private Date valueDate;

    /**
     * 资产到期日
     */
    private Date mdate;

    /**
     * 当前持仓
     */
    private BigDecimal posAmt;

    /**
     * 当日成本
     */
    private BigDecimal tdyCostAmt;

    /**
     * 当日公允价值变动
     */
    private BigDecimal tdyFloatIngpl;

    /**
     * 当日累计应计利息余额
     */
    private BigDecimal tdyIntincexp;

    /**
     * 待清算款
     */
    private BigDecimal delayPayAmt;

    /**
     * 备注
     */
    private String remark;

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
     * 部门代码
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
     * 状态 A-待处理 E-有效 D-删除
     */
    private String status;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 业务主键md5
     */
    private String md5primarykey;
    /**
     * 通道代码（接特殊资产（如：项目类，计划类利率型，存款类）标的挂通道 用 ）
     */
    private String chlId;
    

    public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	/**
     * 主键
     * @return VAL_ID 主键
     */
    public String getValId() {
        return valId;
    }

    /**
     * 主键
     * @param valId 主键
     */
    public void setValId(String valId) {
        this.valId = valId == null ? null : valId.trim();
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
     * 资产代码
     * @return ASSET_ID 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 日期
     * @return VALUE_DATE 日期
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 日期
     * @param valueDate 日期
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 资产到期日
     * @return MDATE 资产到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 资产到期日
     * @param mdate 资产到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 当前持仓
     * @return POS_AMT 当前持仓
     */
    public BigDecimal getPosAmt() {
        return posAmt;
    }

    /**
     * 当前持仓
     * @param posAmt 当前持仓
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 当日成本
     * @return TDY_COST_AMT 当日成本
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    /**
     * 当日成本
     * @param tdyCostAmt 当日成本
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    /**
     * 当日公允价值变动
     * @return TDY_FLOAT_INGPL 当日公允价值变动
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 当日公允价值变动
     * @param tdyFloatIngpl 当日公允价值变动
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    /**
     * 当日累计应计利息余额
     * @return TDY_INTINCEXP 当日累计应计利息余额
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 当日累计应计利息余额
     * @param tdyIntincexp 当日累计应计利息余额
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 待清算款
     * @return DELAY_PAY_AMT 待清算款
     */
    public BigDecimal getDelayPayAmt() {
        return delayPayAmt;
    }

    /**
     * 待清算款
     * @param delayPayAmt 待清算款
     */
    public void setDelayPayAmt(BigDecimal delayPayAmt) {
        this.delayPayAmt = delayPayAmt;
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
     * 部门代码
     * @return ORG_CODE 部门代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 部门代码
     * @param orgCode 部门代码
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
     * 状态 A-待处理 E-有效 D-删除
     * @return STATUS 状态 A-待处理 E-有效 D-删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 A-待处理 E-有效 D-删除
     * @param status 状态 A-待处理 E-有效 D-删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    /**
     * 业务主键md5
     * @return MD5PRIMARYKEY 业务主键md5
     */
    public String getMd5primarykey() {
        return md5primarykey;
    }

    /**
     * 业务主键md5
     * @param md5primarykey 业务主键md5
     */
    public void setMd5primarykey(String md5primarykey) {
        this.md5primarykey = md5primarykey == null ? null : md5primarykey.trim();
    }
    /**
     * 币种
     * @return ccy 币种
     */
	public String getCcy() {
		return ccy;
	}
	 /**
     * 币种
     * @param ccy 币种
     */
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
    
}