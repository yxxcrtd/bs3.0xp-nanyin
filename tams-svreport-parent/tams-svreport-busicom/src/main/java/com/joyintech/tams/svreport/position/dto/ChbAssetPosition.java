package com.joyintech.tams.svreport.position.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbAssetPosition {
    /**
     * 头寸ID
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 投资类型
     */
    private String investType;
    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 交易金额
     */
    private BigDecimal dealamt;

    /**
     * 交易份额
     */
    private BigDecimal dealqty;

    /**
     * 剩余金额
     */
    private BigDecimal accuamt;

    /**
     * 剩余本金
     */
    private BigDecimal accuqty;

    /**
     * 生成日
     */
    private Date cdate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建人
     */
    private String createUser;

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
     * 是否资管计划：01－是，02－否
     */
    private String isfinaplan;
    /**
     * 是否资管计划：01－是，02－否
     */
    private String isundertrade;
    /**
     * 投资品种
     */
    private String investVariety;
    /**
     * 债券面值
     */
    private BigDecimal facevalue;
    
    /**
     * 资产流动类型
     */
    private String curassettype;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 头寸ID
     * @return POSUUID 头寸ID
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 头寸ID
     * @param posuuid 头寸ID
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
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
     * @return ASSET_CODE 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 投资类型
     * @return INVEST_TYPE 投资类型
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 投资类型
     * @param investType 投资类型
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 通道代码
     * @return CHL_ID 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 交易金额
     * @return DEALAMT 交易金额
     */
    public BigDecimal getDealamt() {
        return dealamt;
    }

    /**
     * 交易金额
     * @param dealamt 交易金额
     */
    public void setDealamt(BigDecimal dealamt) {
        this.dealamt = dealamt;
    }

    /**
     * 交易份额
     * @return DEALQTY 交易份额
     */
    public BigDecimal getDealqty() {
        return dealqty;
    }

    /**
     * 交易份额
     * @param dealqty 交易份额
     */
    public void setDealqty(BigDecimal dealqty) {
        this.dealqty = dealqty;
    }

    /**
     * 剩余金额
     * @return ACCUAMT 剩余金额
     */
    public BigDecimal getAccuamt() {
        return accuamt;
    }

    /**
     * 剩余金额
     * @param accuamt 剩余金额
     */
    public void setAccuamt(BigDecimal accuamt) {
        this.accuamt = accuamt;
    }

    /**
     * 剩余本金
     * @return ACCUQTY 剩余本金
     */
    public BigDecimal getAccuqty() {
        return accuqty;
    }

    /**
     * 剩余本金
     * @param accuqty 剩余本金
     */
    public void setAccuqty(BigDecimal accuqty) {
        this.accuqty = accuqty;
    }

    /**
     * 生成日
     * @return CDATE 生成日
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 生成日
     * @param cdate 生成日
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
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

    public String getIsfinaplan() {
        return isfinaplan;
    }

    public void setIsfinaplan(String isfinaplan) {
        this.isfinaplan = isfinaplan;
    }

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getIsundertrade() {
		return isundertrade;
	}

	public void setIsundertrade(String isundertrade) {
		this.isundertrade = isundertrade;
	}

	public String getInvestVariety() {
		return investVariety;
	}

	public void setInvestVariety(String investVariety) {
		this.investVariety = investVariety;
	}

	public BigDecimal getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(BigDecimal facevalue) {
		this.facevalue = facevalue;
	}

	public String getCurassettype() {
		return curassettype;
	}

	public void setCurassettype(String curassettype) {
		this.curassettype = curassettype;
	}

    public String getCcy() {
        return ccy;
    }
    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
}