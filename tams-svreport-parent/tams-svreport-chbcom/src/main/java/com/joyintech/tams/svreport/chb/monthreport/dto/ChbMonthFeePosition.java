package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_MONTH_FEE_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-24
 */
public class ChbMonthFeePosition {
    /**
     * null
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 报表开始日期
     */
    private Date startdate;

    /**
     * 报表结束日期
     */
    private Date enddate;

    /**
     * 统计日期
     */
    private Date bookdate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 募集方式
     */
    private String raisemode;

    /**
     * 投资性质
     */
    private String ivstorientation;

    /**
     * 本期银行实现收益总额
     */
    private BigDecimal bankamt;

    /**
     * 本期本机构托管费
     */
    private BigDecimal trustfeeamt;

    /**
     * 本期本机构销售服务费
     */
    private BigDecimal salefeeamt;

    /**
     * 本期本机构其他费用
     */
    private BigDecimal otherfeeamt;

    /**
     * 本期本机构固定管理
     */
    private BigDecimal manafeeamt;

    /**
     * 本期浮动费/中收
     */
    private BigDecimal floatfeeamt;

    /**
     * 产品收益标识
     */
    private String profitType;

    /**
     * 结构性产品挂钩标的
     */
    private String conPrdTraget;
    
    //add by leipeng 20200606

    /**
     * 机构编号
     */
    private String  institutionCode;
    

    public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
    //add by leipeng 20200606

	/**
     * null
     * @return POSUUID null
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * null
     * @param posuuid null
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 产品代码
     * @return PRODID 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 报表开始日期
     * @return STARTDATE 报表开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 报表开始日期
     * @param startdate 报表开始日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 报表结束日期
     * @return ENDDATE 报表结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 报表结束日期
     * @param enddate 报表结束日期
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 统计日期
     * @return BOOKDATE 统计日期
     */
    public Date getBookdate() {
        return bookdate;
    }

    /**
     * 统计日期
     * @param bookdate 统计日期
     */
    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    /**
     * 币种
     * @return CCY 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 募集方式
     * @return RAISEMODE 募集方式
     */
    public String getRaisemode() {
        return raisemode;
    }

    /**
     * 募集方式
     * @param raisemode 募集方式
     */
    public void setRaisemode(String raisemode) {
        this.raisemode = raisemode == null ? null : raisemode.trim();
    }

    /**
     * 投资性质
     * @return IVSTORIENTATION 投资性质
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 投资性质
     * @param ivstorientation 投资性质
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 本期银行实现收益总额
     * @return BANKAMT 本期银行实现收益总额
     */
    public BigDecimal getBankamt() {
        return bankamt;
    }

    /**
     * 本期银行实现收益总额
     * @param bankamt 本期银行实现收益总额
     */
    public void setBankamt(BigDecimal bankamt) {
        this.bankamt = bankamt;
    }

    /**
     * 本期本机构托管费
     * @return TRUSTFEEAMT 本期本机构托管费
     */
    public BigDecimal getTrustfeeamt() {
        return trustfeeamt;
    }

    /**
     * 本期本机构托管费
     * @param trustfeeamt 本期本机构托管费
     */
    public void setTrustfeeamt(BigDecimal trustfeeamt) {
        this.trustfeeamt = trustfeeamt;
    }

    /**
     * 本期本机构销售服务费
     * @return SALEFEEAMT 本期本机构销售服务费
     */
    public BigDecimal getSalefeeamt() {
        return salefeeamt;
    }

    /**
     * 本期本机构销售服务费
     * @param salefeeamt 本期本机构销售服务费
     */
    public void setSalefeeamt(BigDecimal salefeeamt) {
        this.salefeeamt = salefeeamt;
    }

    /**
     * 本期本机构其他费用
     * @return OTHERFEEAMT 本期本机构其他费用
     */
    public BigDecimal getOtherfeeamt() {
        return otherfeeamt;
    }

    /**
     * 本期本机构其他费用
     * @param otherfeeamt 本期本机构其他费用
     */
    public void setOtherfeeamt(BigDecimal otherfeeamt) {
        this.otherfeeamt = otherfeeamt;
    }

    /**
     * 本期本机构固定管理
     * @return MANAFEEAMT 本期本机构固定管理
     */
    public BigDecimal getManafeeamt() {
        return manafeeamt;
    }

    /**
     * 本期本机构固定管理
     * @param manafeeamt 本期本机构固定管理
     */
    public void setManafeeamt(BigDecimal manafeeamt) {
        this.manafeeamt = manafeeamt;
    }

    /**
     * 本期浮动费/中收
     * @return FLOATFEEAMT 本期浮动费/中收
     */
    public BigDecimal getFloatfeeamt() {
        return floatfeeamt;
    }

    /**
     * 本期浮动费/中收
     * @param floatfeeamt 本期浮动费/中收
     */
    public void setFloatfeeamt(BigDecimal floatfeeamt) {
        this.floatfeeamt = floatfeeamt;
    }

    /**
     * 产品收益标识
     * @return PROFIT_TYPE 产品收益标识
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 产品收益标识
     * @param profitType 产品收益标识
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 结构性产品挂钩标的
     * @return CON_PRD_TRAGET 结构性产品挂钩标的
     */
    public String getConPrdTraget() {
        return conPrdTraget;
    }

    /**
     * 结构性产品挂钩标的
     * @param conPrdTraget 结构性产品挂钩标的
     */
    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget == null ? null : conPrdTraget.trim();
    }
}