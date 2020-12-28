package com.joyintech.tams.svreport.position.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_PRD_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-24
 */
public class ChbPrdPosition {
    /**
     * 生成日
     */
    private Date cdate;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 发行机构代码
     */
    private String issuePartyId;
    
    /**
     * 币种
     */
    private String ccy;

    /**
     * 新增份额(赎回、提前到期为负，其他为正) 
     */
    private BigDecimal tdaddQty;

    /**
     * 新增金额(赎回、提前到期为负，其他为正)
     */
    private BigDecimal tdaddAmt;

    /**
     * 新增成本(赎回、提前到期按照移动平均算成本)
     */
    private BigDecimal tdaddCost;

    /**
     * 剩余份额 
     */
    private BigDecimal balanceQty;

    /**
     * 剩余成本(折算人民币)
     */
    private BigDecimal balanceAmt;

    /**
     * 累计收益
     */
    private BigDecimal totalIntamt;

    /**
     * 今日新增收益=ABS(金额)-ABS(成本)
     */
    private BigDecimal tdaddIntamt;

    /**
     * 累计净值(折算人民币)
     */
    private BigDecimal totalUnitbonus;
    
    /**
     * 机构代码
     */
    private String orgCode;
    
    /**
     * "部门代码
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
     * 个人投资者总数
     */
    private BigDecimal singletotal;
    /**
     * 法人投资者总数
     */
    private BigDecimal legaltotal;
    /**
     * 非法人投资者总数
     */
    private BigDecimal unlegaltotal;
    /**
     * 销售区域
     */
    private String buyplace;
    /**
     * 代销总金额
     */
    private BigDecimal consignamt;

    /**
     * 备注
     */
    private String remark;
    /**
     * 父产品代码
     */
    /**
     * 客户二级类型
     */
    private String customerType2;
    /**
     * 保有客户数
     */
    private BigDecimal custCounts;
    private String fprodId;
    private BigDecimal custamt;// 兑付客户总金额
    private BigDecimal custqty;// 兑付客户总份额
    private BigDecimal raiseamt;//    实际募集金额

    /**
     * 起息日
     */
    private Date vDate;

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
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
        this.cdate = cdate == null ? null : cdate;
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
     * 识别标识
     * @return INDENTIFY_MARK 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 币种
     * @return ccy
     */
    public String getCcy() {
		return ccy;
	}

    /**
     * 币种
     * @param ccy
     */
	public void setCcy(String ccy) {
		this.ccy = ccy == null ? null : ccy.trim();
	}

	/**
     * 新增份额(赎回、提前到期为负，其他为正) 
     * @return TDADD_QTY 新增份额(赎回、提前到期为负，其他为正) 
     */
    public BigDecimal getTdaddQty() {
        return tdaddQty;
    }

    /**
     * 新增份额(赎回、提前到期为负，其他为正) 
     * @param tdaddQty 新增份额(赎回、提前到期为负，其他为正) 
     */
    public void setTdaddQty(BigDecimal tdaddQty) {
        this.tdaddQty = tdaddQty;
    }

    /**
     * 新增金额(赎回、提前到期为负，其他为正)
     * @return TDADD_AMT 新增金额(赎回、提前到期为负，其他为正)
     */
    public BigDecimal getTdaddAmt() {
        return tdaddAmt;
    }

    /**
     * 新增金额(赎回、提前到期为负，其他为正)
     * @param tdaddAmt 新增金额(赎回、提前到期为负，其他为正)
     */
    public void setTdaddAmt(BigDecimal tdaddAmt) {
        this.tdaddAmt = tdaddAmt;
    }

    /**
     * 新增成本(赎回、提前到期按照移动平均算成本)
     * @return TDADD_COST 新增成本(赎回、提前到期按照移动平均算成本)
     */
    public BigDecimal getTdaddCost() {
        return tdaddCost;
    }

