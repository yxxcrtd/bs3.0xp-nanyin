package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbMonthPrdPosition extends BaseEntity {
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
     * 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     */
    private String ccy;

    /**
     * 募集方式 01：公募 02：私募
     */
    private String raisemode;

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    private String termtype;

    /**
     * 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    private String operateMode;

    /**
     * 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    private String profitType;

    /**
     * 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    private String saleTarget;

    /**
     * 结构性产品挂钩标的
     */
    private String conPrdTraget;

    /**
     * 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    private String ordIdentified;

    /**
     * 本期总募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyamtsum;

    /**
     * 本期净募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cleanamtsum;

    /**
     * 本期赎回本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redeemamt;

    /**
     * 提前终止/到期本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal terminationamt;

    /**
     * 期末余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endamtsumOth;

    /**
     * 本期客户实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customeramt;

    /**
     * 本期赎回利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redeemintamt;

    /**
     * 本期提前终止/到期 利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal terminationintamt;

    /**
     * 本期现金分红/中间付息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashamt;

    /**
     * 存续状态 01存续/02终止/03未起息
     */
    private String durationflag;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 开放期限 01每日/02 180天以上/03 其他
     */
    private String openduration;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 是否现金管理类
     */
    private String cashManaCate;

    /**
     * 本期兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal payamtsum;

    /**
     * 封闭式产品期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closetermday;

    /**
     * 加权率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldE;

    /**
     * 取得 null
     * @return null
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 设置 null
     * @param posuuid null
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 报表开始日期
     * @return 报表开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置 报表开始日期
     * @param startdate 报表开始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 取得 报表结束日期
     * @return 报表结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 设置 报表结束日期
     * @param enddate 报表结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 取得 统计日期
     * @return 统计日期
     */
    public Date getBookdate() {
        return bookdate;
    }

    /**
     * 设置 统计日期
     * @param bookdate 统计日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    /**
     * 取得 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     * @return 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     * @param ccy 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 募集方式 01：公募 02：私募
     * @return 募集方式 01：公募 02：私募
     */
    public String getRaisemode() {
        return raisemode;
    }

    /**
     * 设置 募集方式 01：公募 02：私募
     * @param raisemode 募集方式 01：公募 02：私募
     */
    public void setRaisemode(String raisemode) {
        this.raisemode = raisemode == null ? null : raisemode.trim();
    }

    /**
     * 取得 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 设置 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 取得 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     * @return 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 设置 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     * @param termtype 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 取得 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @return 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @param operateMode 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @return 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @param profitType 产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @return 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public String getSaleTarget() {
        return saleTarget;
    }

    /**
     * 设置 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @param saleTarget 客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public void setSaleTarget(String saleTarget) {
        this.saleTarget = saleTarget == null ? null : saleTarget.trim();
    }

    /**
     * 取得 结构性产品挂钩标的
     * @return 结构性产品挂钩标的
     */
    public String getConPrdTraget() {
        return conPrdTraget;
    }

    /**
     * 设置 结构性产品挂钩标的
     * @param conPrdTraget 结构性产品挂钩标的
     */
    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget == null ? null : conPrdTraget.trim();
    }

    /**
     * 取得 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     * @return 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    public String getOrdIdentified() {
        return ordIdentified;
    }

    /**
     * 设置 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     * @param ordIdentified 投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构
     */
    public void setOrdIdentified(String ordIdentified) {
        this.ordIdentified = ordIdentified == null ? null : ordIdentified.trim();
    }

    /**
     * 取得 本期总募集金额
     * @return 本期总募集金额
     */
    public BigDecimal getDirtyamtsum() {
        return dirtyamtsum;
    }

    /**
     * 设置 本期总募集金额
     * @param dirtyamtsum 本期总募集金额
     */
    public void setDirtyamtsum(BigDecimal dirtyamtsum) {
        this.dirtyamtsum = dirtyamtsum;
    }

    /**
     * 取得 本期净募集金额
     * @return 本期净募集金额
     */
    public BigDecimal getCleanamtsum() {
        return cleanamtsum;
    }

    /**
     * 设置 本期净募集金额
     * @param cleanamtsum 本期净募集金额
     */
    public void setCleanamtsum(BigDecimal cleanamtsum) {
        this.cleanamtsum = cleanamtsum;
    }

    /**
     * 取得 本期赎回本金
     * @return 本期赎回本金
     */
    public BigDecimal getRedeemamt() {
        return redeemamt;
    }

    /**
     * 设置 本期赎回本金
     * @param redeemamt 本期赎回本金
     */
    public void setRedeemamt(BigDecimal redeemamt) {
        this.redeemamt = redeemamt;
    }

    /**
     * 取得 提前终止/到期本金
     * @return 提前终止/到期本金
     */
    public BigDecimal getTerminationamt() {
        return terminationamt;
    }

    /**
     * 设置 提前终止/到期本金
     * @param terminationamt 提前终止/到期本金
     */
    public void setTerminationamt(BigDecimal terminationamt) {
        this.terminationamt = terminationamt;
    }

    /**
     * 取得 期末余额
     * @return 期末余额
     */
    public BigDecimal getEndamtsumOth() {
        return endamtsumOth;
    }

    /**
     * 设置 期末余额
     * @param endamtsumOth 期末余额
     */
    public void setEndamtsumOth(BigDecimal endamtsumOth) {
        this.endamtsumOth = endamtsumOth;
    }

    /**
     * 取得 本期客户实现收益总额
     * @return 本期客户实现收益总额
     */
    public BigDecimal getCustomeramt() {
        return customeramt;
    }

    /**
     * 设置 本期客户实现收益总额
     * @param customeramt 本期客户实现收益总额
     */
    public void setCustomeramt(BigDecimal customeramt) {
        this.customeramt = customeramt;
    }

    /**
     * 取得 本期赎回利息
     * @return 本期赎回利息
     */
    public BigDecimal getRedeemintamt() {
        return redeemintamt;
    }

    /**
     * 设置 本期赎回利息
     * @param redeemintamt 本期赎回利息
     */
    public void setRedeemintamt(BigDecimal redeemintamt) {
        this.redeemintamt = redeemintamt;
    }

    /**
     * 取得 本期提前终止/到期 利息
     * @return 本期提前终止/到期 利息
     */
    public BigDecimal getTerminationintamt() {
        return terminationintamt;
    }

    /**
     * 设置 本期提前终止/到期 利息
     * @param terminationintamt 本期提前终止/到期 利息
     */
    public void setTerminationintamt(BigDecimal terminationintamt) {
        this.terminationintamt = terminationintamt;
    }

    /**
     * 取得 本期现金分红/中间付息
     * @return 本期现金分红/中间付息
     */
    public BigDecimal getCashamt() {
        return cashamt;
    }

    /**
     * 设置 本期现金分红/中间付息
     * @param cashamt 本期现金分红/中间付息
     */
    public void setCashamt(BigDecimal cashamt) {
        this.cashamt = cashamt;
    }

    /**
     * 取得 存续状态 01存续/02终止/03未起息
     * @return 存续状态 01存续/02终止/03未起息
     */
    public String getDurationflag() {
        return durationflag;
    }

    /**
     * 设置 存续状态 01存续/02终止/03未起息
     * @param durationflag 存续状态 01存续/02终止/03未起息
     */
    public void setDurationflag(String durationflag) {
        this.durationflag = durationflag == null ? null : durationflag.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 开放期限 01每日/02 180天以上/03 其他
     * @return 开放期限 01每日/02 180天以上/03 其他
     */
    public String getOpenduration() {
        return openduration;
    }

    /**
     * 设置 开放期限 01每日/02 180天以上/03 其他
     * @param openduration 开放期限 01每日/02 180天以上/03 其他
     */
    public void setOpenduration(String openduration) {
        this.openduration = openduration == null ? null : openduration.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 是否现金管理类
     * @return 是否现金管理类
     */
    public String getCashManaCate() {
        return cashManaCate;
    }

    /**
     * 设置 是否现金管理类
     * @param cashManaCate 是否现金管理类
     */
    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate == null ? null : cashManaCate.trim();
    }

    /**
     * 取得 本期兑付金额
     * @return 本期兑付金额
     */
    public BigDecimal getPayamtsum() {
        return payamtsum;
    }

    /**
     * 设置 本期兑付金额
     * @param payamtsum 本期兑付金额
     */
    public void setPayamtsum(BigDecimal payamtsum) {
        this.payamtsum = payamtsum;
    }

    /**
     * 取得 封闭式产品期限
     * @return 封闭式产品期限
     */
    public BigDecimal getClosetermday() {
        return closetermday;
    }

    /**
     * 设置 封闭式产品期限
     * @param closetermday 封闭式产品期限
     */
    public void setClosetermday(BigDecimal closetermday) {
        this.closetermday = closetermday;
    }

    /**
     * 取得 加权率
     * @return 加权率
     */
    public BigDecimal getYieldE() {
        return yieldE;
    }

    /**
     * 设置 加权率
     * @param yieldE 加权率
     */
    public void setYieldE(BigDecimal yieldE) {
        this.yieldE = yieldE;
    }
}