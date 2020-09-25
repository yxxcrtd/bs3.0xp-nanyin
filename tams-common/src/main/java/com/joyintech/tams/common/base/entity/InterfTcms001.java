package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfTcms001 extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 产品简称
     */
    private String shortname;

    /**
     * 产品名称
     */
    private String prodname;

    /**
     * 资产池
     */
    private String assetpool;

    /**
     * 销售部门
     */
    private String mktdeptno;

    /**
     * 产品一级分类
     */
    private String prodtype1;

    /**
     * 销售平台
     */
    private String mktplat;

    /**
     * 产品处理模式
     */
    private String prodmode;

    /**
     * 收益分配类别
     */
    private String indistype;

    /**
     * 滚动模式
     */
    private String rollmode;

    /**
     * 管理模式
     */
    private String incomemode;

    /**
     * 滚动期数
     */
    private Short rollamt;

    /**
     * 本期天数
     */
    private Short syperiod;

    /**
     * 发行方式
     */
    private String issuetype;

    /**
     * 客户名称
     */
    private String cusname;

    /**
     * 分行
     */
    private String branch;

    /**
     * 投资方向
     */
    private String investscope;

    /**
     * 产品成立日
     */
    private String vdate;

    /**
     * 产品到期日
     */
    private String mdate;

    /**
     * 产品期限
     */
    private Short term;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 预期收益率（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 方案代码
     */
    private String solutionid;

    /**
     * 认购币种
     */
    private String subccy;

    /**
     * 收益币种
     */
    private String settccy;

    /**
     * 付息周期
     */
    private String intpaycycle;

    /**
     * 销售费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal syield;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hyield;

    /**
     * 资产管理费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amyield;

    /**
     * 其他费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oyield;

    /**
     * 信息公布日
     */
    private String pdate;

    /**
     * 销售开始日
     */
    private String ssdate;

    /**
     * 销售截止日
     */
    private String sedate;

    /**
     * 认购划款日
     */
    private String subpaydate;

    /**
     * 发行规模上限（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal upperlimit;

    /**
     * 发行规模下限（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowerlimit;

    /**
     * 认购起点（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal startamt;

    /**
     * 追加金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal addamt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 经办人ID
     */
    private String submituserid;

    /**
     * 经办人姓名
     */
    private String submitusername;

    /**
     * 复核人ID
     */
    private String reviewuserid;

    /**
     * 复核人姓名
     */
    private String reviewusername;

    /**
     * 系统平台日期
     */
    private String cdate;

    /**
     * 销售模式
     */
    private String salemode;

    /**
     * 代销产品代码
     */
    private String prodid1;

    /**
     * 帐户分行行号
     */
    private String acctbranchid;

    /**
     * 接口ID
     */
    private String interfId;

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
     * 取得 产品简称
     * @return 产品简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置 产品简称
     * @param shortname 产品简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * 设置 产品名称
     * @param prodname 产品名称
     */
    public void setProdname(String prodname) {
        this.prodname = prodname == null ? null : prodname.trim();
    }

    /**
     * 取得 资产池
     * @return 资产池
     */
    public String getAssetpool() {
        return assetpool;
    }

    /**
     * 设置 资产池
     * @param assetpool 资产池
     */
    public void setAssetpool(String assetpool) {
        this.assetpool = assetpool == null ? null : assetpool.trim();
    }

    /**
     * 取得 销售部门
     * @return 销售部门
     */
    public String getMktdeptno() {
        return mktdeptno;
    }

    /**
     * 设置 销售部门
     * @param mktdeptno 销售部门
     */
    public void setMktdeptno(String mktdeptno) {
        this.mktdeptno = mktdeptno == null ? null : mktdeptno.trim();
    }

    /**
     * 取得 产品一级分类
     * @return 产品一级分类
     */
    public String getProdtype1() {
        return prodtype1;
    }

    /**
     * 设置 产品一级分类
     * @param prodtype1 产品一级分类
     */
    public void setProdtype1(String prodtype1) {
        this.prodtype1 = prodtype1 == null ? null : prodtype1.trim();
    }

    /**
     * 取得 销售平台
     * @return 销售平台
     */
    public String getMktplat() {
        return mktplat;
    }

    /**
     * 设置 销售平台
     * @param mktplat 销售平台
     */
    public void setMktplat(String mktplat) {
        this.mktplat = mktplat == null ? null : mktplat.trim();
    }

    /**
     * 取得 产品处理模式
     * @return 产品处理模式
     */
    public String getProdmode() {
        return prodmode;
    }

    /**
     * 设置 产品处理模式
     * @param prodmode 产品处理模式
     */
    public void setProdmode(String prodmode) {
        this.prodmode = prodmode == null ? null : prodmode.trim();
    }

    /**
     * 取得 收益分配类别
     * @return 收益分配类别
     */
    public String getIndistype() {
        return indistype;
    }

    /**
     * 设置 收益分配类别
     * @param indistype 收益分配类别
     */
    public void setIndistype(String indistype) {
        this.indistype = indistype == null ? null : indistype.trim();
    }

    /**
     * 取得 滚动模式
     * @return 滚动模式
     */
    public String getRollmode() {
        return rollmode;
    }

    /**
     * 设置 滚动模式
     * @param rollmode 滚动模式
     */
    public void setRollmode(String rollmode) {
        this.rollmode = rollmode == null ? null : rollmode.trim();
    }

    /**
     * 取得 管理模式
     * @return 管理模式
     */
    public String getIncomemode() {
        return incomemode;
    }

    /**
     * 设置 管理模式
     * @param incomemode 管理模式
     */
    public void setIncomemode(String incomemode) {
        this.incomemode = incomemode == null ? null : incomemode.trim();
    }

    /**
     * 取得 滚动期数
     * @return 滚动期数
     */
    public Short getRollamt() {
        return rollamt;
    }

    /**
     * 设置 滚动期数
     * @param rollamt 滚动期数
     */
    public void setRollamt(Short rollamt) {
        this.rollamt = rollamt;
    }

    /**
     * 取得 本期天数
     * @return 本期天数
     */
    public Short getSyperiod() {
        return syperiod;
    }

    /**
     * 设置 本期天数
     * @param syperiod 本期天数
     */
    public void setSyperiod(Short syperiod) {
        this.syperiod = syperiod;
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssuetype() {
        return issuetype;
    }

    /**
     * 设置 发行方式
     * @param issuetype 发行方式
     */
    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype == null ? null : issuetype.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCusname() {
        return cusname;
    }

    /**
     * 设置 客户名称
     * @param cusname 客户名称
     */
    public void setCusname(String cusname) {
        this.cusname = cusname == null ? null : cusname.trim();
    }

    /**
     * 取得 分行
     * @return 分行
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 分行
     * @param branch 分行
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 取得 投资方向
     * @return 投资方向
     */
    public String getInvestscope() {
        return investscope;
    }

    /**
     * 设置 投资方向
     * @param investscope 投资方向
     */
    public void setInvestscope(String investscope) {
        this.investscope = investscope == null ? null : investscope.trim();
    }

    /**
     * 取得 产品成立日
     * @return 产品成立日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 产品成立日
     * @param vdate 产品成立日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate == null ? null : vdate.trim();
    }

    /**
     * 取得 产品到期日
     * @return 产品到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 产品到期日
     * @param mdate 产品到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public Short getTerm() {
        return term;
    }

    /**
     * 设置 产品期限
     * @param term 产品期限
     */
    public void setTerm(Short term) {
        this.term = term;
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 预期收益率（年化）
     * @return 预期收益率（年化）
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 预期收益率（年化）
     * @param yield 预期收益率（年化）
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSolutionid() {
        return solutionid;
    }

    /**
     * 设置 方案代码
     * @param solutionid 方案代码
     */
    public void setSolutionid(String solutionid) {
        this.solutionid = solutionid == null ? null : solutionid.trim();
    }

    /**
     * 取得 认购币种
     * @return 认购币种
     */
    public String getSubccy() {
        return subccy;
    }

    /**
     * 设置 认购币种
     * @param subccy 认购币种
     */
    public void setSubccy(String subccy) {
        this.subccy = subccy == null ? null : subccy.trim();
    }

    /**
     * 取得 收益币种
     * @return 收益币种
     */
    public String getSettccy() {
        return settccy;
    }

    /**
     * 设置 收益币种
     * @param settccy 收益币种
     */
    public void setSettccy(String settccy) {
        this.settccy = settccy == null ? null : settccy.trim();
    }

    /**
     * 取得 付息周期
     * @return 付息周期
     */
    public String getIntpaycycle() {
        return intpaycycle;
    }

    /**
     * 设置 付息周期
     * @param intpaycycle 付息周期
     */
    public void setIntpaycycle(String intpaycycle) {
        this.intpaycycle = intpaycycle == null ? null : intpaycycle.trim();
    }

    /**
     * 取得 销售费率
     * @return 销售费率
     */
    public BigDecimal getSyield() {
        return syield;
    }

    /**
     * 设置 销售费率
     * @param syield 销售费率
     */
    public void setSyield(BigDecimal syield) {
        this.syield = syield;
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getHyield() {
        return hyield;
    }

    /**
     * 设置 托管费率
     * @param hyield 托管费率
     */
    public void setHyield(BigDecimal hyield) {
        this.hyield = hyield;
    }

    /**
     * 取得 资产管理费率
     * @return 资产管理费率
     */
    public BigDecimal getAmyield() {
        return amyield;
    }

    /**
     * 设置 资产管理费率
     * @param amyield 资产管理费率
     */
    public void setAmyield(BigDecimal amyield) {
        this.amyield = amyield;
    }

    /**
     * 取得 其他费率
     * @return 其他费率
     */
    public BigDecimal getOyield() {
        return oyield;
    }

    /**
     * 设置 其他费率
     * @param oyield 其他费率
     */
    public void setOyield(BigDecimal oyield) {
        this.oyield = oyield;
    }

    /**
     * 取得 信息公布日
     * @return 信息公布日
     */
    public String getPdate() {
        return pdate;
    }

    /**
     * 设置 信息公布日
     * @param pdate 信息公布日
     */
    public void setPdate(String pdate) {
        this.pdate = pdate == null ? null : pdate.trim();
    }

    /**
     * 取得 销售开始日
     * @return 销售开始日
     */
    public String getSsdate() {
        return ssdate;
    }

    /**
     * 设置 销售开始日
     * @param ssdate 销售开始日
     */
    public void setSsdate(String ssdate) {
        this.ssdate = ssdate == null ? null : ssdate.trim();
    }

    /**
     * 取得 销售截止日
     * @return 销售截止日
     */
    public String getSedate() {
        return sedate;
    }

    /**
     * 设置 销售截止日
     * @param sedate 销售截止日
     */
    public void setSedate(String sedate) {
        this.sedate = sedate == null ? null : sedate.trim();
    }

    /**
     * 取得 认购划款日
     * @return 认购划款日
     */
    public String getSubpaydate() {
        return subpaydate;
    }

    /**
     * 设置 认购划款日
     * @param subpaydate 认购划款日
     */
    public void setSubpaydate(String subpaydate) {
        this.subpaydate = subpaydate == null ? null : subpaydate.trim();
    }

    /**
     * 取得 发行规模上限（元）
     * @return 发行规模上限（元）
     */
    public BigDecimal getUpperlimit() {
        return upperlimit;
    }

    /**
     * 设置 发行规模上限（元）
     * @param upperlimit 发行规模上限（元）
     */
    public void setUpperlimit(BigDecimal upperlimit) {
        this.upperlimit = upperlimit;
    }

    /**
     * 取得 发行规模下限（元）
     * @return 发行规模下限（元）
     */
    public BigDecimal getLowerlimit() {
        return lowerlimit;
    }

    /**
     * 设置 发行规模下限（元）
     * @param lowerlimit 发行规模下限（元）
     */
    public void setLowerlimit(BigDecimal lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    /**
     * 取得 认购起点（元）
     * @return 认购起点（元）
     */
    public BigDecimal getStartamt() {
        return startamt;
    }

    /**
     * 设置 认购起点（元）
     * @param startamt 认购起点（元）
     */
    public void setStartamt(BigDecimal startamt) {
        this.startamt = startamt;
    }

    /**
     * 取得 追加金额（元）
     * @return 追加金额（元）
     */
    public BigDecimal getAddamt() {
        return addamt;
    }

    /**
     * 设置 追加金额（元）
     * @param addamt 追加金额（元）
     */
    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 经办人ID
     * @return 经办人ID
     */
    public String getSubmituserid() {
        return submituserid;
    }

    /**
     * 设置 经办人ID
     * @param submituserid 经办人ID
     */
    public void setSubmituserid(String submituserid) {
        this.submituserid = submituserid == null ? null : submituserid.trim();
    }

    /**
     * 取得 经办人姓名
     * @return 经办人姓名
     */
    public String getSubmitusername() {
        return submitusername;
    }

    /**
     * 设置 经办人姓名
     * @param submitusername 经办人姓名
     */
    public void setSubmitusername(String submitusername) {
        this.submitusername = submitusername == null ? null : submitusername.trim();
    }

    /**
     * 取得 复核人ID
     * @return 复核人ID
     */
    public String getReviewuserid() {
        return reviewuserid;
    }

    /**
     * 设置 复核人ID
     * @param reviewuserid 复核人ID
     */
    public void setReviewuserid(String reviewuserid) {
        this.reviewuserid = reviewuserid == null ? null : reviewuserid.trim();
    }

    /**
     * 取得 复核人姓名
     * @return 复核人姓名
     */
    public String getReviewusername() {
        return reviewusername;
    }

    /**
     * 设置 复核人姓名
     * @param reviewusername 复核人姓名
     */
    public void setReviewusername(String reviewusername) {
        this.reviewusername = reviewusername == null ? null : reviewusername.trim();
    }

    /**
     * 取得 系统平台日期
     * @return 系统平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 系统平台日期
     * @param cdate 系统平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 销售模式
     * @return 销售模式
     */
    public String getSalemode() {
        return salemode;
    }

    /**
     * 设置 销售模式
     * @param salemode 销售模式
     */
    public void setSalemode(String salemode) {
        this.salemode = salemode == null ? null : salemode.trim();
    }

    /**
     * 取得 代销产品代码
     * @return 代销产品代码
     */
    public String getProdid1() {
        return prodid1;
    }

    /**
     * 设置 代销产品代码
     * @param prodid1 代销产品代码
     */
    public void setProdid1(String prodid1) {
        this.prodid1 = prodid1 == null ? null : prodid1.trim();
    }

    /**
     * 取得 帐户分行行号
     * @return 帐户分行行号
     */
    public String getAcctbranchid() {
        return acctbranchid;
    }

    /**
     * 设置 帐户分行行号
     * @param acctbranchid 帐户分行行号
     */
    public void setAcctbranchid(String acctbranchid) {
        this.acctbranchid = acctbranchid == null ? null : acctbranchid.trim();
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }
}