    /**
     * 新增成本(赎回、提前到期按照移动平均算成本)
     * @param tdaddCost 新增成本(赎回、提前到期按照移动平均算成本)
     */
    public void setTdaddCost(BigDecimal tdaddCost) {
        this.tdaddCost = tdaddCost;
    }

    /**
     * 剩余份额 
     * @return BALANCE_QTY 剩余份额 
     */
    public BigDecimal getBalanceQty() {
        return balanceQty;
    }

    /**
     * 剩余份额 
     * @param balanceQty 剩余份额 
     */
    public void setBalanceQty(BigDecimal balanceQty) {
        this.balanceQty = balanceQty;
    }

    /**
     * 剩余成本(折算人民币)
     * @return BALANCE_AMT 剩余成本(折算人民币)
     */
    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * 剩余成本(折算人民币)
     * @param balanceAmt 剩余成本(折算人民币)
     */
    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    /**
     * 今日新增收益=ABS(金额)-ABS(成本)
     * @return TDADD_INTAMT 今日新增收益=ABS(金额)-ABS(成本)
     */
    public BigDecimal getTdaddIntamt() {
        return tdaddIntamt;
    }

    /**
     * 今日新增收益=ABS(金额)-ABS(成本)
     * @param tdaddIntamt 今日新增收益=ABS(金额)-ABS(成本)
     */
    public void setTdaddIntamt(BigDecimal tdaddIntamt) {
        this.tdaddIntamt = tdaddIntamt;
    }
    

    public BigDecimal getTotalIntamt() {
		return totalIntamt;
	}

	public void setTotalIntamt(BigDecimal totalIntamt) {
		this.totalIntamt = totalIntamt;
	}

	/**
     * 累计净值(折算人民币)
     * @return TOTALDRAW_UNITBONUS 累计净值(折算人民币)
     */
    public BigDecimal getTotalUnitbonus() {
        return totalUnitbonus;
    }

    /**
     * 累计净值(折算人民币)
     * @param totaldrawUnitbonus 累计净值(折算人民币)
     */
    public void setTotalUnitbonus(BigDecimal totalUnitbonus) {
        this.totalUnitbonus = totalUnitbonus;
    }

    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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

	public BigDecimal getSingletotal() {
		return singletotal;
	}

	public void setSingletotal(BigDecimal singletotal) {
		this.singletotal = singletotal;
	}

	public BigDecimal getLegaltotal() {
		return legaltotal;
	}

	public void setLegaltotal(BigDecimal legaltotal) {
		this.legaltotal = legaltotal;
	}

	public BigDecimal getUnlegaltotal() {
		return unlegaltotal;
	}

	public void setUnlegaltotal(BigDecimal unlegaltotal) {
		this.unlegaltotal = unlegaltotal;
	}

	public String getBuyplace() {
		return buyplace;
	}

	public void setBuyplace(String buyplace) {
		this.buyplace = buyplace== null ? null : buyplace.trim();
	}

	public BigDecimal getConsignamt() {
		return consignamt;
	}

	public void setConsignamt(BigDecimal consignamt) {
		this.consignamt = consignamt;
	}
	/**
     * 父产品代码
     * @return FPROD_ID 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

	public String getCustomerType2() {
		return customerType2;
	}

	public void setCustomerType2(String customerType2) {
		this.customerType2 = customerType2;
	}

	public BigDecimal getCustCounts() {
		return custCounts == null ? BigDecimal.ZERO : custCounts;
	}

	public void setCustCounts(BigDecimal custCounts) {
		this.custCounts = custCounts;
	}

	public BigDecimal getCustamt() {
		return custamt;
	}

	public void setCustamt(BigDecimal custamt) {
		this.custamt = custamt;
	}

	public BigDecimal getCustqty() {
		return custqty;
	}

	public void setCustqty(BigDecimal custqty) {
		this.custqty = custqty;
	}

	public BigDecimal getRaiseamt() {
		return raiseamt;
	}

	public void setRaiseamt(BigDecimal raiseamt) {
		this.raiseamt = raiseamt;
	}
    
}