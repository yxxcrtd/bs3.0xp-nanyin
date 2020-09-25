/*
 * 文件名：InterfProdInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 产品申赎信息接口表数据dto
 *
 * @author etliu
 * @version 1.0 2018年12月26日
 * @see InterfProdInfo001Dto
 * @since 1.0
 */
public class InterfProdInfo001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6551836847085009291L;

    // 表字段以外项目start
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 分行号
     */
    private String branchId;

    /**
     * 449188接口.分行认购客户数、449191接口.分行认购客户数、449194接口.分行认购客户数、449186接口.分行认购客户数
     */
    private String branchbuynum;

    /**
     * 449181接口.分行募集金额、449183接口.募集、449283接口.募集、449186接口.分行募集份额
     */
    private String branchbuyamount;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 产品到期日
     */
    private String prodMdate;
    
    /**
     * 收益类型，净值、预期收益、货币、结构
     */
    private String profitType;
    // 表字段以外项目end

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 系统平台日期
     */
    private String cdate;

    /**
     * 产品名称
     */
    private String prodname;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 产品起息日 449188接口.成立日、449191接口.成立日、449194接口.成立日、449189接口.成立日、449195接口.开始日期
     */
    private String startdate;

    /**
     * 产品到期日 449188接口.到期日、449191接口.名义到期日、449194接口.名义到期日、449189接口.到期日、449195接口.名义结束日期
     */
    private String enddate;

    /**
     * 产品实际到期日 449188接口.实际到期日、449191接口.实际到期日、449189接口.实际到期日、449195接口.实际结束日期
     */
    private String actenddate;

    /**
     * 单位净值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal netvalue;

    /**
     * 滚动型产品，上期剩余金额计入本期 449181接口.上期滚入金额、449194接口.已到期滚入金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal preinamount;

    /**
     * 当日产品总剩余金额 449183接口.总本金、449283接口.总本金、449194接口.本期次总金额、449189接口.总成本本金、449195接口.本期次原始本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcurrentamount;

    /**
     * 当日产品总申购金额
     * 449181接口.本期新认购、449185接口.当日申购金额、449183接口.总募集、449283接口.总募集、449188接口.本期新认购、449191接口.本期新认购、449194接口.本期新认购、448108接口.上日申购金额、449186接口.上日申购金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalbuyamount;

    /**
     * 当日产品总赎回金额
     * 449181接口.本期新赎回、449183接口.总赎回、449283接口.总赎回、449194接口.当日新赎回、448108接口.上日赎回市值金额、449186接口.上日赎回市值金额、449189接口.总金额、449195接口.总交易金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totaldrawamount;

    /**
     * 产品赎回实际支付给客户的金额 448108接口.上日赎回给客户的金额、449186接口.上日赎回给客户的金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totaldrawcustamount;

    /**
     * 产品赎回支付的手续费 448108接口.上日赎回手续费、449186接口.上日赎回手续费
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalfeesamount;

    /**
     * 当日已经结算给客户的收益 449183接口.总已结转收益、449283接口.总已结转收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcarriedinterest;

    /**
     * 当日未结算给客户的收益 449183接口.总待结转收益、449283接口.总待结转收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalnotcarriedinterest;

    /**
     * 预约买入金额 449183接口.预约买入金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal reservebuyamt;

    /**
     * 预约赎回份额 449183接口.预约赎回份额、449185接口.当日赎回份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal reservedrawvol;

    /**
     * 红利再投金额 449183接口.月红利再投总份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcarriedvol;

    /**
     * 449283接口.总应付收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalpayableinterest;

    /**
     * 449283接口.总新计提收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalnewinterest;

    /**
     * 449283接口.总销户下发收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcloseinterest;

    /**
     * 449283接口.总定期结息（每月1号才有）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcarryinterest;

    /**
     * 产品的持有总份额 449185接口.当日净份额、448108接口.上日净份额、449186接口.上日净份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalcurrentquantity;

    /**
     * 当日申购的份额 448108接口.上日申购份额、449186接口.上日申购份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalbuyquantity;

    /**
     * 当日赎回的份额 、448108接口.上日赎回份额、449186接口.上日赎回份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totaldrawquantity;

    /**
     * 449195接口.总收益笔数
     */
    private Integer totalcount;

    /**
     * 到期总收益 449189接口.总收益、449195接口.总收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalprofitamount;

    /**
     * 产品类型：净值型，货币型，日添利，开放式阶梯等
     */
    private String prodtype;

    /**
     * 交易类型
     */
    private String pinanBusinessType;

    /**
     * 接口ID
     */
    private String interfId;

    /**
     * 取得 产品代码
     *
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     *
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 系统平台日期
     *
     * @return 系统平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 系统平台日期
     *
     * @param cdate 系统平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 产品名称
     *
     * @return 产品名称
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * 设置 产品名称
     *
     * @param prodname 产品名称
     */
    public void setProdname(String prodname) {
        this.prodname = prodname == null ? null : prodname.trim();
    }

    /**
     * 取得 币种
     *
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 产品起息日 449188接口.成立日、449191接口.成立日、449194接口.成立日、449189接口.成立日、449195接口.开始日期
     *
     * @return 产品起息日 449188接口.成立日、449191接口.成立日、449194接口.成立日、449189接口.成立日、449195接口.开始日期
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 产品起息日 449188接口.成立日、449191接口.成立日、449194接口.成立日、449189接口.成立日、449195接口.开始日期
     *
     * @param startdate 产品起息日 449188接口.成立日、449191接口.成立日、449194接口.成立日、449189接口.成立日、449195接口.开始日期
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    /**
     * 取得 产品到期日 449188接口.到期日、449191接口.名义到期日、449194接口.名义到期日、449189接口.到期日、449195接口.名义结束日期
     *
     * @return 产品到期日 449188接口.到期日、449191接口.名义到期日、449194接口.名义到期日、449189接口.到期日、449195接口.名义结束日期
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 产品到期日 449188接口.到期日、449191接口.名义到期日、449194接口.名义到期日、449189接口.到期日、449195接口.名义结束日期
     *
     * @param enddate 产品到期日 449188接口.到期日、449191接口.名义到期日、449194接口.名义到期日、449189接口.到期日、449195接口.名义结束日期
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    /**
     * 取得 产品实际到期日 449188接口.实际到期日、449191接口.实际到期日、449189接口.实际到期日、449195接口.实际结束日期
     *
     * @return 产品实际到期日 449188接口.实际到期日、449191接口.实际到期日、449189接口.实际到期日、449195接口.实际结束日期
     */
    public String getActenddate() {
        return actenddate;
    }

    /**
     * 设置 产品实际到期日 449188接口.实际到期日、449191接口.实际到期日、449189接口.实际到期日、449195接口.实际结束日期
     *
     * @param actenddate 产品实际到期日 449188接口.实际到期日、449191接口.实际到期日、449189接口.实际到期日、449195接口.实际结束日期
     */
    public void setActenddate(String actenddate) {
        this.actenddate = actenddate == null ? null : actenddate.trim();
    }

    /**
     * 取得 单位净值
     *
     * @return 单位净值
     */
    public BigDecimal getNetvalue() {
        return netvalue;
    }

    /**
     * 设置 单位净值
     *
     * @param netvalue 单位净值
     */
    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    /**
     * 取得 滚动型产品，上期剩余金额计入本期 449181接口.上期滚入金额、449194接口.已到期滚入金额
     *
     * @return 滚动型产品，上期剩余金额计入本期 449181接口.上期滚入金额、449194接口.已到期滚入金额
     */
    public BigDecimal getPreinamount() {
        return preinamount;
    }

    /**
     * 设置 滚动型产品，上期剩余金额计入本期 449181接口.上期滚入金额、449194接口.已到期滚入金额
     *
     * @param preinamount 滚动型产品，上期剩余金额计入本期 449181接口.上期滚入金额、449194接口.已到期滚入金额
     */
    public void setPreinamount(BigDecimal preinamount) {
        this.preinamount = preinamount;
    }

    /**
     * 取得 当日产品总剩余金额 449183接口.总本金、449283接口.总本金、449194接口.本期次总金额、449189接口.总成本本金、449195接口.本期次原始本金
     *
     * @return 当日产品总剩余金额 449183接口.总本金、449283接口.总本金、449194接口.本期次总金额、449189接口.总成本本金、449195接口.本期次原始本金
     */
    public BigDecimal getTotalcurrentamount() {
        return totalcurrentamount;
    }

    /**
     * 设置 当日产品总剩余金额 449183接口.总本金、449283接口.总本金、449194接口.本期次总金额、449189接口.总成本本金、449195接口.本期次原始本金
     *
     * @param totalcurrentamount 当日产品总剩余金额
     *            449183接口.总本金、449283接口.总本金、449194接口.本期次总金额、449189接口.总成本本金、449195接口.本期次原始本金
     */
    public void setTotalcurrentamount(BigDecimal totalcurrentamount) {
        this.totalcurrentamount = totalcurrentamount;
    }

    /**
     * 取得 当日产品总申购金额
     * 449181接口.本期新认购、449185接口.当日申购金额、449183接口.总募集、449283接口.总募集、449188接口.本期新认购、449191接口.本期新认购、449194接口.本期新认购、448108接口.上日申购金额、449186接口.上日申购金额
     *
     * @return 当日产品总申购金额
     *         449181接口.本期新认购、449185接口.当日申购金额、449183接口.总募集、449283接口.总募集、449188接口.本期新认购、449191接口.本期新认购、449194接口.本期新认购、448108接口.上日申购金额、449186接口.上日申购金额
     */
    public BigDecimal getTotalbuyamount() {
        return totalbuyamount;
    }

    /**
     * 设置 当日产品总申购金额
     * 449181接口.本期新认购、449185接口.当日申购金额、449183接口.总募集、449283接口.总募集、449188接口.本期新认购、449191接口.本期新认购、449194接口.本期新认购、448108接口.上日申购金额、449186接口.上日申购金额
     *
     * @param totalbuyamount 当日产品总申购金额
     *            449181接口.本期新认购、449185接口.当日申购金额、449183接口.总募集、449283接口.总募集、449188接口.本期新认购、449191接口.本期新认购、449194接口.本期新认购、448108接口.上日申购金额、449186接口.上日申购金额
     */
    public void setTotalbuyamount(BigDecimal totalbuyamount) {
        this.totalbuyamount = totalbuyamount;
    }

    /**
     * 取得 当日产品总赎回金额
     * 449181接口.本期新赎回、449183接口.总赎回、449283接口.总赎回、449194接口.当日新赎回、448108接口.上日赎回市值金额、449186接口.上日赎回市值金额、449189接口.总金额、449195接口.总交易金额
     *
     * @return 当日产品总赎回金额
     *         449181接口.本期新赎回、449183接口.总赎回、449283接口.总赎回、449194接口.当日新赎回、448108接口.上日赎回市值金额、449186接口.上日赎回市值金额、449189接口.总金额、449195接口.总交易金额
     */
    public BigDecimal getTotaldrawamount() {
        return totaldrawamount;
    }

    /**
     * 设置 当日产品总赎回金额
     * 449181接口.本期新赎回、449183接口.总赎回、449283接口.总赎回、449194接口.当日新赎回、448108接口.上日赎回市值金额、449186接口.上日赎回市值金额、449189接口.总金额、449195接口.总交易金额
     *
     * @param totaldrawamount 当日产品总赎回金额
     *            449181接口.本期新赎回、449183接口.总赎回、449283接口.总赎回、449194接口.当日新赎回、448108接口.上日赎回市值金额、449186接口.上日赎回市值金额、449189接口.总金额、449195接口.总交易金额
     */
    public void setTotaldrawamount(BigDecimal totaldrawamount) {
        this.totaldrawamount = totaldrawamount;
    }

    /**
     * 取得 产品赎回实际支付给客户的金额 448108接口.上日赎回给客户的金额、449186接口.上日赎回给客户的金额
     *
     * @return 产品赎回实际支付给客户的金额 448108接口.上日赎回给客户的金额、449186接口.上日赎回给客户的金额
     */
    public BigDecimal getTotaldrawcustamount() {
        return totaldrawcustamount;
    }

    /**
     * 设置 产品赎回实际支付给客户的金额 448108接口.上日赎回给客户的金额、449186接口.上日赎回给客户的金额
     *
     * @param totaldrawcustamount 产品赎回实际支付给客户的金额 448108接口.上日赎回给客户的金额、449186接口.上日赎回给客户的金额
     */
    public void setTotaldrawcustamount(BigDecimal totaldrawcustamount) {
        this.totaldrawcustamount = totaldrawcustamount;
    }

    /**
     * 取得 产品赎回支付的手续费 448108接口.上日赎回手续费、449186接口.上日赎回手续费
     *
     * @return 产品赎回支付的手续费 448108接口.上日赎回手续费、449186接口.上日赎回手续费
     */
    public BigDecimal getTotalfeesamount() {
        return totalfeesamount;
    }

    /**
     * 设置 产品赎回支付的手续费 448108接口.上日赎回手续费、449186接口.上日赎回手续费
     *
     * @param totalfeesamount 产品赎回支付的手续费 448108接口.上日赎回手续费、449186接口.上日赎回手续费
     */
    public void setTotalfeesamount(BigDecimal totalfeesamount) {
        this.totalfeesamount = totalfeesamount;
    }

    /**
     * 取得 当日已经结算给客户的收益 449183接口.总已结转收益、449283接口.总已结转收益
     *
     * @return 当日已经结算给客户的收益 449183接口.总已结转收益、449283接口.总已结转收益
     */
    public BigDecimal getTotalcarriedinterest() {
        return totalcarriedinterest;
    }

    /**
     * 设置 当日已经结算给客户的收益 449183接口.总已结转收益、449283接口.总已结转收益
     *
     * @param totalcarriedinterest 当日已经结算给客户的收益 449183接口.总已结转收益、449283接口.总已结转收益
     */
    public void setTotalcarriedinterest(BigDecimal totalcarriedinterest) {
        this.totalcarriedinterest = totalcarriedinterest;
    }

    /**
     * 取得 当日未结算给客户的收益 449183接口.总待结转收益、449283接口.总待结转收益
     *
     * @return 当日未结算给客户的收益 449183接口.总待结转收益、449283接口.总待结转收益
     */
    public BigDecimal getTotalnotcarriedinterest() {
        return totalnotcarriedinterest;
    }

    /**
     * 设置 当日未结算给客户的收益 449183接口.总待结转收益、449283接口.总待结转收益
     *
     * @param totalnotcarriedinterest 当日未结算给客户的收益 449183接口.总待结转收益、449283接口.总待结转收益
     */
    public void setTotalnotcarriedinterest(BigDecimal totalnotcarriedinterest) {
        this.totalnotcarriedinterest = totalnotcarriedinterest;
    }

    /**
     * 取得 预约买入金额 449183接口.预约买入金额
     *
     * @return 预约买入金额 449183接口.预约买入金额
     */
    public BigDecimal getReservebuyamt() {
        return reservebuyamt;
    }

    /**
     * 设置 预约买入金额 449183接口.预约买入金额
     *
     * @param reservebuyamt 预约买入金额 449183接口.预约买入金额
     */
    public void setReservebuyamt(BigDecimal reservebuyamt) {
        this.reservebuyamt = reservebuyamt;
    }

    /**
     * 取得 预约赎回份额 449183接口.预约赎回份额、449185接口.当日赎回份额
     *
     * @return 预约赎回份额 449183接口.预约赎回份额、449185接口.当日赎回份额
     */
    public BigDecimal getReservedrawvol() {
        return reservedrawvol;
    }

    /**
     * 设置 预约赎回份额 449183接口.预约赎回份额、449185接口.当日赎回份额
     *
     * @param reservedrawvol 预约赎回份额 449183接口.预约赎回份额、449185接口.当日赎回份额
     */
    public void setReservedrawvol(BigDecimal reservedrawvol) {
        this.reservedrawvol = reservedrawvol;
    }

    /**
     * 取得 红利再投金额 449183接口.月红利再投总份额
     *
     * @return 红利再投金额 449183接口.月红利再投总份额
     */
    public BigDecimal getTotalcarriedvol() {
        return totalcarriedvol;
    }

    /**
     * 设置 红利再投金额 449183接口.月红利再投总份额
     *
     * @param totalcarriedvol 红利再投金额 449183接口.月红利再投总份额
     */
    public void setTotalcarriedvol(BigDecimal totalcarriedvol) {
        this.totalcarriedvol = totalcarriedvol;
    }

    /**
     * 取得 449283接口.总应付收益
     *
     * @return 449283接口.总应付收益
     */
    public BigDecimal getTotalpayableinterest() {
        return totalpayableinterest;
    }

    /**
     * 设置 449283接口.总应付收益
     *
     * @param totalpayableinterest 449283接口.总应付收益
     */
    public void setTotalpayableinterest(BigDecimal totalpayableinterest) {
        this.totalpayableinterest = totalpayableinterest;
    }

    /**
     * 取得 449283接口.总新计提收益
     *
     * @return 449283接口.总新计提收益
     */
    public BigDecimal getTotalnewinterest() {
        return totalnewinterest;
    }

    /**
     * 设置 449283接口.总新计提收益
     *
     * @param totalnewinterest 449283接口.总新计提收益
     */
    public void setTotalnewinterest(BigDecimal totalnewinterest) {
        this.totalnewinterest = totalnewinterest;
    }

    /**
     * 取得 449283接口.总销户下发收益
     *
     * @return 449283接口.总销户下发收益
     */
    public BigDecimal getTotalcloseinterest() {
        return totalcloseinterest;
    }

    /**
     * 设置 449283接口.总销户下发收益
     *
     * @param totalcloseinterest 449283接口.总销户下发收益
     */
    public void setTotalcloseinterest(BigDecimal totalcloseinterest) {
        this.totalcloseinterest = totalcloseinterest;
    }

    /**
     * 取得 449283接口.总定期结息（每月1号才有）
     *
     * @return 449283接口.总定期结息（每月1号才有）
     */
    public BigDecimal getTotalcarryinterest() {
        return totalcarryinterest;
    }

    /**
     * 设置 449283接口.总定期结息（每月1号才有）
     *
     * @param totalcarryinterest 449283接口.总定期结息（每月1号才有）
     */
    public void setTotalcarryinterest(BigDecimal totalcarryinterest) {
        this.totalcarryinterest = totalcarryinterest;
    }

    /**
     * 取得 产品的持有总份额 449185接口.当日净份额、448108接口.上日净份额、449186接口.上日净份额
     *
     * @return 产品的持有总份额 449185接口.当日净份额、448108接口.上日净份额、449186接口.上日净份额
     */
    public BigDecimal getTotalcurrentquantity() {
        return totalcurrentquantity;
    }

    /**
     * 设置 产品的持有总份额 449185接口.当日净份额、448108接口.上日净份额、449186接口.上日净份额
     *
     * @param totalcurrentquantity 产品的持有总份额 449185接口.当日净份额、448108接口.上日净份额、449186接口.上日净份额
     */
    public void setTotalcurrentquantity(BigDecimal totalcurrentquantity) {
        this.totalcurrentquantity = totalcurrentquantity;
    }

    /**
     * 取得 当日申购的份额 448108接口.上日申购份额、449186接口.上日申购份额
     *
     * @return 当日申购的份额 448108接口.上日申购份额、449186接口.上日申购份额
     */
    public BigDecimal getTotalbuyquantity() {
        return totalbuyquantity;
    }

    /**
     * 设置 当日申购的份额 448108接口.上日申购份额、449186接口.上日申购份额
     *
     * @param totalbuyquantity 当日申购的份额 448108接口.上日申购份额、449186接口.上日申购份额
     */
    public void setTotalbuyquantity(BigDecimal totalbuyquantity) {
        this.totalbuyquantity = totalbuyquantity;
    }

    /**
     * 取得 当日赎回的份额 、448108接口.上日赎回份额、449186接口.上日赎回份额
     *
     * @return 当日赎回的份额 、448108接口.上日赎回份额、449186接口.上日赎回份额
     */
    public BigDecimal getTotaldrawquantity() {
        return totaldrawquantity;
    }

    /**
     * 设置 当日赎回的份额 、448108接口.上日赎回份额、449186接口.上日赎回份额
     *
     * @param totaldrawquantity 当日赎回的份额 、448108接口.上日赎回份额、449186接口.上日赎回份额
     */
    public void setTotaldrawquantity(BigDecimal totaldrawquantity) {
        this.totaldrawquantity = totaldrawquantity;
    }

    /**
     * 取得 449195接口.总收益笔数
     *
     * @return 449195接口.总收益笔数
     */
    public Integer getTotalcount() {
        return totalcount;
    }

    /**
     * 设置 449195接口.总收益笔数
     *
     * @param totalcount 449195接口.总收益笔数
     */
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    /**
     * 取得 到期总收益 449189接口.总收益、449195接口.总收益
     *
     * @return 到期总收益 449189接口.总收益、449195接口.总收益
     */
    public BigDecimal getTotalprofitamount() {
        return totalprofitamount;
    }

    /**
     * 设置 到期总收益 449189接口.总收益、449195接口.总收益
     *
     * @param totalprofitamount 到期总收益 449189接口.总收益、449195接口.总收益
     */
    public void setTotalprofitamount(BigDecimal totalprofitamount) {
        this.totalprofitamount = totalprofitamount;
    }

    /**
     * 取得 产品类型：净值型，货币型，日添利，开放式阶梯等
     *
     * @return 产品类型：净值型，货币型，日添利，开放式阶梯等
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 产品类型：净值型，货币型，日添利，开放式阶梯等
     *
     * @param prodtype 产品类型：净值型，货币型，日添利，开放式阶梯等
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 交易类型
     *
     * @return 交易类型
     */
    public String getPinanBusinessType() {
        return pinanBusinessType;
    }

    /**
     * 设置 交易类型
     *
     * @param pinanBusinessType 交易类型
     */
    public void setPinanBusinessType(String pinanBusinessType) {
        this.pinanBusinessType = pinanBusinessType == null ? null : pinanBusinessType.trim();
    }

    /**
     * 取得 接口ID
     *
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     *
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 branchId
     *
     * @return branchId.
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置 branchId
     *
     * @param branchId branchId
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 获取 branchbuynum
     *
     * @return branchbuynum.
     */
    public String getBranchbuynum() {
        return branchbuynum;
    }

    /**
     * 设置 branchbuynum
     *
     * @param branchbuynum branchbuynum
     */
    public void setBranchbuynum(String branchbuynum) {
        this.branchbuynum = branchbuynum;
    }

    /**
     * 获取 branchbuyamount
     *
     * @return branchbuyamount.
     */
    public String getBranchbuyamount() {
        return branchbuyamount;
    }

    /**
     * 设置 branchbuyamount
     *
     * @param branchbuyamount branchbuyamount
     */
    public void setBranchbuyamount(String branchbuyamount) {
        this.branchbuyamount = branchbuyamount;
    }

    /**
     * 获取 prodRegistCode
     *
     * @return prodRegistCode.
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 prodRegistCode
     *
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    /**
     * 获取 prodMdate
     * 
     * @return prodMdate.
     */
    public String getProdMdate() {
        return prodMdate;
    }

    /**
     * 设置 prodMdate
     * 
     * @param prodMdate prodMdate
     */
    public void setProdMdate(String prodMdate) {
        this.prodMdate = prodMdate;
    }

    /**
     * 取得 收益类型，净值、预期收益、货币、结构
     * @return 收益类型，净值、预期收益、货币、结构
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型，净值、预期收益、货币、结构
     * @param profitType 收益类型，净值、预期收益、货币、结构
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }
}
