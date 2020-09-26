package com.joyintech.tams.valuation.batch.settlementinstruction.dto;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 结算指令明细信息DTO
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月8日
 * @see SettlementInstructionDTO
 * @since 1.0
 */
public class SettlementInstructionDTO {
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 发生日期
     */
    private Date happenDate;
    /**
     * 入账日期
     */
    private Date bookDate;
    /**
     * 账套日期
     */
    private Date booksetDate;
    /**
     * 录入日
     */
    private Date booksetDate1;
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    /**
     * 科目号
     */
    private String subjectNo;
    /**
     * 四级科目号
     */
    private String detailSubjectNo;
    /**
     * 是否计息
     */
    private String accIntFlag;
    /**
     * 是否允许透支
     */
    private String overdrawnFlag;
    /**
     * 指令编码
     */
    private String orderId;
    /**
     * 指令类型
     */
    private String orderType;
    /**
     * 指令序号
     */
    private String orderNum;
    /**
     * 结算日期
     */
    private Date settleDate;
    /**
     * 起息日
     */
    private String tradeId;
    /**
     * 本方资金账户
     */
    private String myAcctNo;
    
    /**
     * 业务明细类型  (本方资金账户类型)
     */
    private String busiDetailType;
    
    /**
     * 对方资金账户
     */
    private String counterAcctNo;
    
    /**
     * 对方资金账户类型
     */
    private String counterAcctNoType;
    
    /**
     * 收付方向
     */
    private String ps;
    /**
     * 结算金额
     */
    private BigDecimal settleAmt;
    /**
     * 结算状态
     */
    private String settleStatus;
    /**
     * 本位币
     */
    private String standardCcy;
    
    /**
     * 币种
     */
    private String ccy;
    /**
     * 业务明细代码
     */
    private String busiId;
    
    /**
     * 任务优先级
     */
    private Long taskPri;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品类型
     */
    private String finprodType;
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 登记托管机构
     */
    private String registOrg;
    /**
     * 对手方账户类型
     */
    private String acctType;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    
    public String getBooksetId() {
        return booksetId;
    }
    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    public String getBokDetailId() {
        return bokDetailId;
    }
    
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
    
    public Date getHappenDate() {
        return happenDate;
    }
    
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }
    
    public Date getBookDate() {
        return bookDate;
    }
    
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
    
    public Date getBooksetDate() {
        return booksetDate;
    }
    
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }
    
    public Date getBooksetDate1() {
        return booksetDate1;
    }
    
    public void setBooksetDate1(Date booksetDate1) {
        this.booksetDate1 = booksetDate1;
    }
    
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }
    
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
    
    public String getSubjectNo() {
        return subjectNo;
    }
    
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }
    
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }
    
    public String getAccIntFlag() {
        return accIntFlag;
    }
    
    public void setAccIntFlag(String accIntFlag) {
        this.accIntFlag = accIntFlag;
    }
    
    public String getOverdrawnFlag() {
        return overdrawnFlag;
    }
    
    public void setOverdrawnFlag(String overdrawnFlag) {
        this.overdrawnFlag = overdrawnFlag;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    public String getOrderNum() {
        return orderNum;
    }
    
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    public Date getSettleDate() {
        return settleDate;
    }
    
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
    
    public String getTradeId() {
        return tradeId;
    }
    
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    public String getMyAcctNo() {
        return myAcctNo;
    }
    
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }
    
    public String getPs() {
        return ps;
    }
    
    public void setPs(String ps) {
        this.ps = ps;
    }
    
    public String getCounterAcctNo() {
        return counterAcctNo;
    }
    
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }
    
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }
    
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }
    
    public String getSettleStatus() {
        return settleStatus;
    }
    
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }
    
    public String getStandardCcy() {
        return standardCcy;
    }
    
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    
    public String getCcy() {
        return ccy;
    }

    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    public String getBusiId() {
        return busiId;
    }

    
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    
    public String getBusiDetailType() {
        return busiDetailType;
    }

    
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    
    public Long getTaskPri() {
        return taskPri;
    }

    
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    
    public String getCounterAcctNoType() {
        return counterAcctNoType;
    }

    
    public void setCounterAcctNoType(String counterAcctNoType) {
        this.counterAcctNoType = counterAcctNoType;
    }

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public String getRegistOrg() {
		return registOrg;
	}

	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

}
