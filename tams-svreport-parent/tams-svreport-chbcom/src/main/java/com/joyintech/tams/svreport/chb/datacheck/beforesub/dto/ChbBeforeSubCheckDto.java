package com.joyintech.tams.svreport.chb.datacheck.beforesub.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:产品终止登记报送前检查实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-17
 */
public class ChbBeforeSubCheckDto {

    private String prodid; //产品代码
    private String assetcode;   //资产代码
    private Date checkdate;   //核查日期
    private String termid;  //月报期数
    private Date vdate;   //起息日
    private Date mdate;   //到期日
    private Date posdate; //持仓日
    private int regcounts; //产品或资产已登记的数量
    private BigDecimal maxprice;    //最高交易价格
    private BigDecimal minprice;    //最低交易价格
    private BigDecimal holdqty; //产品或资产份额
    private BigDecimal cashamt; //剩余现金
    private BigDecimal bankprofit1; //银行收益1
    private BigDecimal bankprofit2; //银行收益2
    private BigDecimal prodprofit1; //产品收益1
    private BigDecimal prodprofit2; //产品收益2
    private BigDecimal prodyield;   //产品年化收益率
    private BigDecimal custprofit1; //客户收益1
    private BigDecimal custprofit2; //客户收益2
    private BigDecimal custyield;  //客户年化收益率    
    private BigDecimal diffrate1;   //偏差率1
    private BigDecimal diffrate2;  //偏差率2
    private String registseqno;  //登记序列
    
    public String getProdid() {
        return prodid;
    }
    
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
    
    public String getAssetcode() {
        return assetcode;
    }
    
    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }
    
    public Date getCheckdate() {
        return checkdate;
    }
    
    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }
    
    public String getTermid() {
        return termid;
    }
    
    public void setTermid(String termid) {
        this.termid = termid;
    }
    
    public Date getVdate() {
        return vdate;
    }
    
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
    
    public Date getMdate() {
        return mdate;
    }
    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    
    public Date getPosdate() {
        return posdate;
    }
    
    public void setPosdate(Date posdate) {
        this.posdate = posdate;
    }
    
    public int getRegcounts() {
        return regcounts;
    }
    
    public void setRegcounts(int regcounts) {
        this.regcounts = regcounts;
    }
    
    public BigDecimal getMaxprice() {
        return maxprice;
    }
    
    public void setMaxprice(BigDecimal maxprice) {
        this.maxprice = maxprice;
    }
    
    public BigDecimal getMinprice() {
        return minprice;
    }
    
    public void setMinprice(BigDecimal minprice) {
        this.minprice = minprice;
    }
    
    public BigDecimal getHoldqty() {
        return holdqty;
    }
    
    public void setHoldqty(BigDecimal holdqty) {
        this.holdqty = holdqty;
    }
    
    public BigDecimal getCashamt() {
        return cashamt;
    }
    
    public void setCashamt(BigDecimal cashamt) {
        this.cashamt = cashamt;
    }
    
    public BigDecimal getBankprofit1() {
        return bankprofit1;
    }
    
    public void setBankprofit1(BigDecimal bankprofit1) {
        this.bankprofit1 = bankprofit1;
    }
    
    public BigDecimal getBankprofit2() {
        return bankprofit2;
    }
    
    public void setBankprofit2(BigDecimal bankprofit2) {
        this.bankprofit2 = bankprofit2;
    }
    
    public BigDecimal getProdprofit1() {
        return prodprofit1;
    }
    
    public void setProdprofit1(BigDecimal prodprofit1) {
        this.prodprofit1 = prodprofit1;
    }
    
    public BigDecimal getProdprofit2() {
        return prodprofit2;
    }
    
    public void setProdprofit2(BigDecimal prodprofit2) {
        this.prodprofit2 = prodprofit2;
    }
    
    public BigDecimal getProdyield() {
        return prodyield;
    }
    
    public void setProdyield(BigDecimal prodyield) {
        this.prodyield = prodyield;
    }
    
    public BigDecimal getCustprofit1() {
        return custprofit1;
    }
    
    public void setCustprofit1(BigDecimal custprofit1) {
        this.custprofit1 = custprofit1;
    }
    
    public BigDecimal getCustprofit2() {
        return custprofit2;
    }
    
    public void setCustprofit2(BigDecimal custprofit2) {
        this.custprofit2 = custprofit2;
    }
    
    public BigDecimal getCustyield() {
        return custyield;
    }
    
    public void setCustyield(BigDecimal custyield) {
        this.custyield = custyield;
    }
    
    public BigDecimal getDiffrate1() {
        return diffrate1;
    }
    
    public void setDiffrate1(BigDecimal diffrate1) {
        this.diffrate1 = diffrate1;
    }
    
    public BigDecimal getDiffrate2() {
        return diffrate2;
    }
    
    public void setDiffrate2(BigDecimal diffrate2) {
        this.diffrate2 = diffrate2;
    }

	public String getRegistseqno() {
		return registseqno;
	}

	public void setRegistseqno(String registseqno) {
		this.registseqno = registseqno;
	}

    
}